import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class FallingWordFrame extends JFrame{
  private Scanner scanner;
  private BufferedReader br;
  private static Vector<String> v; 
  private ProcessPanel processPanel;
  private ClientPanel clientPanel;
  private ManagerPanel managerPanel;
  
  public FallingWordFrame(){
    v = makeVector();
    clientPanel = new ClientPanel();
    managerPanel = new ManagerPanel();
    processPanel = new ProcessPanel(v, clientPanel, managerPanel);
    
    Container c = getContentPane();
    
    c.add(managerPanel, BorderLayout.NORTH);
    c.add(processPanel, BorderLayout.CENTER);
    c.add(clientPanel, BorderLayout.SOUTH);
    
    Thread gameThread = new Thread(processPanel);
    gameThread.start();
    
    setSize(400,700);
    setVisible(true);
  }
  
  public static void main(String[] args){
    new FallingWordFrame();
  }
  
  private Vector<String> makeVector(){        //words.txt로부터 불러온 단어들을 벡터에 저장을 한다.
    Vector<String> vec = new Vector<>();  
    String tmp;
    try{
      File f = new File("/workspace/GUI/src/words.txt");
      br = new BufferedReader(new FileReader(f));

      while((tmp = br.readLine()) != null){
          vec.add(tmp);
      }
      br.close();
    }catch(IOException e){}
    return vec;
  }

}

class ProcessPanel extends JPanel implements Runnable{
  private ClientPanel clientPanel;
  private ManagerPanel managerPanel;
  private int r;
  private String fallingWord;
  private int x,y;
  private Vector<String> v;
  private ImageIcon icon = new ImageIcon("/workspace/GUI/src/images/bg.jpg");
  private Image img = icon.getImage();
  
  private int level = 1;
  private static final int SPEED_LEVEL_1 = 100;
  private static final int SPEED_LEVEL_2 = 70;
  private static final int SPEED_LEVEL_3 = 50;
  private static final int SPEED_LEVEL_4 = 30;
  private int countAnswer = 0;
  
  public ProcessPanel(Vector<String> v, ClientPanel clientPanel, ManagerPanel managerPanel){
    this.v = v;
    this.clientPanel  = clientPanel;
    this.managerPanel = managerPanel;
    setLayout(null);

    r = (int)(Math.random() * v.size());
    fallingWord = v.get(r);
    x = (int)(Math.random() * 300) ;
    y = 0;
  
  }

  @Override
  public void run(){
    while(true){
      y += 5;
      try{
        switch(level){
          case 1:
            Thread.sleep(SPEED_LEVEL_1);
            break;
          case 2:
            Thread.sleep(SPEED_LEVEL_2);
            break;
          case 3:
            Thread.sleep(SPEED_LEVEL_3);
            break;
          case 4:
            Thread.sleep(SPEED_LEVEL_4);
            break;
        }
        
      }catch(InterruptedException e){return;}
      repaint();

      if( y > this.getHeight()){          
        nextStep();
      }
      
      if(clientPanel.getSubmit()){
        clientPanel.resetTextField();
        clientPanel.setSubmitFalse();
        if(fallingWord.equals(clientPanel.getClientText())){
          countAnswer++;
          clientPanel.getMessageLabel().setText("Correct !!!");
          nextStep();
        }
        else{
          clientPanel.getMessageLabel().setText("Try again ...");
        }
      }
      setLevel(countAnswer);
      
      managerPanel.getLevelLabel().setText("현재 단계  : " + Integer.toString(level));
      managerPanel.get_cntLabel().setText("정답 횟수  : " + Integer.toString(countAnswer));
      
      if(countAnswer == 20){
        return;
      }
    }
  }
  
  public void setLevel(int countAnswer){
    if(countAnswer < 5)
      level = 1;
    else if(countAnswer < 10)
      level = 2;
    else if(countAnswer < 15)
      level = 3;
    else 
      level = 4;
  }
  
  public void nextStep(){
      r = (int)(Math.random() * v.size());
      fallingWord = v.get(r);

      x = (int)(Math.random() * 300);
      y = 0;
      repaint();
    }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Gothic", Font.BOLD, 20));

    g.drawImage(img, 0, 0, this);
    g.drawString(fallingWord, x, y);
    
    if(countAnswer == 20){
      g.setFont(new Font("Arial", Font.BOLD, 40));
      g.setColor(Color.RED);
      g.drawString("You Win...!!!" , 100, 300);
    }
  }
}

class ClientPanel extends JPanel{
  private JTextField tf = new JTextField(15);
  private JButton btn = new JButton("Enter");
  private JLabel messageLabel = new JLabel("Start !!!", SwingConstants.CENTER);
  private String clientText;
  private boolean submit = false;
  
  public ClientPanel(){
    setLayout(null);
    
    add(tf);
    
    tf.setSize(190,20);
    tf.setLocation(20,10);
    tf.setVisible(true);
    tf.setFont(new Font("Gothic", Font.BOLD, 15));
    tf.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        JTextField t = (JTextField)e.getSource();
        clientText = t.getText();
        submit = true;
      }
    });
    
    add(btn);
    btn.setSize(60,20);
    btn.setLocation(220,10);
    btn.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
    btn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        clientText = tf.getText();
        submit = true;
      }
    });
    
    messageLabel.setSize(90,30);
    messageLabel.setLocation(300, 5);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 15));
    messageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    messageLabel.setForeground(Color.WHITE);
    
    add(messageLabel);
    
    setBackground(Color.DARK_GRAY);
    setFocusable(true);
    requestFocus();
    
    setPreferredSize(new Dimension(400,40));
    setVisible(true);
  }
  
  public String getClientText(){
    return clientText;
  }
  
  public boolean getSubmit(){
    return submit;
  }
  
  public void setSubmitFalse(){
    submit = false;
  }
  
  public void resetTextField(){
    tf.setText("");
  }
  
  public JLabel getMessageLabel(){
    return messageLabel;
  }
}

class ManagerPanel extends JPanel{
  private JLabel levelLabel = new JLabel();
  private JLabel cntLabel = new JLabel();
  
  public ManagerPanel(){
    setLayout(new FlowLayout(FlowLayout.CENTER, 70 , 10));
    
    levelLabel.setFont(new Font("Gothic", Font.PLAIN, 14));
    levelLabel.setForeground(Color.WHITE);
    add(levelLabel);
    
    cntLabel.setFont(new Font("Gothic", Font.PLAIN, 14));
    cntLabel.setForeground(Color.WHITE);
    add(cntLabel);
    
    setBackground(Color.DARK_GRAY);
  }
  
  public JLabel getLevelLabel(){
    return levelLabel;
  }
  
  public JLabel get_cntLabel(){
    return cntLabel;
  }
}
