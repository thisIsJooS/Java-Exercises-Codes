package BulletGame;

import java.awt.*;
import javax.swing.*;

public class Target extends JLabel{
  private int width = 100;
  private int height = 100;
  
  //타깃 이미지
  ImageIcon icon = new ImageIcon("/workspace/GUI/src/BulletGame/images/target.png");
  Image image = icon.getImage();
  Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
  ImageIcon imageIcon = new ImageIcon(newImage);
  
  public int getWidth(){
    return this.width;
  }
  
  public int getHeight(){
    return this.height;
  }
  
  public Target(){
    setIcon(imageIcon);
    setSize(width,height);
    setVisible(true);
    setLocation(0,0);
  }
}