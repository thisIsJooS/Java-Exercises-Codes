import java.util.Scanner;

abstract class GameObject{ //추상 클래스
    protected int distance; //한번 이동거리
    protected int x,y; //현재 위치 (화면 맵 상의 위치)
    public GameObject(int startX, int startY, int distance){ //초기 위치와 이동거리 설정
        this.x = startX;
        this.y = startY;
        this.distance = distance;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public boolean collide(GameObject p){ //이 객체가 객체 p와 충동했으면 true 리턴
        if(this.x == p.getX() && this.y == p.getY()) 
            return true;
        else
            return false;
    }
    protected abstract void move(); //이동한 후의 새로운 위치로 x,y변경
    protected abstract char getShape(); //객체의 모양을 나타내는 문자 리턴
}
class Bear extends GameObject{
    private Scanner scanner;
    public Bear(int x, int y, int distance){
        super(x,y,distance);
        scanner = new Scanner(System.in);
    }
    @Override
    public void move(){
        System.out.println("왼쪽(a), 아래(s), 위(w), 오른쪽(d) >> ");
        char c = scanner.nextLine().charAt(0);
        switch(c){
            case 'a':
                if (this.getY() != 0){
                    this.y--;
                    this.distance++;
                }    
                break;
            case 's':
                if (this.getX() != (BearFishGame.MAX_X-1)){
                    this.x++;
                    this.distance++;
                }
                break;
            case 'w':
                if(this.getX() != 0){
                    this.x--;
                    this.distance++;
                }
                break;
            case 'd':
                if(this.getY() != (BearFishGame.MAX_Y-1)){
                    this.y++;
                    this.distance++;
                }
                break;
            default:
                System.out.println("a,s,w,d 중 하나만 입력하세요.");
        } 
    }
    @Override
    public char getShape(){
        return 'B';
    }
}
class Fish extends GameObject{
    public Fish(int x, int y, int distance){
        super(x,y,distance);
    }
    @Override
    public void move(){
        if((this.distance%3==0) || (this.distance%4==0)){
            int r = (int)(Math.random()*4);
            switch(r){
                case 0:
                    if(this.getX() != 0){
                        this.x--;
                    }    
                    break;
                case 1:
                    if(this.getX() != (BearFishGame.MAX_X-1)){
                        this.x++;
                    } 
                    break;
                case 2:
                    if(this.getY() != 0){
                        this.y--;
                    } 
                    break;
                case 3:
                    if(this.getY() !=  (BearFishGame.MAX_Y-1)){
                        this.y++;
                    } 
                    break;
            }
        }
    }
    @Override
    public char getShape(){
        return '@';
    }
}

public class BearFishGame{
    public static final int MAX_X = 10;
	public static final int MAX_Y = 20;
    private char board [][] = new char[MAX_X][MAX_Y];
    private GameObject[] m = new GameObject[2];
  
    public BearFishGame(){
        for (int i=0; i<board.length; i++)
            for(int j=0; j<board[i].length; j++)
                board[i][j] = '-';
        m[0] = new Bear(0,0,1);
        m[1] = new Fish((int)(Math.random()*10), (int)(Math.random()*20), 0);
    }
    
    private void update(){
        for(int i = m.length-1; i>=0; i--) //Fish부터 먼저 그려서 Fish를 먹는 경우 Fish가 보이지 않기
            board[m[i].getX()][m[i].getY()] = m[i].getShape();
    }
    private void clear(){
        for(int i=0; i<m.length; i++)
            board[m[i].getX()][m[i].getY()]='-';
    }
    private void draw(){
        System.out.println();
        for(int i=0; i<MAX_X; i++){
            for(int j=0; j<MAX_Y; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
    
    
    public void run(){
        System.out.println("** Bear의 Fish 먹기 게임을 시작합니다.**");;
        
        update();
        draw();
        
        while(true){
            if(!m[0].collide(m[1])){
                clear();
                for(int i=0; i<m.length; i++)
                    m[i].move();
                update();
                draw();
            }
            else{
                System.out.println("Bear Wins!!");
                break;
            }
            
        }
    }
        
    public static void main(String[] args){
        BearFishGame game = new BearFishGame();
        game.run();
    }
}