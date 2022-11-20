package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFactory {

	private static final String[] colors = new String[] { "#FFFFCC", "#CCFFFF", "#FFCCCC", "#99CCCC", "#FFCC99",
			"#FFCCCC", "#FF9999", "#996699", "#FFCCCC", "#CC9999", "#FFFFCC", "#CCCC99", "#FFCCCC", "#FFFF99",
			"#CCCCFF", "#0099CC", "#CCCCCC", "#FF6666", "#FF9966", "#FF6666", "#FFCCCC", "#CC9966", "#666666",
			"#CC9999", "#FF6666", "#FFFF66", "#99CC66", "#CC3333", "#CCCCCC", "#003366", "#993333", "#CCCC00",
			"#663366", "#CCCC99", "#666666", "#CC9999", "#FF6666", "#FFFF00", "#FFCC99", "#FFFF99", "#99CC99",
			"#FFCC99", "#CCFF99", "#CCCCCC", "#FFCC99", "#FFFFCC", "#99CCFF", "#FF9966", "#FFFFCC", "#99CC99",
			"#FF9900", "#FFFFCC", "#336699", "#CCCC33", "#FFFF99", "#CC9933", "#996600", "#FFCC33", "#FFFFCC",
			"#FFFFCC", "#CC9933", "#336666", "#FF9900", "#FFFF00", "#0099CC", "#99CC33", "#FF9900", "#FFCC00",
			"#FF9933", "#99CC33", "#CC6699", "#FF9933", "#FFFF00", "#3366CC", "#FF9933", "#FFFFCC", "#009966",
			"#FF6600", "#FFFF66", "#009966", "#990033", "#CCFF66", "#FF9900", "#FF9966", "#996600", "#CCCC00",
			"#CC6600", "#999999", "#CCCC33", "#CC6600", "#CCCC33", "#CCFF99", "#FFFFFF", "#66CCCC", "#339999",
			"#FFFFFF", "#99CCFF", "#66CC99", "#FFFFFF", "#666699", "#009999", "#66CCCC", "#CCFFFF", "#66CCCC",
			"#CCFF66", "#FF99CC", "#339999", "#FFFF00", "#336699", "#CC9933", "#339999", "#FFCC33", "#FFCC00",
			"#009999", "#CC3333", "#669999", "#CCCCCC", "#CC99CC", "#339999" };
	private static final Random RANDOM = new Random();
	
	public static List<Block[][]> genareteShape(String type) {
		switch (type) {
		case "T":
			return genareteShapeT();
		case "l":
			return genareteShapel();
		case "Z":
			return genareteShapeZ();
		case "S":
			return genareteShapeS();
		case "O":
			return genareteShapeO();
		case "L":
			return genareteShapeL();
		case "J":
			return genareteShapeJ();
		default:
			break;
		}
		return null;		
	}

	private static List<Block[][]> genareteShapeJ() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ null, block ,null , null },
			{ null, block, null, null }, 
			{ block, block, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ block, block, block, null },
			{ null, null, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ block,  block, null, null },
			{ block, null, null, null }, 
			{ block, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ block, null, null, null },
			{ block, block, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapeL() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ block,null ,null , null },
			{ block, null, null, null }, 
			{ block, block, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ null, null, block, null },
			{ block, block, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ block,  block, null, null },
			{ null, block, null, null }, 
			{ null, block, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ block, block, block, null },
			{ block, null, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapeS() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ null,  block, block, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ block, null, null, null },
			{ block, block, null, null }, 
			{ null, block, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ null,  block, block, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ block, null, null, null },
			{ block, block, null, null }, 
			{ null, block, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapeO() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ block,  block, null, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ block,  block, null, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ block,  block, null, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ block,  block, null, null },
			{ block, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapeZ() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ block,  block, null, null },
			{ null, block, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ null, block, null, null },
			{ block, block, null, null }, 
			{ block, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ block,  block, null, null },
			{ null, block, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ null, block, null, null },
			{ block, block, null, null }, 
			{ block, null, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapel() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ null, null, null, null },
			{ block, block, block, block }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ null, block, null, null },
			{ null, block, null, null }, 
			{ null, block, null, null }, 
			{ null, block, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ null, null, null, null },
			{ block, block, block, block }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ null, block, null, null },
			{ null, block, null, null }, 
			{ null, block, null, null }, 
			{ null, block, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}

	private static List<Block[][]> genareteShapeT() {
		List<Block[][]> lists = new ArrayList<Block[][]>();
		Block block = new Block(Color.decode(colors[RANDOM.nextInt(colors.length)]));
		Block[][] shapeT1 = new Block[][] { 
			{ null, block, null, null },
			{ block, block, block, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT2 = new Block[][] { 
			{ null, block, null, null },
			{ block, block, null, null }, 
			{ null, block, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT3 = new Block[][] { 
			{ block, block, block, null },
			{ null, block, null, null }, 
			{ null, null, null, null }, 
			{ null, null, null, null } };
		Block[][] shapeT4 = new Block[][] { 
			{ null, block, null, null },
			{ null, block, block, null }, 
			{ null, block, null, null }, 
			{ null, null, null, null } };
		lists.add(shapeT1);
		lists.add(shapeT2);
		lists.add(shapeT3);
		lists.add(shapeT4);
		return lists;
	}
}
