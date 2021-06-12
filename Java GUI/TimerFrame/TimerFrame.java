import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TimerFrame extends JFrame{
  public TimerFrame(){
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    
    JLabel timerLabel = new JLabel();
    timerLabel.setFont(new Font("Gothic", Font.PLAIN, 80));
    c.add(timerLabel);
    
    ThreadTimer timer = new ThreadTimer(timerLabel);
    timer.start();
    
    setSize(300,200);
    setVisible(true);
  }
  
  public static void main(String[] args){
    new TimerFrame();
  }
}

class ThreadTimer extends Thread{
  private JLabel timerLabel;
  public ThreadTimer(JLabel timerLabel){
    this.timerLabel = timerLabel;
  }
  
  @Override
  public void run(){
    int n=0;
    while(true){
      timerLabel.setText(Integer.toString(n));
      n++;
      try{
        sleep(500);
      }catch(InterruptedException e){return;}
    }
  }
}

