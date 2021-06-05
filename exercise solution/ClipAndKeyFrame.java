//명품 Java Programming ch12 Open Challenge 673p
//ClipAndKeyFrame

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ClipAndKeyFrame extends JFrame{
  private ImageIcon icon = new ImageIcon("/workspace/GUI/src/images/iu2.jpg");
  private Image img = icon.getImage();
  private ImagePanel imagePanel = new ImagePanel();
  private int x = 100, y = 100;
  private int width = 400;
  private int height = 600;
  private int clipSize = 70;
  
  public ClipAndKeyFrame(){
    super("Open Challenge 12");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().add(imagePanel);
    
    setSize(width,height);
    setVisible(true);
    
    imagePanel.setFocusable(true);
    imagePanel.requestFocus();
  }
  
  class ImagePanel extends JPanel{
    public ImagePanel(){
      addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
          if(e.getKeyCode() == KeyEvent.VK_UP){
            y = (y > 0) ? (y - 10) : 0;
            repaint();
          }
          else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x = (x > 0) ? (x - 10) : 0;
            repaint();
          }
          else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x = (x < width-clipSize) ? (x + 10) : width-clipSize;
            repaint();
          }
          else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y = (y < height-clipSize) ? (y + 10) : height-clipSize;
            repaint();
          }
        }
      });
      
      addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JPanel panel = (JPanel)e.getSource();
					panel.requestFocus();
				}
			});
    }
    
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.setClip(x, y, clipSize,clipSize);
      g.drawImage(img, 0,0, getWidth(), getHeight(), this);
    }
  }

  public static void main(String[] args){
    new ClipAndKeyFrame();
  }
}

