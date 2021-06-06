package BulletGame;

import javax.swing.*;

public class BulletGameFrame extends JFrame{
  private GamePanel panel = new GamePanel();
  
  public BulletGameFrame(){
    setTitle("Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setContentPane(panel);
    
    setSize(500,700);
    setVisible(true);
    
  }
  
  public static void main(String [] args){
    new BulletGameFrame();
  }
}