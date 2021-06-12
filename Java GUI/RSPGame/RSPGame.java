//명품 Java Programming ch11 Q09 638p
//Rock Scissors Paper Game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RSPGame extends JFrame{
  private JButton [] btnArr = new JButton[3];
  private ImageIcon [] images = {
    new ImageIcon("/workspace/GUI/src/images/Rock.jpg"),
    new ImageIcon("/workspace/GUI/src/images/Scissors.jpg"),
    new ImageIcon("/workspace/GUI/src/images/Paper.jpg")
  };
  private JLabel me;
  private JLabel com;
  private String myPick;
  private String comPick;
  private JLabel res;
  
  private NorthPanel northPanel = new NorthPanel();
  private CenterPanel centerPanel = new CenterPanel();
  
  public RSPGame(){
    super("Rock Scissors Paper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    
    add(northPanel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
 
    //final
    setSize(300,300);
    setVisible(true);
  }
  
  class NorthPanel extends JPanel{
    public NorthPanel(){
      //NorthPanel
      setLayout(null);
      setPreferredSize(new Dimension(300,150));
      setVisible(true);
      setBackground(Color.GRAY);

      ActionListener listener = new MyActionListener();

      for(int i=0; i< btnArr.length; i++){
        btnArr[i] = new JButton("", images[i]);
        btnArr[i].setBounds(i*100, 0, 100, 150);
        btnArr[i].addActionListener(listener);
        if(i==1)
          btnArr[i].setName("rock");
        else if(i==2)
          btnArr[i].setName("scissors");
        else
          btnArr[i].setName("paper");
        add(btnArr[i]);
      }
    }
  }
  
  class CenterPanel extends JPanel{
    public CenterPanel(){
      setLayout(new FlowLayout());
      setPreferredSize(new Dimension(300,150));
      setVisible(true);
      setBackground(Color.PINK);

      me = new JLabel("me", null ,SwingConstants.CENTER);
      com = new JLabel("com", null ,SwingConstants.CENTER);

      res = new JLabel();
      res.setSize(100,30);
      res.setOpaque(true);
      res.setBackground(Color.CYAN);

      add(res);
      add(me);
      add(com);
    }
  }
  
  class MyActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      JButton btn = (JButton)e.getSource();
      me.setIcon((ImageIcon)btn.getIcon());
      myPick = btn.getName();
      
      int r = (int)(Math.random()*3);
      com.setIcon(images[r]);
      comPick = btnArr[r].getName();
      
      if(myPick.equals("rock") && comPick.equals("rock"))
        res.setText("DRAW");
      else if(myPick.equals("rock") && comPick.equals("scissors"))
        res.setText("YOU WIN!!!");
      else if(myPick.equals("rock") && comPick.equals("paper"))
        res.setText("YOU LOSE...");
      else if(myPick.equals("scissors") && comPick.equals("rock"))
        res.setText("YOU LOSE...");
      else if(myPick.equals("scissors") && comPick.equals("scissors"))
        res.setText("DRAW");
      else if(myPick.equals("scissors") && comPick.equals("paper"))
        res.setText("YOU WIN!!!");
      else if(myPick.equals("paper") && comPick.equals("rock"))
        res.setText("YOU WIN!!!");
      else if(myPick.equals("paper") && comPick.equals("scissors"))
        res.setText("YOU LOSE...");
      else if(myPick.equals("paper") && comPick.equals("paper"))
        res.setText("DRAW");
    }
  }
  
  public static void main(String[] args){
    new RSPGame();
  }  
}


