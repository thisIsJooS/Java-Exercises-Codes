//명품 Java Programming ch11 Q06 636p
//문자열을 입력할 때마다 입력된 글자 수에 맞게 슬라이더 바가 자동으로 움직이게 하고 
//100자 이상 입력할수 없게 한다.
//또한, 사용자가 마우스로 슬라이더 손잡이를 움직이면 슬라이더의 크기만큼 텍스트영역의 글자가 지워진다.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class JLabelandJTextArea extends JFrame{
  private JTextArea ta;
  private JSlider sl;
  private StringBuffer sb = new StringBuffer();
  private JLabel val = new JLabel("0");
  public MyFrame(){
    Container c = getContentPane();
    c.setLayout(null);

    //TextArea
    ta = new JTextArea();
    
    //JScrollPane
    JScrollPane sp = new JScrollPane(ta);
    sp.setBounds(0,0,300,100);
    ta.addKeyListener(new KeyAdapter(){
      @Override
      public void keyTyped(KeyEvent e){
        if(ta.getText().length() <= 100){
          sb.delete(0, sb.length());
          sb.append(ta.getText());
          sl.setValue(sb.length());
        }
        else{
          ta.setText(sb.toString());
        }
      }
    });
    c.add(sp);
    
    //Silder
    sl = new JSlider(JSlider.HORIZONTAL, 0,100,0);
    sl.setPaintLabels(true);
    sl.setPaintTicks(true);
    sl.setPaintTrack(true);
    sl.setMinorTickSpacing(5);
    sl.setMajorTickSpacing(20);
    sl.setBounds(0,150,300,50);
    sl.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        JSlider s = (JSlider)e.getSource();
        sb.setLength(s.getValue());
        ta.setText("");
        ta.setText(sb.toString());
        val.setText(Integer.toString(s.getValue()));
      }
    });
    c.add(sl);
    
    //label
    val.setOpaque(true);
    val.setBackground(Color.PINK);
    val.setBounds(135,120,30,30);
    val.setHorizontalAlignment(JLabel.CENTER);
    c.add(val);
    
    //final
    setSize(300,200);
    setVisible(true);
  }

  
  public static void main(String[] args){
    new JLabelandJTextArea();
  }  
}


