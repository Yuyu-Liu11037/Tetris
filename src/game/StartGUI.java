package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StartGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox;

	public static void main(String[] args) {
		StartGUI frame = new StartGUI();
		frame.setVisible(true);
	}

	public StartGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(300, 100, 280, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("TETRIS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("����", Font.BOLD, 45));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		p1.setPreferredSize(new Dimension(260, 300));
		panel.add(p1);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btPlay = new JButton("PLAY");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = Integer.parseInt(comboBox.getSelectedItem().toString().split(" ")[1]);
				dispose();
				new TetrisFrame(level);
			}
		});
		btPlay.setPreferredSize(new Dimension(150, 23));
		p1.add(btPlay);
		
		JButton btLoad = new JButton("Load Game");
		btLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				
				try {
					JFileChooser fileChooser = new JFileChooser(new File("."));
					int result = fileChooser.showOpenDialog(contentPane);
			        if (result == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			            TetrisModel mode = (TetrisModel) stream.readObject();
			            stream.close();
			            dispose();
			            new TetrisFrame(mode);
			        }
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Not a record file!");
				}
			}
		});
		btLoad.setPreferredSize(new Dimension(150, 23));
		p1.add(btLoad);
		
		comboBox = new JComboBox<>();
		comboBox.setPreferredSize(new Dimension(150, 21));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"LEVEL: 1", "LEVEL: 2", "LEVEL: 3", "LEVEL: 4", "LEVEL: 5", "LEVEL: 6", "LEVEL: 7", "LEVEL: 8", "LEVEL: 9"}));
		p1.add(comboBox);
		
		JPanel space = new JPanel();
		space.setBackground(Color.BLACK);
		space.setPreferredSize(new Dimension(150, 100));
		p1.add(space);
		
		JTextArea info = new JTextArea();
		info.setLineWrap(true);
		info.setForeground(Color.WHITE);
		info.setBackground(Color.BLACK);
		info.setEditable(false);
		info.setFont(new Font("Monospaced", Font.BOLD, 10));
		info.setText("Press <SPACE> to  turn the tetrominoes;\r\nPress key <LEFT> to move to left;\r\nPress key <RIGHT> to move to right;\r\nPress key <P> to pause the game;");
		info.setPreferredSize(new Dimension(240, 100));
		p1.add(info);
	}

}