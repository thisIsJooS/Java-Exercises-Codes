package BulletGame;


public class BulletThread extends Thread{
  private Bullet bullet;
  private Target target;
  private TargetThread thTarget;
  private int targetWidth, targetHeight;
  private int bulletWidth, bulletHeight;
  private int bulletSpeed = 5; //작을수록 빠름
  private SuccessLabel successLabel;
  
  //불릿 스레드 생성자
  public BulletThread(Bullet bullet, Target target, TargetThread thTarget, SuccessLabel successLabel ){
    this.bullet = bullet;
    this.target = target;
    this.thTarget = thTarget;
    this.successLabel = successLabel;
    
    targetWidth = target.getWidth();
    targetHeight = target.getHeight();
    bulletWidth = bullet.getWidth();
    bulletHeight = bullet.getHeight();
  }
  
  //스레드 정지
  synchronized public void pause(){
    try{
      wait();
    }catch(InterruptedException e){return;}
  }
  
  //스레드 재개
  synchronized public void restart(){
    notify();
  }
  
  @Override
  public void run(){
    int y = GamePanel.panelHeight - GamePanel.baseHeight - bulletHeight;
    while(true){
      bullet.setLocation((GamePanel.panelWidth - bulletWidth)/2, y);
        
      //명중시  코드
      if(y <= targetHeight){
        int xBullet = bullet.getX();
        int xTarget = target.getX();
        
        if( (xTarget>=(xBullet-targetWidth)) && (xTarget<=xBullet+bulletWidth) ){
          successLabel.setVisible(true);  //성공레이블 표시
          thTarget.interrupt();  // 타깃스레드에서 interrupt당했을때 정지하도록 처리했음
          pause();  //불릿스레드 정지
          
          //마우스 클릭 이벤트가 일어나 재개 되었을 경우 이어지는 코드. y좌표 초기화.
          y = GamePanel.panelHeight - GamePanel.baseHeight - bulletHeight;
          
          //성공레이블 숨기기
          successLabel.setVisible(false);
          
          //불릿  원위치
          bullet.setLocation((GamePanel.panelWidth - bulletWidth)/2, y);
          
          //다시 마우스 클릭 이벤트를 기다린다.
          pause();
          
          //마우스 클릭 이벤트가 일어나 스레드가 재개 되었을 경우 while 루프 처음부터 시작.
          continue;
        }
       }
      
      //명중에 실패하였을경우 불릿 원위치 시키고 대기.
      if(y == -20){
        y=GamePanel.panelHeight - GamePanel.baseHeight - bulletHeight;
        bullet.setLocation((GamePanel.panelWidth - bulletWidth)/2,y);
        pause();
      }
      
      //불릿을 5px씩 올린다. 
      y -= 5;
      
      try{
        sleep(bulletSpeed);
        
      }catch(InterruptedException e){return;}
    }
  }
}