//Tetris

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

//subclass of abstract piece class
public class TBlock extends Piece {

	//defines fields in Constructor
	public TBlock() {
		spaces = new ArrayList<Point>();
		spaces.add(new Point(135, 15));
		spaces.add(new Point(135, 45));
		spaces.add(new Point(135, 75));
		spaces.add(new Point(105, 75));
		spaces.add(new Point(165, 75));
		
		pieceColor = Color.GREEN;
		orientation = 1;
	}

	//rotates the block 90 degrees counterclockwise
	public void rotateLeft() {
		switch(orientation)
		{
			case 1: 
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x + 60, spaces.get(3).y));
				spaces.set(4,  new Point(spaces.get(4).x, spaces.get(4).y - 60));
				break;
			case 2: 
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y + 60));
				spaces.set(4,  new Point(spaces.get(4).x + 60, spaces.get(4).y));
				break;
			case 3: 
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x - 60, spaces.get(3).y));
				spaces.set(4,  new Point(spaces.get(4).x, spaces.get(4).y + 60));
				break;
			default:
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y - 60));
				spaces.set(4,  new Point(spaces.get(4).x - 60, spaces.get(4).y));
		}
		if(orientation == 1)
			orientation = 4;
		else
			orientation --;
	}

	//rotates the block 90 degrees clockwise
	public void rotateRight() {
		switch(orientation) {
			case 1: 
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y - 60));
				spaces.set(4,  new Point(spaces.get(4).x - 60, spaces.get(4).y));
				break;
			case 2:
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x + 60, spaces.get(3).y));
				spaces.set(4,  new Point(spaces.get(4).x, spaces.get(4).y - 60));
				break;
			case 3:
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y + 60));			
				spaces.set(4,  new Point(spaces.get(4).x + 60, spaces.get(4).y));
				break;
			default:
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x - 60, spaces.get(3).y));
				spaces.set(4,  new Point(spaces.get(4).x, spaces.get(4).y + 60));
		}
		if(orientation == 4)
			orientation = 1;
		else 
			orientation++;
	}

}//end of TBlock
