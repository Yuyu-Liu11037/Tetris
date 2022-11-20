package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TetrisFrame {

	private JFrame frame;

	private final int BOARD_WIDTH = 280;
	private final int BOARD_HEIGHT = 460;
	int level;
	Timer timer;
	TetrisModel model;

	List<Integer> scores = new ArrayList<Integer>();

	public TetrisFrame(int level) {
		model = new TetrisModel(level, BOARD_WIDTH, BOARD_HEIGHT);
		this.level = level;
		initialize();
		frame.setVisible(true);
	}

	public TetrisFrame(TetrisModel model) {
		this.model = model;
		this.level = model.getLevel();
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(300, 100, BOARD_WIDTH, BOARD_HEIGHT + 40);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Block map[][] = model.getMap();
				Block shapes[][] = model.getShapes();
				int posX = model.getPosX(), posY = model.getPosY();
				int blockWidth = model.getBlockWidth();
				int blockHeight = model.getBlockHeight();

				for (int row = 0; row < map.length; row++) {
					for (int col = 0; col < map[row].length; col++) {
						if (map[row][col] == null) {
							g.setColor(Color.BLACK);
							g.fillRect(blockWidth * (col), blockHeight * (row),
									blockWidth - 2, blockHeight - 2);
						} else {
							g.setColor(map[row][col].getColor());
							g.fillRect(blockWidth * (col), blockHeight * (row),
									blockWidth, blockHeight);
						}
					}
				}
				for (int row = 0; row < shapes.length; row++) {
					for (int col = 0; col < shapes[row].length; col++) {
						if (shapes[row][col] != null) {
							g.setColor(shapes[row][col].getColor());
							g.fillRect(blockWidth * (col + posY), blockHeight
									* (row + posX), blockWidth - 2,
									blockHeight - 2);
						}
					}
				}

				g.setColor(Color.WHITE);
				g.drawString("Score : " + model.getScore(), 10,
						BOARD_HEIGHT - 10);
			}
		};

		panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		//创造一个计时器，初始化初始延迟和事件间延迟到毫秒
		timer = new Timer(600 - level * 60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model.isGameOver()) {
					timer.stop();
					showGameOver();
					return;
				}
				model.down();
				panel.repaint();
			}
		});
		//启动timer，使它开始向其监听器发送动作事件
		timer.start();
		//接收键盘事件(击键)的侦听器接口
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					model.rotate();
					break;
				case KeyEvent.VK_DOWN:
					model.down();
					break;
				case KeyEvent.VK_LEFT:
					model.toLeft();
					break;
				case KeyEvent.VK_RIGHT:
					model.toRight();
					break;
				case KeyEvent.VK_P:
					showPause();
					break;
				}
				panel.repaint();
			}
		});
		initScores();
	}

	@SuppressWarnings("unchecked")
	private void initScores() {
         try {
			//创建对象输入流的对象
			ObjectInputStream stream = new ObjectInputStream(
					new FileInputStream(new File("scores")));
			//将输入流读取到Object
			Object readObject = stream.readObject();
			//instanceof关键字：测试左边的对象是否是它右边的类的实例，返回boolean
			if (readObject instanceof ArrayList) {
				scores = (ArrayList<Integer>) stream.readObject();
			}
			stream.close();
		} catch (Exception e) {
		}	
	}

	private void showPause() {
		timer.stop();
		JDialog dialog = new JDialog(frame, true);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.setBounds(frame.getX() + 45, frame.getY() + 45, 180, 280);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

		JLabel lblPause = new JLabel("PAUSE");
		lblPause.setHorizontalAlignment(SwingConstants.CENTER);
		lblPause.setPreferredSize(new Dimension(160, 15));
		lblPause.setFont(new Font("����", Font.BOLD, 16));
		lblPause.setForeground(Color.WHITE);
		contentPane.add(lblPause);

		JButton btResume = new JButton("RESUME");
		contentPane.add(btResume);

		JButton btReturn = new JButton("RETURN TO MAIN");
		contentPane.add(btReturn);
		JButton btSave = new JButton("SAVE PROGRESS");
		contentPane.add(btSave);

		JButton btQuit = new JButton("Quit");
		contentPane.add(btQuit);

		dialog.setContentPane(contentPane);
		btResume.addActionListener(e -> {
			dialog.dispose();
			timer.start();
		});
		btSave.addActionListener(e -> {
			saveProgress();
		});
		btQuit.addActionListener(e -> {
			System.exit(0);
		});
		btReturn.addActionListener(e -> {
			dialog.dispose();
			frame.dispose();
			new StartGUI().setVisible(true);
		});
		dialog.setVisible(true);
	}

	private void showGameOver() {
		timer.stop();
		JDialog dialog = new JDialog(frame, true);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.setBounds(frame.getX() + 45, frame.getY() + 45, 180, 290);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPause = new JLabel("GAME OVER");
		lblPause.setHorizontalAlignment(SwingConstants.CENTER);
		lblPause.setPreferredSize(new Dimension(160, 15));
		lblPause.setFont(new Font(null, Font.BOLD, 16));
		lblPause.setForeground(Color.WHITE);
		contentPane.add(lblPause);

		JButton btRestart = new JButton("RESTART");
		contentPane.add(btRestart);

		JButton btReturn = new JButton("RETURN TO HOME");
		contentPane.add(btReturn);
		JLabel jLabel = new JLabel("Your Score is : " + model.getScore());
		jLabel.setForeground(Color.RED);
		contentPane.add(jLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(130, 140));
		contentPane.add(scrollPane);

		JTable table = new JTable();
		scores.add(model.getScore());
		Collections.sort(scores);
		Collections.reverse(scores);
		Object data[][] = new Object[scores.size()][1];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = scores.get(i);
		}

		table.setModel(new DefaultTableModel(data, new String[] { "High SCORES" }));
		scrollPane.setViewportView(table);
		dialog.setContentPane(contentPane);
		btRestart.addActionListener(e -> {
			//dispose()释放本机所有屏幕资源
			dialog.dispose();
			frame.dispose();
			new TetrisFrame(level);
		});
		btReturn.addActionListener(e -> {
			dialog.dispose();
			frame.dispose();
			new StartGUI().setVisible(true);
		});
		saveScore();
		dialog.setVisible(true);
	}

	private void saveScore() {
		try {
			File file = new File("scores");
			ObjectOutputStream stream = new ObjectOutputStream(
					new FileOutputStream(file));
			stream.writeObject(scores);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	public void saveProgress() {
		try {
			String fileName = "Tetris"+System.currentTimeMillis()+".txt";
			File file = new File(fileName);
			ObjectOutputStream stream = new ObjectOutputStream(
					new FileOutputStream(file));
			stream.writeObject(model);
			stream.flush();
			stream.close();
			//消息提示框
			JOptionPane.showMessageDialog(frame, "Save the progress in path :"+file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
