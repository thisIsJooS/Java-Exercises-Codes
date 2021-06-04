//명품 Java Programming ch12 Q011 681p
//draw Pie Chart

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PieChart extends JFrame{
  private String [] itemName = {"apple", "cherry", "strawberry", "prune"};
  private JTextField [] tf = new JTextField [4];
  private int [] data = {0,0,0,0};
  private int [] arcAngle = new int[4];
  private Color [] color = {Color.red, Color.blue, Color.magenta, Color.orange};
  private ChartPanel chartPanel = new ChartPanel();
  private int [] ratio = {0,0,0,0};
  
  public PieChart(){
    setTitle("Pie Chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().add(new InputPanel(), BorderLayout.NORTH);
    getContentPane().add(chartPanel, BorderLayout.CENTER);
    
    setSize(500,400);
    setVisible(true);
    drawChart();
  }
  
  public static void main(String[] args){
    new PieChart();
  }
  
  public void drawChart(){
    int sum=0;
    for(int i=0; i< data.length; i++){
      data[i] = Integer.parseInt(tf[i].getText());
      sum+=data[i];
    }
    
    if(sum==0) return;
    
    for(int i=0; i< data.length; i++){
      ratio[i] = (int)Math.round((double)data[i]/(double)sum*100);
      arcAngle[i] = (int)Math.round(360*(double)data[i]/(double)sum);
    }
    chartPanel.repaint();
  }
  
  private class InputPanel extends JPanel{
    private MyActionListener listener = new MyActionListener();
    public InputPanel(){
      setLayout(new FlowLayout());
      setBackground(Color.LIGHT_GRAY);
      for(int i=0; i<data.length; i++){
        add(new JLabel(itemName[i]));
        tf[i] = new JTextField("0", 5);
        tf[i].addActionListener(listener);
        add(tf[i]);
        
      }
    }
  }
  
  class MyActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      JTextField t = (JTextField)e.getSource();
      
      //textfield에 입력한 값이 정수인지를 판단한다. 정수가 아닐경우 0으로 setText하고 리턴.
      int k;
      try{
        k = Integer.parseInt(t.getText());
      }catch(NumberFormatException ex){
        t.setText("0");
        return;
      }

      drawChart();
    }
  }

  private class ChartPanel extends JPanel{
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      for(int i=0; i<data.length; i++){
        g.setColor(color[i]);
        g.drawString(itemName[i] + " "+ Integer.toString(ratio[i]) + " %" ,100*i + 50 ,20);
      }
      
      int startAngle =0 ;
      for(int i=0; i<data.length; i++){
        g.setColor(color[i]);
        g.fillArc(100,80,250,250, startAngle, arcAngle[i]);
        startAngle += arcAngle[i];
      }
    }
  }
}

