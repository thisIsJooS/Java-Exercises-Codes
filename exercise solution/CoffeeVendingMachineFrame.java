import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;

public class CoffeeVendingMachineFrame extends JFrame{    
  protected final static int FRAME_WIDTH = 800;
  protected final static int FRAME_HEIGHT = 700;
  private CenterPanel centerPanel;
  
  public CoffeeVendingMachineFrame(){
    setTitle("Open Challenge 14");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    
    //North
    c.add(new NorthPanel(), BorderLayout.NORTH);
    
    //Center
    centerPanel  = new CenterPanel();
    c.add(centerPanel, BorderLayout.CENTER);
    
    //South
    c.add(new SouthPanel(centerPanel.getIngredientLabel(), centerPanel.getCoffeeImageLabel()), BorderLayout.SOUTH);
    
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setVisible(true);
  }

  public static void main(String[] args){
    new CoffeeVendingMachineFrame();
  }
}

//재료 레이블. 5개클래스가 상속할 예정
abstract class IngredientLabel extends JLabel{
  protected static final int INGRDNT_LABEL_WIDTH = 100;
  protected static final int INGRDNT_LABEL_HEIGHT = 250;
  int consumed = 0;
  
  public IngredientLabel(){
    setPreferredSize(new Dimension(INGRDNT_LABEL_WIDTH, INGRDNT_LABEL_HEIGHT)); //레이블의 너비 높이 지정
    setOpaque(true); //배경색 지정하기 위해.
    setBackground(Color.WHITE); 
    setBorder(new BevelBorder(BevelBorder.RAISED));
  }  

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g); 
    g.fillRect(0, consumed, INGRDNT_LABEL_WIDTH, INGRDNT_LABEL_HEIGHT - consumed );
  }

  abstract public void consume();

  abstract public void reset();
  
  abstract public boolean isEmpty();
}

//North panel
class NorthPanel extends JLabel{
  private static final int NORTHPANEL_HEIGHT = 30;

  public NorthPanel(){
    super("Welcome. Hot Coffee!!", SwingConstants.CENTER);
    setFont(new Font("Arial", Font.BOLD, 20));
    setPreferredSize(new Dimension(CoffeeVendingMachineFrame.FRAME_WIDTH, NORTHPANEL_HEIGHT));
    setOpaque(true);
    setBackground(Color.MAGENTA);
  }
}

//Center Panel
class CenterPanel extends JPanel{
  private static final int CENTERPANEL_HEIGHT = 600;
  private IngredientLabel [] ingredientLabel = new IngredientLabel[5];
  private String [] IngredientTitle = {"Cup", "Coffee", "Water", "Sugar", "Cream"};
  private ImageIcon icon = new ImageIcon("/workspace/GUI/src/images/coffee.jpg");
  private Image img = icon.getImage();
  private Image newImg =  img.getScaledInstance(160, 100, Image.SCALE_SMOOTH);
  private ImageIcon newIcon = new ImageIcon(newImg);
  private JLabel coffeeImageLabel = new JLabel(newIcon);

  public CenterPanel(){
    setLayout(null);
    setPreferredSize(new Dimension(CoffeeVendingMachineFrame.FRAME_WIDTH, CENTERPANEL_HEIGHT));

    ingredientLabel[0] = new CupLabel();
    ingredientLabel[1] = new CoffeeLabel();
    ingredientLabel[2] = new WaterLabel();
    ingredientLabel[3] = new SugarLabel();
    ingredientLabel[4] = new CreamLabel();


    for(int i=0; i<ingredientLabel.length; i++){
      ingredientLabel[i].setLocation(30 + (160*i), 10);
      ingredientLabel[i].setSize(IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT);
      ingredientLabel[i].setVisible(true);
      add(ingredientLabel[i]);
    }

    coffeeImageLabel.setLocation(320, 400);
    coffeeImageLabel.setSize(160,100);
    coffeeImageLabel.setVisible(false);
    add(coffeeImageLabel);

    setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setFont(new Font("Arial", Font.BOLD, 18));
    for(int i =0; i < IngredientTitle.length; i++){
      g.drawString(IngredientTitle[i], 60+(160*i), 290);
    }
  }
  
  public IngredientLabel [] getIngredientLabel(){
    return ingredientLabel;
  }
  
  public JLabel getCoffeeImageLabel(){
    return coffeeImageLabel;
  }
}

//South panel
class SouthPanel extends JPanel{
  private static final int SOUTHPANEL_HEIGHT = 50;
  private JButton [] btn = new JButton[4];
  private String [] btnTitle = {"Black Coffee", "Sugar Coffee", "Dabang Coffee", "Refill"};
  private IngredientLabel [] ingredientLabel;
  private JLabel coffeeImage;

  public SouthPanel(IngredientLabel [] ingredientLabel, JLabel coffeeImage){
    this.ingredientLabel = ingredientLabel;
    this.coffeeImage = coffeeImage;

    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
    setPreferredSize(new Dimension(CoffeeVendingMachineFrame.FRAME_WIDTH, SOUTHPANEL_HEIGHT));
    setBackground(Color.DARK_GRAY);

    for(int i=0; i<btn.length; i++){
      btn[i] = new JButton(btnTitle[i]);
      btn[i].addActionListener(new MyActionListener(btn[i] , ingredientLabel, coffeeImage));
      btn[i].setFont(new Font("Arial", Font.BOLD, 14));
      btn[i].setPreferredSize(new Dimension(180,40));

      add(btn[i]);
    }
    setVisible(true);
  }
}

//커피 종류마다의 버튼의 ActionListener
class MyActionListener implements ActionListener{
  private JButton btn;
  private IngredientLabel [] ingredientLabel;
  private JLabel coffeeImage; 

