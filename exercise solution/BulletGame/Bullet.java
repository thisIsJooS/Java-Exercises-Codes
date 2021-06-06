package BulletGame;

import java.awt.*;
import javax.swing.*;

public class Bullet extends JLabel{
  private int width = 10;
  private int height = 50;
  
  //타깃 이미지
  ImageIcon icon = new ImageIcon("/workspace/GUI/src/BulletGame/images/bullet.png");
  Image image = icon.getImage();
  Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
  ImageIcon imageIcon = new ImageIcon(newImage);
  
  public int getWidth(){
    return this.width;
  }
  
  public int getHeight(){
    return this.height;
  }
  
  public Bullet(){
    setIcon(imageIcon);
    setSize(width,height);
    setLocation((GamePanel.panelWidth - width)/2,GamePanel.panelHeight - GamePanel.baseHeight - height);
  }
}