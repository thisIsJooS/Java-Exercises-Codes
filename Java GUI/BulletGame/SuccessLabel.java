package BulletGame;

import java.awt.*;
import javax.swing.*;

public class SuccessLabel extends JLabel{
  public SuccessLabel(){
    super("Success!!!");
    setFont(new Font("Gothic", Font.ITALIC, 80));
    setForeground(Color.RED);
    setSize((int)(GamePanel.panelWidth * 0.8), (int)(GamePanel.panelHeight * 0.1));
    setLocation((int)(GamePanel.panelWidth * 0.15), (int)(GamePanel.panelHeight * 0.4));
    setVisible(false);
  }
}
