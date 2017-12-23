//Assignment: Honors Contract - Tetris
//Name: Ian Bradley
//ASU ID: 1213339060
//Lecture: T Th 4:30
//Description: 

import java.awt.*;
import java.util.*;

//Piece class
public abstract class Piece {
	protected ArrayList<Point> spaces;
	protected Color pieceColor;
	protected int orientation;
	
	//abstract, rotates 90 degrees counterclockwise
	public abstract void rotateLeft();
	//abstract, rotates 90 degrees clockwise
	public abstract void rotateRight();
	
	//moves the piece to the the space right before the bottom of the screen
	public void jumpToBottom(ArrayList<Point> otherPoints) {
		while(!isTouching(otherPoints)) {
			shiftDown();
		}
		shiftUp();		
	}
	
	//checks whether the piece is currently inside another piece or out of bounds
	public boolean isInsideOrOut(ArrayList<Point> otherPoints) {
		for(Point p: spaces)
		{
			for(Point s: otherPoints)
			{
				if(p.y == s.y && p.x == s.x)
					return true;
				else if(p.x > 285 || p.x < 15)
					return true;
			}
		}
		return false;
	}
	
	//shifts the piece right
	public void shiftRight() {
		for(Point p: spaces) {
			p.x += 30;
		}
	}
	
	//shifts the piece left
	public void shiftLeft() {
		for(Point p: spaces) {
			p.x -= 30;
		}
	}
	
	//shifts the piece down
	public void shiftDown() {
		for(Point p: spaces) {
			p.y += 30;
		}
	}
	
	//shifts the piece up
	public void shiftUp() {
		for(Point p: spaces) {
			p.y -= 30;
		}
	}
	
	//draws the piece with the specified color
	public void draw(Graphics page){
		page.setColor(pieceColor);
		for(Point p: spaces)
		{
			page.fillRect(p.x-15, p.y-15, 30, 30);
		}
	}
	
	//checks whether there is a piece or a wall to the left
	public boolean somethingLeft(ArrayList<Point> otherPoints) {
		for(Point p: spaces)
		{
			if(p.x == 15)
				return true;
			for(Point s: otherPoints)
			{
					if(s.y == p.y && s.x == p.x - 30)
						return true;
			}

		}
		
		return false;
	}
	
	//checks whether there is a piece or wall to the right
	public boolean somethingRight(ArrayList<Point> otherPoints) {
		for(Point p: spaces)
		{
			if(p.x == 285)
				return true;
			for(Point s: otherPoints)
			{
					if(s.y == p.y && s.x == p.x + 30)
						return true;
			}

		}
		
		return false;
	}
	
	//checks whether the piece is touching the ground or the top of another piece
	public boolean isTouching(ArrayList<Point> otherPoints)
	{
		for(Point p: spaces)
		{
			if(p.y == 700 - 55)
				return true;
			for(Point s: otherPoints)
			{
					if(s.y == p.y + 30 && s.x == p.x)
						return true;
			}

		}
		
		return false;
	}
	
	//returns the ArrayList that defines the piece
	public ArrayList<Point> getSpaces(){
		return spaces;
	}
	
}//end of Piece
