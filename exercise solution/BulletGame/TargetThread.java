package BulletGame;


public class TargetThread extends Thread{
  private Target target;
  private int targetSpeed = 20; //작을수록 빠름
  
  //타깃스레드가 생성되자마자 start하도록 설정.
  public TargetThread(Target target){
    this.target = target;
    start();
  }
  
  //스레드 일시정지
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
    int x = 0;
    while(true){  
      target.setLocation(x , 0);
      
      //타깃이 좌측 끝까지 갔으면 다시 가장 우측으로
      if(x == GamePanel.panelWidth){
        x = 0;
        continue;
      }
      
      //타깃을 5px씩 이동시킴
      x += 5;
      
      try{
        sleep(targetSpeed);
      }catch(InterruptedException e){
        pause(); //불릿 스레드에서 interrupt가 발생하였을떄 스레드를 일시정지만 시킨다. 
      }
    }
    
  }
}