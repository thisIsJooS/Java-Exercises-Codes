//명품 Java Programming Ch08 Q05 486p
package Solution.CompareFiles;

import java.io.*;

public class CompareFiles{
    public static void main(String[] args){
        FileInputStream fin1 = null;
        FileInputStream fin2 = null;
        FileInputStream fin3 = null;
        try{
            fin1 = new FileInputStream("/workspace/java/src/Solution/CompareFiles/elvis1.txt");
            fin2 = new FileInputStream("/workspace/java/src/Solution/CompareFiles/elvis1-copied.txt");
            fin3 = new FileInputStream("/workspace/java/src/Solution/CompareFiles/elvis2.txt");
    
            if(compareFile(fin1, fin2))
                System.out.println("elvis1.txt 과 elvis1-copied.txt는 같은 파일입니다.");
            else
                System.out.println("elvis1.txt 과 elvis1-copied.txt는 다른 파일입니다.");
            
            if(compareFile(fin1, fin3))
                System.out.println("elvis1.txt 과 elvis2.txt는 같은 파일입니다.");
            else
                System.out.println("elvis1.txt 과 elvis2.txt는 다른 파일입니다.");
            
            fin1.close();
            fin2.close();
            fin3.close();
        }catch(IOException e){
            System.out.println("오류입니다.");
        }
    }
    
    private static boolean compareFile(FileInputStream src, FileInputStream dest) throws IOException{
        byte srcBuf [] = new byte[1024];
        byte destBuf [] = new byte[1024];
        
        int srcCount =0, destCount=0;
        
        while(true){
            srcCount = src.read(srcBuf, 0, srcBuf.length);
            destCount = dest.read(destBuf, 0, destBuf.length);
            
            if(srcCount != destCount)
                return false;
            if(srcCount == -1)
                break;
            for(int i=0; i<srcCount; i++){
                if(srcBuf[i] != destBuf[i])
                    return false;
            }
        }
        return true;
    }
}