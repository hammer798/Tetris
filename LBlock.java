//Assignment: Honors Contract - Tetris
//Name: Ian Bradley
//ASU ID: 1213339060
//Lecture: T Th 4:30
//Description: 

import java.util.*;
import java.awt.*;

//extends abstract class Piece
public class LBlock extends Piece {

	//initializes fields in constructor
	public LBlock()
	{
		spaces = new ArrayList<Point>();
		spaces.add(new Point(135, 15));
		spaces.add(new Point(135, 45));
		spaces.add(new Point(135, 75));
		spaces.add(new Point(165, 75));
		
		pieceColor = Color.RED;
		orientation = 1;
	}
	
	//rotates the block 90 degrees counterclockwise
	public void rotateLeft() {
		switch(orientation)
		{
			case 1: 
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y - 60));
				break;
			case 2: 
				spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x + 60, spaces.get(3).y));
				break;
			case 3: 
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y - 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y + 30));
				spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y + 60));
				break;
			default:
				spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y + 30));
				spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y - 30));
				spaces.set(3,  new Point(spaces.get(3).x - 60, spaces.get(3).y));
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
			spaces.set(3,  new Point(spaces.get(3).x - 60, spaces.get(3).y));
			break;
		case 2:
			spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y + 30));
			spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y - 30));
			spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y - 60));
			break;
		case 3:
			spaces.set(0,  new Point(spaces.get(0).x - 30, spaces.get(0).y - 30));
			spaces.set(2,  new Point(spaces.get(2).x + 30, spaces.get(2).y + 30));
			spaces.set(3,  new Point(spaces.get(3).x + 60, spaces.get(3).y));
			break;
		default:
			spaces.set(0,  new Point(spaces.get(0).x + 30, spaces.get(0).y - 30));
			spaces.set(2,  new Point(spaces.get(2).x - 30, spaces.get(2).y + 30));
			spaces.set(3,  new Point(spaces.get(3).x, spaces.get(3).y + 60));
	}
	if(orientation == 4)
		orientation = 1;
	else 
		orientation++;

	}

}//end LBlock
