package game;

import java.awt.Color;
import java.io.Serializable;

public class Block implements Serializable{

	private Color color;
	
	public Block() {
		color = Color.BLACK;
	}

	public Block(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Block [color=" + color + "]";
	}
	
}
