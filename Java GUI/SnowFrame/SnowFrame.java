import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SnowFrame extends JFrame{
  private ImageIcon icon = new ImageIcon("/workspace/GUI/src/images/background.png");
  private Image backgroundImg = icon.getImage();
  private final int WIDTH = icon.getIconWidth();
  private final int HEIGHT = icon.getIconHeight();
  
  public SnowFrame(){
    super("Snow");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(new SnowPane());
    
    setSize(WIDTH,HEIGHT);
    setVisible(true);
  }
  
  public static void main(String[] args){
    new SnowFrame();
  }
  
  class SnowPane extends JPanel{
    private static final int SNOW_THREAD_COUNT = 10;
    private static final int SNOW_SIZE = 10;
    private Snows [] snows = new Snows [SNOW_THREAD_COUNT];
    
    public SnowPane(){
      setLayout(null);
      
      setSize(WIDTH,HEIGHT);
      setVisible(true);
      
      //Snows클래스를 만들어 바로 그에따른 스레드 생성후 시작.
      for(int i=0; i<snows.length; i++){
        snows[i] = new Snows();
        Thread th = new Thread(snows[i]);
        th.start();
      }
    }
        
    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.setColor(Color.WHITE);
      g.drawImage(backgroundImg, 0, 0, this);
      
      //panel에 snow들을 그린다. 
      for(int i=0; i<snows.length; i++){
        for(int j=0; j<snows[i].v.size(); j++){
          g.fillOval(snows[i].v.get(j).x, snows[i].v.get(j).y, SNOW_SIZE, SNOW_SIZE);
        }
      }
      
    }
  }
  
  class Snows implements Runnable{
    private static final int SNOW_COUNT_PER_CLASS = 10;
    //눈 들의 좌표를 저장할 벡터
    Vector<Point> v = new Vector<>();
    
    //랜덤으로 좌표를 지정하여 벡터에 추가
    public Snows(){
      for(int i=0; i<SNOW_COUNT_PER_CLASS; i++){
        int x = (int)(Math.random() * WIDTH);
        int y = (int)(Math.random() * HEIGHT);
        v.add(new Point(x,y));
      }
    }
    
    @Override
    public void run(){
      while(true){
        int r = (int)(Math.random() * 20) + 10; //눈이 떨어지는 속도르르 다르게 하기 위해
        try{
          Thread.sleep(100);
        }catch(InterruptedException e){return;}
        
        for(int i=0; i<v.size(); i++){
          v.get(i).y += r;
          
          //눈이 다 떨어지면 위에서 다시 나오게 함
          if(v.get(i).y >= HEIGHT)
            v.get(i).y = 0;
        }
        
        //paintComponent() 함수 호출
        repaint();
      }
    }
  }

}

