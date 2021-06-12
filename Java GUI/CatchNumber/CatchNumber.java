//명품 Java Programming ch11 Q10 638p
//Catch Number Game
//0부터 9까지 순서대로 클릭하여 10개를 모두 클릭하면 다시 10개의 레이블을 랜덤한 위치에 배치한다.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CatchNumber extends JFrame{
  private JLabel [] labels = new JLabel[10];
  private MouseListener listener = new MyMouseListener();
  private int currentNum=0;
  private Container c; 
  
  public CatchNumber(){
    super("Catch Number");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    c = getContentPane();
    c.setLayout(null);
    
    //labels 배열 초기화, 리스너 할당
    for(int i=0; i<labels.length; i++){
      labels[i] = new JLabel(Integer.toString(i));
      labels[i].addMouseListener(listener);
    }
    
    //레이블 초기 배치
    for(int i=0; i<labels.length; i++){
      int x = (int)(Math.random()*290);
      int y = (int)(Math.random()*290);
      labels[i].setLocation(x,y);
      labels[i].setSize(20,20);
      c.add(labels[i]);
    }
    
    //final
    setSize(300,300);
    setVisible(true);
  }
  
  class MyMouseListener extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e){
      JLabel label = (JLabel)e.getSource();
      if(label.getText().equals(Integer.toString(currentNum))){
        label.setVisible(false);
        currentNum++;
      }
      relocation();
    }
  }
  
  //숫자를 전부 눌렀을 시 다시 10개의 레이블을 랜덤한 위치에 배치
  public void relocation(){
    if(currentNum == 10){
      for(int i=0; i<labels.length; i++){
        int x = (int)(Math.random()*290);
        int y = (int)(Math.random()*290);
        
        labels[i].setVisible(true);
        labels[i].setLocation(x,y);
        labels[i].setSize(20,20);
        c.add(labels[i]);
        currentNum=0;
      }
    }
  }
  
  public static void main(String[] args){
    new CatchNumber();
  }  
}


