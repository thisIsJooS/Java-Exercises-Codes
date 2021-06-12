//명품 Java Programming ch08 Q08 487p

import java.io.*;

public class SearchBiggestFile{
    static File biggestFile = null;
    
    public static void main(String[] args) {
        File f = new File("/workspace/java/src");
        
        setFirstFile(f);
        run(f);
        
        System.out.println("가장 큰 파일은" + biggestFile.getName() + "\t" + biggestFile.length() + "바이트");
    }
    
    private static void run(File file){
        File [] list = file.listFiles();
        
        for(File k : list){
            if(k.isFile()){
                System.out.println(k.getName() + "파일 검사중...");
                if(k.length() > biggestFile.length()){
                    biggestFile = k;
                }
            }
            else if(k.isDirectory()){
                System.out.println(k.getName() + "디렉토리 검사중...----------------");
                run(k);
            }
        }
    }
    
    private static void setFirstFile(File file){
        File [] list = file.listFiles();
        
        for(File k: list){
            if(k.isFile()){
                biggestFile = k;
                break;
            }
            else if(k.isDirectory())
                setFirstFile(k);
        }
    }
}
