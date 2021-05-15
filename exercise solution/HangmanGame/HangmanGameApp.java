//명품 Java Programming Open Challenge 480p
package Solution;

import java.util.*;
import java.io.*;

public class HangmanGameApp{
    private Scanner scanner;
    private BufferedReader br;
    private static Vector<String> v; 
    
    public HangmanGameApp(){
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args){
        HangmanGameApp game = new HangmanGameApp();
        game.run();
    }
    
    private void run(){
        v = makeVector();
        System.out.println("지금부터 행맨 게임을 시작합니다.");
        
        while(true){
            String word = chooseWord();
            gameProcess(word);
            System.out.print("Next (y/n)? ");
            String s = scanner.next();
            scanner.nextLine();    //버퍼 처리
            if(s.equals("n"))
                break;
        }
    }
    
  
    private String chooseWord(){    //매개변수로 들어온 벡터로부터 랜덤하게 단어를 한 개 선택하여 리턴한다.
        int r = (int)(Math.random() * v.size());
        String temp = v.get(r);
        return temp;
    }
    
    private Vector<String> makeVector(){        //words.txt로부터 불러온 단어들을 벡터에 저장을 한다.
        Vector<String> vec = new Vector<>();  
        String tmp;
        try{
            File f = new File("/workspace/java/src/words.txt");
            br = new BufferedReader(new FileReader(f));
            
            while((tmp = br.readLine()) != null){
                vec.add(tmp);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return vec;
    }
    
    private void gameProcess(String word){    
        int [] hyphenIndex = {-1, -1};            //글자를 숨길 곳의 인덱스를 저장하는 배열이다.
        
        for(int i=0; i<hyphenIndex.length; i++)    //랜덤으로 인덱스를 정한다.
            hyphenIndex[i] = (int)(Math.random()*word.length());
        
        while(true){
            if(hyphenIndex[0] == hyphenIndex[1])
                hyphenIndex[1] = (int)(Math.random()*word.length());
            else break;
        }    
        
        String query = String.valueOf(word);
        char [] q = new char[query.length()];
        for(int i=0; i<q.length; i++)
            q[i] = query.charAt(i);  
        
        for(int i=0; i<hyphenIndex.length; i++)        //두 곳의 글자를 하이픈으로 변환하여 숨긴다.
            q[hyphenIndex[i]] = '-';
        
        int tryCnt=0, successCnt=0, failCnt=0;
        char c;
        boolean success;
        while(true){
            success = false;
            if(tryCnt==5){
                System.out.println("5번 실패 하였습니다.");
                System.out.println(query);
                break;
            }
            System.out.print(String.valueOf(q));
            System.out.printf("\t || 시도 : %d, 성공 : %d, 실패 : %d\n", tryCnt, successCnt, failCnt);
            System.out.print(">> ");
            c = scanner.next().charAt(0);
            tryCnt++;
            
            for(int i=0; i<hyphenIndex.length; i++){
                if(c == query.charAt(hyphenIndex[i])){
                    q[hyphenIndex[i]] = c;
                    success = true;
                }
            }
            if(success == true) successCnt++;
            else failCnt++;
            
            if(String.valueOf(q).indexOf("-") < 0){
                System.out.print(String.valueOf(q));
                System.out.println(" 정답입니다!!");
                break;
            }
        }
    
    }
}