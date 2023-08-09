import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	//declaration section--------->
	//length of snake array
	private int[] snakexlength= new int[750];
	private int[] snakeylength= new int[750];
	
	//movement of snake 
 	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	
	//snake images added
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakeimage;
	
	
	//this is the place where the food/enemy of snake is show----->
	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625};
	private ImageIcon enemyImage;
	private Random random=new Random();
	
	//this is the length fo enemyxpos
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);
	
	
	// time 
	private Timer timer;
	private int delay=100;
	
	//declaring snake Length 
	private int lengthofsnake=3;
	private int moves=0;
	private ImageIcon titleImage;
	
	public Gameplay(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer=new Timer(delay,this);
		timer.start();
	}
	
	// in this function we add all the images and background colors
	public void paint (Graphics g) {
		
		// moves snake into the screen
		if(moves==0)
		{
			snakexlength[0]=100;
			snakexlength[1]=75;
			
			snakexlength[2]=50;
			
			snakeylength[0]=100;
			snakeylength[1]=100;
			snakeylength[2]=100;
			
		}
		//border of title image
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		//border of Game play
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		//to paint the snake----------->
		rightmouth= new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
		
		for(int a=0;a<lengthofsnake;a++) {
			if(a==0 && right) {
				rightmouth= new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0 && left) {
				leftmouth= new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0 && up) {
				upmouth= new ImageIcon("upmouth.png");
				upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a==0 && down) {
				downmouth= new ImageIcon("downmouth.png");
				downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
			if(a!=0)
			{
				snakeimage= new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this,g,snakexlength[a],snakeylength[a]);
			}
		}
		
		//adding food/enemy image
		enemyImage = new ImageIcon("enemy.png");
		enemyImage.paintIcon(this, g,enemyxpos[xpos], enemyypos[ypos]); 
		//to check if snake head touch the food/enemy size increase and change the food position
		if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0]) 
		{
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			
		}
		
		//to show game over 
		for(int b=1;b<lengthofsnake;b++) 
		{
		if(snakexlength[b]==snakexlength[0] && snakeylength[b]==snakeylength[0] )
		     {
			right=false;
			left=false;
			up=false;
			down=false;
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,50));
			g.drawString("Game Over",300,300);
			//Restart the game
			g.setFont(new Font("arial",Font.BOLD,20));
			g.drawString("Space to RESTART",350,350);
			
		     }
		}
		g.dispose();
		
	}
	
	//auto call when timer is start
	//this is also used to move the snake left,right,up,down
	@Override
	public void actionPerformed(ActionEvent argO) {
		//to check the snake direction 
		//to move the snake from its position using shift space front to back
		if(right){
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeylength[i+1]=snakeylength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakexlength[i]=snakexlength[i]+25;
				}
				else {
					snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]>850) {
					snakexlength[i]=25;
				}
			}
			//we have to repaint because if we dont it show no changes
			repaint();
		}
		if(left){
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeylength[i+1]=snakeylength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakexlength[i]=snakexlength[i]-25;
				}
				else {
					snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]<25) {
					snakexlength[i]=850;
				}
			}
			repaint();
		}
		if(up){
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeylength[i]=snakeylength[i]-25;
				}
				else {
					snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]<75) {
					snakeylength[i]=625;
				}
			}
			repaint();
		}
		if(down){
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeylength[i]=snakeylength[i]+25;
				}
				else {
					snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]>625) {
					snakeylength[i]=75;
				}
			}
			repaint();
		}
	}
	
	//this is used to when we press key where to move the snake 
	//to check which arrow key pressed
	@Override
	public void keyPressed(KeyEvent e) {
		// it show how the are working to move the snake left and write
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			moves++;
			//if are snake is in left side we can not move it to right it touch him self
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			moves++;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			moves++;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
		     left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			moves++;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			left=false;
			right=false;
		}
		//when we restart the game 
		if(e.getKeyCode()== KeyEvent.VK_SPACE) 
		{
			moves=0;
			lengthofsnake=3;
			repaint();
		}
			
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent argO) {
	}
	@Override
	public void keyReleased(KeyEvent argO) {
	}	
	}
	


