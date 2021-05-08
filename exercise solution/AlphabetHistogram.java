import java.util.Scanner;

public class AlphabetHistogram{
    private Scanner scanner;
    private int alphabet [] = new int [26];
    
    public AlphabetHistogram(){
        scanner = new Scanner(System.in);
        for(int k : alphabet) alphabet[k] = 0;
    }
    
    private void draw(){    //히스토그램을 출력하는 함수.
        System.out.println("히스토그램을 그립니다.");
        for(int i=0; i<alphabet.length; i++){
            System.out.print((char)(i+'A'));
            for(int j=0; j<alphabet[i]; j++)
                System.out.print("-");
            System.out.println();
        }
    }
    
    private void countAlphabet(String text){        //알파벳이 몇번 나왔는지 세는 함수.
        text = text.toUpperCase();
        for(int i=0; i<text.length(); i++){
            char c = text.charAt(i);
            if(c >= 'A' && c <= 'Z')
                alphabet[c-'A']++;
        }
    }
    
    private String scanString(){
        System.out.println("영문 텍스트를 입력하고 세미콜론을 입력하세요.");
        StringBuffer sb = new StringBuffer(); //키 입력을 받을 스트링버퍼 생성
        while(true){
            String line = scanner.nextLine(); //텍스트 한 라인을 읽는다.
            if(line.equals(";")) break;
            sb.append(line); //읽은 라인 문자열을 스트링 버퍼에 추가한다. 
        }
        return sb.toString(); //스트링버퍼의 문자열을 스트링으로 리턴
    }
    
    public void run(){
        String text =  scanString();
        countAlphabet(text);
        draw();
    }
    
    public static void main(String[] args){
        AlphabetHistogram alphabetHistogram = new AlphabetHistogram();
        alphabetHistogram.run();
    }
}