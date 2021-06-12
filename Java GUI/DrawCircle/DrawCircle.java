//명품 Java Programming ch12 Q08 680p
//draw circle when mouse dragged

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DrawCircle extends JFrame{
  public DrawCircle(){
    super("Draw Circle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setContentPane(new MyPanel());
    
    setSize(500,500);
    setVisible(true);
  }
  
  public static void main(String[] args){
    new DrawCircle();
  }
}

class MyPanel extends JPanel{
  private int x1, x2, y1, y2;
  private Vector<Circle> circle = new Vector<>();

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    for(int i=1; i<10; i++){
      g.drawLine(0, i*50, 500, i*50);
      g.drawLine(i*50, 0, i*50, 500);
    }

    for(int i=0; i<circle.size(); i++){
      Circle c = circle.get(i);
      g.drawOval(c.get_cX() - c.get_r() , c.get_cY() - c.get_r(), 2*c.get_r(), 2*c.get_r());
    }

  }

  public MyPanel(){
    addMouseListener(new MouseAdapter(){
      @Override
      public void mousePressed(MouseEvent e){
        x1 = (int)e.getPoint().getX();
        y1 = (int)e.getPoint().getY();  
      }

      @Override
      public void mouseReleased(MouseEvent e){
        x2 = (int)e.getPoint().getX();
        y2 = (int)e.getPoint().getY();  

        int cX = (int)((x1 + x2) * 0.5);
        int cY = (int)((y1 + y2) * 0.5);
        int r = (int)(Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2)) * 0.5);

        circle.add(new Circle(cX, cY, r));

        repaint();
     }
   });
  }
}

class Circle{
      private int cX, cY, r;
      
      public Circle(int cX, int cY, int r){
        this.cX = cX;
        this.cY = cY;
        this.r = r;
      }

      public int get_cX(){
        return cX;
      }
      
      public int get_cY(){
        return cY;
      }
      
      public int get_r(){
        return r;
      }
    }