  public MyActionListener(JButton btn , IngredientLabel [] ingredientLabel, JLabel coffeeImage){
    this.btn = btn;
    this.ingredientLabel = ingredientLabel;
    this.coffeeImage = coffeeImage;
  }

  public boolean checkEmpty(){
    boolean b;
    for(int i=0; i<ingredientLabel.length; i++){
      b = ingredientLabel[i].isEmpty();
      if(b == true)
        return true;
    }
    return false;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    String text = btn.getText();

    switch(text){
      case "Black Coffee":
        ingredientLabel[0].consume();
        ingredientLabel[1].consume();
        ingredientLabel[2].consume();

        if(checkEmpty()){
          JOptionPane.showMessageDialog(null, "부족한 것이 있습니다. 채워주세요.", "커피 자판기 경고", JOptionPane.INFORMATION_MESSAGE);
          break;
        }

        showImage(coffeeImage);
        JOptionPane.showMessageDialog(null, "뜨거워요! 즐거운 하루!", "커피 나왔습니다.", JOptionPane.INFORMATION_MESSAGE);
        hideImage(coffeeImage);
        break;

      case "Sugar Coffee":
        ingredientLabel[0].consume();
        ingredientLabel[1].consume();
        ingredientLabel[2].consume();
        ingredientLabel[3].consume();

        if(checkEmpty()){
          JOptionPane.showMessageDialog(null, "부족한 것이 있습니다. 채워주세요.", "커피 자판기 경고", JOptionPane.INFORMATION_MESSAGE);
          break;
        }

        showImage(coffeeImage);
        JOptionPane.showMessageDialog(null, "뜨거워요! 즐거운 하루!", "커피 나왔습니다.", JOptionPane.INFORMATION_MESSAGE);
        hideImage(coffeeImage);
        break;

      case "Dabang Coffee":
        ingredientLabel[0].consume();
        ingredientLabel[1].consume();
        ingredientLabel[2].consume();
        ingredientLabel[3].consume();
        ingredientLabel[4].consume();

        if(checkEmpty()){
          JOptionPane.showMessageDialog(null, "부족한 것이 있습니다. 채워주세요.", "커피 자판기 경고", JOptionPane.INFORMATION_MESSAGE);
          break;
        }

        showImage(coffeeImage);
        JOptionPane.showMessageDialog(null, "뜨거워요! 즐거운 하루!", "커피 나왔습니다.", JOptionPane.INFORMATION_MESSAGE);
        hideImage(coffeeImage);
        break;

      case "Refill":
        for(int i=0; i<ingredientLabel.length; i++){
          ingredientLabel[i].reset();
        }
        JOptionPane.showMessageDialog(null, "재료가 리필되었습니다!", "리필되었습니다.", JOptionPane.INFORMATION_MESSAGE);
        break;
    }
  }

  public void showImage(JLabel coffeeImage){
    coffeeImage.setVisible(true);
  }

  public void hideImage(JLabel coffeeImage){
    coffeeImage.setVisible(false);
  }

}

//Cup Label
class CupLabel extends IngredientLabel{
  int consumed = 0;

  @Override
  public void paintComponent(Graphics g){
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, consumed, IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT - consumed );
  }

  @Override
  public void consume(){
    consumed += 1;
    repaint();
  }

  @Override
  public boolean isEmpty(){
    if(consumed >= INGRDNT_LABEL_HEIGHT)
      return true;
    else return false;
  }

  @Override
  public void reset(){
      consumed = 0;
      repaint();
  }
}

//Coffee Label
class CoffeeLabel extends IngredientLabel{
  int consumed = 0;

  @Override
  public void paintComponent(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(0, consumed, IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT - consumed );
  }

  @Override
  public void consume(){
    consumed += 10;
    repaint();
  }

  @Override
  public boolean isEmpty(){
    if(consumed >= INGRDNT_LABEL_HEIGHT)
      return true;
    else return false;
  }

  @Override
  public void reset(){
      consumed = 0;
      repaint();
  }
}

 //Water Label
class WaterLabel extends IngredientLabel{
  int consumed = 0;

  @Override
  public void paintComponent(Graphics g){
    g.setColor(new Color(25, 135, 184));
    g.fillRect(0, consumed, IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT - consumed );
  }

  @Override
  public void consume(){
    consumed += 20;
    repaint();
  }

  @Override
  public boolean isEmpty(){
    if(consumed >= INGRDNT_LABEL_HEIGHT)
      return true;
    else return false;
  }

  @Override
  public void reset(){
      consumed = 0;
      repaint();
  }
}

 //Sugar Label
class SugarLabel extends IngredientLabel{
  int consumed = 0;

  @Override
  public void paintComponent(Graphics g){
    g.setColor(new Color(247,182,90));
    g.fillRect(0, consumed, IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT - consumed );
  }

  @Override
  public void consume(){
    consumed += 20;
    repaint();
  }

  @Override
  public boolean isEmpty(){
    if(consumed >= INGRDNT_LABEL_HEIGHT)
      return true;
    else return false;
  }

  @Override
  public void reset(){
      consumed = 0;
      repaint();
  }
}

//Cream Label
class CreamLabel extends IngredientLabel{
  int consumed = 0;

  @Override
  public void paintComponent(Graphics g){
    g.setColor(new Color(162,225,234));
    g.fillRect(0, consumed, IngredientLabel.INGRDNT_LABEL_WIDTH, IngredientLabel.INGRDNT_LABEL_HEIGHT - consumed );
  }

  @Override
  public void consume(){
    consumed += 30;
    repaint();
  }

  @Override
  public boolean isEmpty(){
    if(consumed >= INGRDNT_LABEL_HEIGHT)
      return true;
    else return false;
  }

  @Override
  public void reset(){
      consumed = 0;
      repaint();
  }
}
