//명품 Java Programming Ch07 Q05 436p - 1
import java.util.Scanner;
import java.util.ArrayList;

class Student{
    private String name;
    private String major;
    private String studentNumber;
    private String gpa;
    
    public Student(String [] arr){
        this.name = arr[0];
        this.major = arr[1];
        this.studentNumber = arr[2];
        this.gpa = arr[3];
    }
    
    @Override
    public String toString(){
            return this.name + ", " + this.major + ", " + this.studentNumber + ", " + this.gpa;
    }
    protected String getName(){ return this.name;}
    protected String getMajor(){ return this.major;}
    protected String getStudentNumber(){ return this.studentNumber;}
    protected String getGPA(){ return this.gpa;}
}


public class StudentManager{
    private Scanner scanner;
    private ArrayList<Student> student;
    
    public StudentManager(){
        scanner = new Scanner(System.in);
        student = new ArrayList<Student>();
    }
   
    public void readInfo(){
        String info;
        String [] infoArr = new String[4];
        System.out.println("학생 이름, 학과, 학번, 학점평균을 입력하세요.(-1 을 입력하면 학생 입력 끝.)");
        while(true){
            System.out.print(">> ");
            info = scanner.nextLine();
            if(!checkFormat(info)){
                System.out.println("맞지 않는 형식입니다.");
                continue;
            }
            infoArr = resetInfoArray(infoArr);
            
            if(info.equals("-1")){
                System.out.println("----------------------------------------------");
                break;
            }
            
            infoArr = info.split(",");
            for(int i=0; i<infoArr.length; i++)
                infoArr[i] = infoArr[i].trim();
            student.add(new Student(infoArr));
        }
    }
    public void printInfo(){
        for(int i=0; i<student.size(); i++){
            System.out.println("이름 : " +  student.get(i).getName());
            System.out.println("학과 : " +  student.get(i).getMajor());
            System.out.println("학번 : " +  student.get(i).getStudentNumber());
            System.out.println("학점평균 : " +  student.get(i).getGPA());
            System.out.println("----------------------------------------------");
        }
    }
    public void searchInfo(){
        String name;
        boolean exists;
        while(true){
            exists = false;
            System.out.print("학생 이름 >> ");
            name = scanner.nextLine();
            if(name.equals("-1")) break;
            for(int i=0; i<student.size(); i++){
                if(student.get(i).getName().equals(name)){
                    System.out.println(student.get(i).toString());
                    exists = true;
                    break;
                }
            }
            if(exists == false) System.out.println("존재하지 않는 이름입니다.");
        }
    }
   
    public boolean checkFormat(String str){    //4개의 정보를 전부 입력하지 않았을 경우를 검사.
        if(str.equals("-1")) return true;
        String [] arr = str.split(",");
        if(arr.length == 4) return true;
        else return false;
    }
    
    public String[] resetInfoArray(String[] arr){
        for(int i=0; i<arr.length; i++)
            arr[i] = null;
        return arr;
    }
    public void run(){
        readInfo();
        printInfo();
        searchInfo();
    }
    public static void main(String [] args){
        StudentManager studentManager = new StudentManager();
        studentManager.run();
    }
}