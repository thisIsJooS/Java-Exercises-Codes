//명품 Java Programming ch08 Q07 486p

package Solution.ImageCopy;
import java.io.*;

public class ImageCopy{
    public static void main(String[] args){  
        BufferedInputStream srcStream = null;
		BufferedOutputStream destStream = null;
        
        File srcFile = new File("/workspace/java/src/Solution/ImageCopy/a.jpg");
        File destFile = new File("/workspace/java/src/Solution/ImageCopy/b.jpg");
        
        try{
            srcStream = new BufferedInputStream(new FileInputStream(srcFile));
            destStream = new BufferedOutputStream(new FileOutputStream(destFile));
            
            long tenPercent = srcFile.length() /10;
            long progress = 0;
            
            System.out.println("a.jpg를 b.jpg로 복사합니다.");
            System.out.println("10%마다 *를 출력합니다.");
            
            byte[] buf = new byte[1024];
            int numRead =0;
            
            while(true){
                numRead = srcStream.read(buf, 0, buf.length); //읽은 바이트의 개수
                if(numRead == -1){
                    if(progress > 0 ){
                        System.out.print("*");
                    }
                    break;
                }
                if(numRead >0)
                    destStream.write(buf, 0, numRead);
                
                progress += numRead; //progress 변수에 읽은 바이트의 개수를 더한다.
                if(progress >= tenPercent){
                    System.out.print("*");
                    progress=0;
                }
            }
            srcStream.close();
            destStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}