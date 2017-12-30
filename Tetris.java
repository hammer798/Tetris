//Tetris

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Tetris class extends JApplet
public class Tetris extends JApplet {

	//starts the applet
	public void init()
	{
		GamePanel gamePanel = new GamePanel();
		getContentPane().add(gamePanel);
		setSize(410, 700);
	}
}
	