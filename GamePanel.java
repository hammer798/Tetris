//Tetris

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {
	private ArrayList<Point> usedPieces;
	private Piece activePiece;
	private CanvasPanel canvas;
	private JLabel scoreLabel;
	private int score = 0;
	private int time = 1000;
	private int modulo = 1000;
	private Timer timer;
	
	//constructor, organizes panel, initializes variables
	public GamePanel(){
		usedPieces = new ArrayList<Point>();
		setLayout(new BorderLayout());
		canvas = new CanvasPanel();
		JButton b = new JButton("Start Game");
		scoreLabel  = new JLabel("Score: " + score);
		
		//set up controls label
		JLabel controls = new JLabel("Controls:");
		JLabel rotL = new JLabel("Rotate Left: \'w\'");
		JLabel rotR = new JLabel("Rotate Right: \'s\'");
		JLabel shiftL = new JLabel("Shift Left: \'a\'");
		JLabel shiftR = new JLabel("Shift Right: \'d\'");
		JLabel jump = new JLabel("Jump to Bottom: \'p\'");
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(controls);
		controlPanel.add(rotL);
		controlPanel.add(rotR);
		controlPanel.add(shiftL);
		controlPanel.add(shiftR);
		controlPanel.add(jump);
		
		//adds buttonlistener to the button
		b.addActionListener(new ButtonListener());
		
		//add components to the panel
		add(canvas);
		add(b, BorderLayout.SOUTH);
		add(scoreLabel, BorderLayout.NORTH);
		add(controlPanel, BorderLayout.EAST);
	}
	
	//class ButtonListener, sets up the game, adds the key listener and starts the timer to run the game
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			//game setup
			nextPiece();
			usedPieces = new ArrayList<Point>();
			score = 0;
			scoreLabel.setText("Score: " + score);
			canvas.repaint();
			
			//start timer, runs TetrisTask every second
			timer = new Timer();
			timer.schedule(new TetrisTask(), time, time);
			
			//add key listener if not already added
			KeyListener[] list = getListeners(KeyListener.class);
			setFocusable(true);
			requestFocusInWindow();
			if(list.length == 0) {
				addKeyListener(new ControlKeys());
			}
		}
	}//end of ButtonListener
	
	//class CanvasPanel, the panel where the game action will occur
	private class CanvasPanel extends JPanel{
		//paintComponent method draws the pieces onto the canvas if they exist
		public void paintComponent(Graphics page) {
			super.paintComponent(page);
			setBackground(Color.WHITE);
			if(activePiece != null)
				activePiece.draw(page);
			if(usedPieces.size() > 0) {
				for(Point p: usedPieces) {
					page.setColor(Color.GRAY);
					page.fillRect(p.x-15, p.y-15, 30, 30);
				}
			}
		}
	}//end of CanvasPanel
	
	//class ControlKeys determines what happens when a key is typed
	private class ControlKeys implements KeyListener{
		public void keyPressed(KeyEvent k) {}
		
		//takes whatever key is typed and does the appropriate action
		public void keyTyped(KeyEvent k) {
			if(k.getKeyChar() == 'w') {
				activePiece.rotateLeft();
				//checks if piece can rotate or if there's a piece or a wall there, rotates back if necessary
				if(activePiece.isInsideOrOut(usedPieces))
					activePiece.rotateRight();
			}
			//check if something is to the left, shifts if possible
			else if(k.getKeyChar() == 'a' && !activePiece.somethingLeft(usedPieces))
					activePiece.shiftLeft();
			else if(k.getKeyChar() == 's') {
				activePiece.rotateRight();
				//checks if piece can rotate or if there's a piece or a wall there, rotates back if necessary
				if(activePiece.isInsideOrOut(usedPieces))
					activePiece.rotateLeft();
			}
			//check if something is to the right, shifts if possible
			else if(k.getKeyChar() == 'd' && !activePiece.somethingRight(usedPieces))
				activePiece.shiftRight();
			else if(k.getKeyChar() == 'p')
				activePiece.jumpToBottom(usedPieces);
			canvas.repaint();
		}
		public void keyReleased(KeyEvent k) {}
	}//end of ControlKeys
	
	//sort ArrayList from least to greatest
	public ArrayList<Integer> sort(ArrayList<Integer> array) {
		if(array != null) {
			for(int x = 0; x < array.size(); x ++)
			{
				for(int y = 1; y < array.size(); y++) {
					if(array.get(x) > array.get(y)) {
						int temp = array.get(y);
						array.set(y, array.get(x));
						array.set(x, temp);
					}
				}
			}
		}
		return array;

	}
	//checkRows checks if a row is filled, deletes the row if it is
	public void checkRows()
	{
		ArrayList<Integer> toBeDeleted = new ArrayList<Integer>();
		for(Point p: usedPieces){
			int numInSameRow = 0;
			for(Point s: usedPieces)
			{
				if(p.y == s.y)
					numInSameRow++;
			}
			//checks if all 10 spots in the row are filled
			if(numInSameRow == 10)
			{
				boolean found = false;
				//checks if this row has been chosen for deletion yet
				for(int x: toBeDeleted)
				{
					if(x == p.y)
						found = true;
				}
				if(!found)
					toBeDeleted.add(p.y);	
			}
		}
		//sort toBeDeleted
		toBeDeleted = sort(toBeDeleted);
		
		//deletes the rows
		for(int x = 0; x < toBeDeleted.size(); x ++)
			deleteRow(toBeDeleted.get(x));
	}
	
	//deletes the row and adds to the score
	public void deleteRow(int row) {
		int size = usedPieces.size();
		
		//goes through usedPieces and removes those in that row, if above that row, moves them down
		for(int x = 0; x < size; x ++) {
			if(usedPieces.get(x).y == row)
			{
				usedPieces.remove(usedPieces.get(x));
				x--;
				size--;
			}
			else if(usedPieces.get(x).y < row)
				usedPieces.get(x).y += 30;
		}
		//adds points and updates score on screen
		score += 100;
		scoreLabel.setText("Score: " + score);
		
		canvas.repaint();
	}
	
	//assigns the next random piece to become the new active piece
	public void nextPiece()
	{
		Random r = new Random();
		int choice = r.nextInt(5);
		switch(choice) {
			case 0:
				activePiece = new LBlock();
				break;
			case 1:
				activePiece = new ReverseLBlock();
				break;
			case 2:
				activePiece = new TBlock();
				break;
			case 3:
				activePiece = new StickBlock();
				break;
			case 4:
				activePiece = new SquareBlock();
		}
	}
	
	//runs the game
	public void playGame(){
		//moves the piece down, checks if touching top of another piece or the ground
		activePiece.shiftDown();
		if(activePiece.isTouching(usedPieces)) {
			for(Point p: activePiece.getSpaces())
			{
				usedPieces.add(p);
			}
			//checks rows after piece is deactivated and gets next piece
			checkRows();
			if(score % modulo == 0 && score != 0 && time > 500)
			{
				modulo += 1000;
				time -= 100;
				timer.cancel();
				timer = new Timer();
				timer.schedule(new TetrisTask(), time, time);
			}
			
			nextPiece();
		}
		// if the user has lost, stop the game and tell the user their score
		if(gameOver()) {
			timer.cancel();
			scoreLabel.setText("You lost! Your score was " + score + ". Press \'Start Game\' to play again.");
		}
	}
	
	//checks if the user has lost
	public boolean gameOver() {
		for(Point p: usedPieces)
		{
			if(p.y <= 90)
				return true;
		}
		return false;
	}
	
	//TetrisTask runs the game and updates the canvas every second
	private class TetrisTask extends TimerTask{
		public void run()
		{
			playGame();
			canvas.repaint();
		}
	}//end TetrisTask
}//end GamePanel

