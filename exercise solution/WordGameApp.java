import java.util.Scanner;

public class WordGameApp{
    private Scanner scanner;
    private Player player [];
    private int num;
    
    public WordGameApp(){
        scanner = new Scanner(System.in);
    }
    
    public void createPlayer(){
        System.out.print("게임에 참가하는 인원은 몇명입니까 >>");
        num = scanner.nextInt();
        scanner.nextLine();
        player = new Player[num];
        for (int i=0; i<num; i++){
            System.out.print("참가자의 이름을 입력하세요 >> ");
            String name = scanner.nextLine();
            player[i] = new Player(name);
        }
    }
    
    public void run(){
        int n=0;
        
        System.out.println("끝말잇기 게임을 시작합니다...");
        createPlayer();
        System.out.println("시작하는 단어는 아버지입니다");
        
        String startWord = "아버지";
        
        while(true){
            if(n == num) n=0;
            System.out.print(player[n].getName() + ">>");
            String nextWord = player[n].getWordFromUser();
            
            int lastIndex = startWord.length()-1;
            char lastChar = startWord.charAt(lastIndex);
            char firstChar = nextWord.charAt(0);
            
            if(!player[n].checkSuccess(firstChar, lastChar)){
                System.out.println(player[n].getName() + "(이)가 졌습니다.");
                break;
            }
            
            startWord = nextWord;
            n++;
        }
    }
    public static void main(String[] args){
        WordGameApp game = new WordGameApp();
        game.run();
    }
}
class Player{
    Scanner scanner = new Scanner(System.in);
    
    private String name;
    private String word;

    public Player(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getWordFromUser(){
        word = scanner.next();
        return word;
    }
    public boolean checkSuccess(char a, char b){
        if(a==b) return true;
        else return false;
    }
}
