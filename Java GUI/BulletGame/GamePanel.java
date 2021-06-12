package BulletGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel{
  //target, bullet 객체 생성
  private Target target = new Target();
  private Bullet bullet = new Bullet();
  private JLabel base = new JLabel();;
  private Image backgroundImage;
  private BulletThread thBullet;
  private TargetThread thTarget;
  private SuccessLabel successLabel = new SuccessLabel();
  
  //패널과 베이스의 너비,높이 고정
  static final int panelWidth = 500;
  static final int panelHeight = 700;
  static final int baseWidth = 100;
  static final int baseHeight = 20;

  
  public GamePanel(){
    setLayout(null);
    
    //배경 이미지 작업
    ImageIcon icon = new ImageIcon("/workspace/GUI/src/BulletGame/images/sky.jpg");
    Image image = icon.getImage();
    backgroundImage = image.getScaledInstance(panelWidth,panelHeight, Image.SCALE_SMOOTH);

    //총알이 나오는 고정된 레이블
    base.setOpaque(true);
    base.setSize(baseWidth,baseHeight);
    base.setLocation((panelWidth - baseWidth)/2 , panelHeight-baseHeight);
    base.setBackground(Color.BLACK);
    add(base);
    
    //타겟
    add(target);
    thTarget = new TargetThread(target);
    
    //총알
    add(bullet);
    thBullet = new BulletThread(bullet, target , thTarget, successLabel);
    
    //성공 레이블
    add(successLabel);
    
    //마우스 클릭 이벤트 등록.
    addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent e){
        //불릿 스레드 시작
        if(thBullet.getState() == Thread.State.NEW)
          thBullet.start();
        
        //불릿 스레드 재개
        else if(thBullet.getState() == Thread.State.WAITING){
          thBullet.restart();
        }
        
        //타깃 스레드 재개
        if(thTarget.getState() == Thread.State.WAITING)
          thTarget.restart();
      }
    });
  }
  
  //배경화면 등록.
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
  }
}