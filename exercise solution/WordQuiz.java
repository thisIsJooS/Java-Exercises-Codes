//명품 Java Programming. Open Challenge 430p
import java.util.Scanner;
import java.util.Vector;
import java.util.InputMismatchException;

class Word{
    private String eng;
    private String kor;
    public Word(String e, String k){
        this.eng = e; this.kor = k;
    }
    protected String getEng(){
        return this.eng;
    }
    protected String getKor(){
        return this.kor;
    }
}

public class WordQuiz{
    private Scanner scanner;
    private Vector<Word> v;
    private int myAnswer;
    private int answer;
    private int qIndex;
    
    public WordQuiz(){
        scanner = new Scanner(System.in);
        v = new Vector<Word>();
        v.add(new Word("love", "사랑"));
        v.add(new Word("animal", "동물"));
        v.add(new Word("emotion", "감정"));
        v.add(new Word("human", "사람"));
        v.add(new Word("stock", "주식"));
        v.add(new Word("trade", "교환"));
        v.add(new Word("society", "사회"));
        v.add(new Word("baby", "아기"));
        v.add(new Word("honey", "꿀"));
        v.add(new Word("painting", "그림"));
        v.add(new Word("bear", "곰"));
        v.add(new Word("picture", "사진"));
        v.add(new Word("example", "예시"));
        v.add(new Word("statue", "조각상"));
    }
    
    private void run(){
        System.out.println("\"명품영어\"의 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
        System.out.println("현재 " + v.size() + "개의 단어가 들어있습니다.");
        while(true){
            question();
            makeOption(qIndex);
            while(true){
                try{
                    myAnswer = scanner.nextInt();
                    break;
                }
                catch (InputMismatchException e){
                    System.out.print("정수를 입력해주세요. : >");
                    scanner.nextLine();
                }
            }
            if(myAnswer == -1){
                System.out.println("\"명품영어\"를 종료합니다...");
                break;
            }
            checkAnswer(myAnswer);
        }
    }
    private void question(){
        qIndex = (int)(Math.random()*v.size());
        Word qWord = v.get(qIndex);
        System.out.println(qWord.getEng() + "?");
    }
    private void checkAnswer(int myAnswer){        
        if(answer == myAnswer){
            System.out.println("Excellent !!");
        }
        else
            System.out.println("No. !!");
    }
    private void makeOption(int idx){ //보기를 만들어 정답을 리턴함.
        int [] option = {-1,-1,-1,-1};
        int r;
        for(int k=0; k<option.length; k++){
            do{
                r = (int)(Math.random()*v.size());
            }while(exists(option, r) || (r == idx));
            option[k] = r;
        }
        r = (int)(Math.random()*4);
        option[r] = idx;
        answer = r+1;
        
        for(int k=0 ; k<option.length; k++){
            System.out.print("(" + (k+1) +  ") " + v.get(option[k]).getKor() + " ");
        }
        System.out.print(": > ");
        
    }
    private boolean exists(int [] n, int idx){  //매개변수로 들어온 n 배열에 idx가 있다면 true를 리턴. 없으면 flase를 리턴
        for(int k : n){
            if(k == idx) return true;
        }
        return false;
    }
    public static void main(String[] args){
        WordQuiz quiz = new WordQuiz();
        quiz.run();
    }
}