//명품 Java Programming Open Challenge Ch10 577p
//간단한 갬블링 게임 
//<ENTER> 키를 입력할 때마다 3개의 수를 랜덤하게 발생시켜 각 레이블에 출력한다.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamblingGameFrame extends JFrame{
  public GamblingGameFrame(){
    super("Open Challenge 10"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setContentPane(new GamePanel());
    
    getContentPane().setFocusable(true);
    getContentPane().requestFocus();
      
    setSize(500,300);
    setVisible(true);
  }
  
  class GamePanel extends JPanel{
      JLabel [] labels = new JLabel[3];
      JLabel res = new JLabel("시작합니다");
      
      public GamePanel(){
        setLayout(null);
        
        for(int i=0; i<labels.length; i++){  //3개의 레이블 설정
          labels[i] = new JLabel("0");
          labels[i].setSize(100,100);
          labels[i].setLocation(50+130*i, 50);
          labels[i].setHorizontalAlignment(JLabel.CENTER);
          labels[i].setOpaque(true);
          labels[i].setBackground(Color.CYAN);
          labels[i].setFont(new Font("Tahoma", Font.ITALIC, 30));
          add(labels[i]);
        }
        res.setSize(200,20);
        res.setLocation(200,200);
        add(res);
        
        addKeyListener(new KeyAdapter(){  //엔터 키를 눌렀을 때 랜덤 수 추출
          @Override
          public void keyPressed(KeyEvent e){
            if(e.getKeyChar() == '\n'){
            int x1 = (int)(Math.random()*5); 
						labels[0].setText(Integer.toString(x1));
						int x2 = (int)(Math.random()*5);
						labels[1].setText(Integer.toString(x2));
						int x3 = (int)(Math.random()*5);
						labels[2].setText(Integer.toString(x3));
						
						if(x1 == x2 && x2 == x3) 
							res.setText("축하합니다!!");
						else
							res.setText("아쉽군요.");
            }
          }
        });
        
        addMouseListener(new MouseAdapter(){  //마우스 클릭시 포커스 설정
          @Override
          public void mouseClicked(MouseEvent e){
            Component c  = (Component)e.getSource();
            c.requestFocus();
          }
        });
      }
    }
  public static void main(String[] args){
    new GamblingGameFrame();
  }  
}


