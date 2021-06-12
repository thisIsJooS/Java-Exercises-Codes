import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;

public class TextFieldLimit extends JFrame{    
  private Container contentPane;
  
  public TextFieldLimit(){
    contentPane = getContentPane();
    createToolBar();
  
    setSize(500,500);
    setVisible(true);
  }
  
  public void createToolBar(){
    JToolBar toolBar = new JToolBar("ToolBar");
    toolBar.setBackground(Color.YELLOW);
    
    JLabel label = new JLabel("학번");
    
    JTextField tf = new JTextField(20);
    tf.setDocument(new JTextFieldLimit(8));
    tf.addKeyListener(new KeyAdapter(){
      @Override
      public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        if(!Character.isDigit(c)){
          if(c != KeyEvent.VK_BACK_SPACE)
            JOptionPane.showMessageDialog(null, "숫자만 입력하세요!", "Message", JOptionPane.WARNING_MESSAGE);
          e.consume();
          tf.setFocusable(true);
          tf.requestFocus();
        }
      }
    });

    
    toolBar.add(label);
    toolBar.add(tf);
    contentPane.add(toolBar, BorderLayout.NORTH);
  }
  
  public static void main(String[] args){
    new TextFieldLimit();
  }
}

class JTextFieldLimit extends PlainDocument{
  private int limit;
  public JTextFieldLimit(int limit){
    super();
    this.limit = limit;
  }
  
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
    if(str == null) return;
    if(getLength() + str.length() <= limit)
      super.insertString(offset, str, attr);
  }
}


