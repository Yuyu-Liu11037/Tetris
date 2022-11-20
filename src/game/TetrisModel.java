package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TetrisModel implements Serializable{

	private int blockWidth = 28;
	private int blockHeight = 28;

	private int BOARD_WIDTH = 280;
	private int BOARD_HEIGHT = 460;
	private final int ROW = 22;
	private final int COL = 10;
	private Block map[][] = new Block[ROW][COL];
	private Block shapes[][];

	private int posX = 0, posY = 3;

	private Random random = new Random();

	private List<List<Block[][]>> shapeList = new ArrayList<List<Block[][]>>();
	private int shapeIndex;
	private int curTurn;
	private int score;
	private boolean isGameOver = false;
	private int level;

	public TetrisModel(int level, int width, int height) {
		this.level = level;
		BOARD_WIDTH = width;
		BOARD_HEIGHT = height;
		shapeList.add(BlockFactory.genareteShape("T"));
		shapeList.add(BlockFactory.genareteShape("l"));
		shapeList.add(BlockFactory.genareteShape("Z"));
		shapeList.add(BlockFactory.genareteShape("S"));
		shapeList.add(BlockFactory.genareteShape("O"));
		shapeList.add(BlockFactory.genareteShape("L"));
		shapeList.add(BlockFactory.genareteShape("J"));
		initialize();
	}

	private void initialize() {
		for (int i = 0; i < COL; i++) {
			map[ROW - 1][i] = new Block();
		}
		blockWidth = (BOARD_WIDTH - 14) / COL;
		blockHeight = (BOARD_HEIGHT - 36) / ROW;
		shapeIndex = random.nextInt(shapeList.size());
		curTurn = random.nextInt(4);
		shapes = shapeList.get(shapeIndex).get(curTurn);
	}

	public void rotate() {
		if (canMove(posX, posY, shapeList.get(shapeIndex)
				.get((curTurn + 1) % 4))) {
			curTurn++;
			shapes = shapeList.get(shapeIndex).get((curTurn) % 4);
		}
	}

	public void toLeft() {
		if (canMove(posX, posY - 1, shapes)) {
			posY--;
		}
	}

	public void toRight() {
		if (canMove(posX, posY + 1, shapes)) {
			posY++;
		}
	}

	public void down() {
		if (isGameOver) {
			return;
		}
		if (canMove(posX + 1, posY, shapes)) {
			posX++;
		} else {
			for (int row = 0; row < shapes.length; row++) {
				for (int col = 0; col < shapes[row].length; col++) {
					if (shapes[row][col] != null) {
						map[row + posX][col + posY] = shapes[row][col];
					}
				}
			}
			while (check()) {
			}
			posX = 0;
			posY = 3;
			resetColor();
			shapeIndex = random.nextInt(shapeList.size());
			curTurn = random.nextInt(4);
			shapes = shapeList.get(shapeIndex).get(curTurn);
			if (!canMove(posX, posY, shapes)) {
				isGameOver = true;
			}
		}
	}

	private boolean check() {
		for (int i = ROW - 2; i >= 0; i--) {
			int fullCount = 0;
			for (int j = 0; j < COL; j++) {
				if (map[i][j] != null) {
					fullCount++;
				}
			}
			if (fullCount == COL) {
				for (int j = i; j > 0; j--) {
					for (int m = 0; m < COL; m++) {
						map[j][m] = map[j - 1][m];
					}
				}
				score += 10;
				return true;
			}

		}
		return false;
	}

	public boolean canMove(int posX, int posY, Block[][] shapes) {
		for (int row = 0; row < shapes.length; row++) {
			for (int col = 0; col < shapes[row].length; col++) {
				if (shapes[row][col] != null
						&& (col + posY < 0 || col + posY >= COL)) {
					return false;
				}
				if (shapes[row][col] != null
						&& map[row + posX][col + posY] != null) {
					return false;
				}
			}
		}
		return true;
	}

	public void resetColor() {
		shapeList.clear();
		shapeList.add(BlockFactory.genareteShape("T"));
		shapeList.add(BlockFactory.genareteShape("l"));
		shapeList.add(BlockFactory.genareteShape("Z"));
		shapeList.add(BlockFactory.genareteShape("S"));
		shapeList.add(BlockFactory.genareteShape("O"));
		shapeList.add(BlockFactory.genareteShape("L"));
		shapeList.add(BlockFactory.genareteShape("J"));
	}
	
	public Block[][] getMap() {
		return map;
	}

	public int getBlockWidth() {
		return blockWidth;
	}

	public int getBlockHeight() {
		return blockHeight;
	}

	public Block[][] getShapes() {
		return shapes;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getScore() {
		return score;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public int getLevel() {
		return level;
	}
 
}
