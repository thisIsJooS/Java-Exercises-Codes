//명품 Java Programming ch07 Q09 438p
import java.util.Vector;

interface IStack<T>{
    T pop();
    boolean push(T ob);
}

class MyStack<T> implements IStack<T>{
    Vector<T> v = null;
    public MyStack(){
        v = new Vector<T>();
    }
    
    @Override
    public T pop(){
        if(v.size() == 0) return null;
        else return v.remove(0);
    }
    
    @Override
    public boolean push(T ob){
        v.add(0, ob);
        return true;
    }
    
}

public class StackManager{
    public static void main(String[] args){
        IStack<Integer> stack = new MyStack<Integer>();
        for(int i=0; i<10; i++) stack.push(i); //10개의 정수 push
        while(true){ //스택이 빌 때까지 pop
            Integer n = stack.pop();
            if(n==null) break;
            System.out.print(n + " ");
        }
    }
}