//명품 Java Programming ch11 Q08 637p
//Image Gallery

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class ImageGallery extends JFrame{
  private File f = new File("/workspace/GUI/src/images");
  private File [] fl = f.listFiles();
  private Vector<ImageIcon> v = new Vector<>();
  private int num=0;
  private JLabel la;
  
  public ImageGallery(){
    super("Image Gallery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    
    //Set Vector 
    for(int i=0; i<fl.length; i++){
      ImageIcon icon = new ImageIcon(fl[i].getPath());
      v.add(icon);
    }
    
    //North panel
    JPanel northPanel = new JPanel();
    JButton leftButton = new JButton("Left");
    JButton rightButton = new JButton("Right");
    
    northPanel.setPreferredSize(new Dimension(300, 50));
    northPanel.setVisible(true);
    northPanel.setLayout(null);
    
    leftButton.setBounds(0,0,150,50);
    rightButton.setBounds(150,0,150,50);
    
    leftButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        if(num > 0)
          la.setIcon(v.get(--num));
      }
    });
    
    rightButton.addActionListener(new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if(num < v.size())
          la.setIcon(v.get(++num));
      }
    });
    
    northPanel.add(leftButton);
    northPanel.add(rightButton);
    
    c.add(northPanel, BorderLayout.NORTH);
    
    //Center panel
    ImageIcon icon = new ImageIcon(fl[0].getPath());
    la = new JLabel("",icon, SwingConstants.CENTER);
    
    c.add(la, BorderLayout.CENTER);
    
    //final
    setSize(300,300);
    setVisible(true);
  }

  
  public static void main(String[] args){
    new ImageGallery();
  }  
}


