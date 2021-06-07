import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamblingWithThread extends JFrame {
	public GamblingWithThread() {
		super("Gambling");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel());  

		setSize(300,220);
		setVisible(true);		
	}
	

	class GamePanel extends JPanel {
		private JLabel [] label = new JLabel [3]; //
		private JLabel result = new JLabel("환영합니다"); 
		private GamblingThread thread = new GamblingThread(label, result); 
		public GamePanel() {
			setLayout(null); 
			for(int i=0; i<label.length; i++) {
				label[i] = new JLabel("0");
				label[i].setSize(60, 30); 
				label[i].setLocation(30+80*i, 50);
				label[i].setHorizontalAlignment(JLabel.CENTER); 
				label[i].setOpaque(true);
				label[i].setBackground(Color.MAGENTA);
				label[i].setForeground(Color.YELLOW);		
				label[i].setFont(new Font("Tahoma", Font.ITALIC, 30));
				add(label[i]); 
			}
			
			result.setHorizontalAlignment(JLabel.CENTER);
			result.setSize(250, 20);
			result.setLocation(30, 120);
			add(result);
			thread.start();
			
			
			addMouseListener(new MouseAdapter() { 
				@Override
				public void mousePressed(MouseEvent e) {
					if(thread.isReady()) 
						thread.startGambling();
				}
			});
		}
	}
	
	class GamblingThread extends Thread {
		private JLabel [] label; 
		private JLabel result; 
		private long delay = 200; 
		private boolean gambling = false; 
		
		public GamblingThread(JLabel [] label, JLabel result) {
			this.label = label;
			this.result = result;
		}
		
		boolean isReady() {
			return !gambling; 
		}
		
		synchronized public void waitForGambling() {
			if(!gambling) 
				try {
					this.wait();
				} catch (InterruptedException e) { return; }
		}
		
		synchronized public void startGambling() {
			gambling = true;
			this.notify(); 
		}		
		
		@Override
		public void run() {
			while(true) {
				waitForGambling(); 
				try {
					int x1 = (int)(Math.random()*5); 
					int x2 = (int)(Math.random()*5); 
					int x3 = (int)(Math.random()*5); 
					
					
					label[0].setForeground(Color.BLUE); 
					sleep(delay);
					label[0].setForeground(Color.YELLOW);
					label[0].setText(Integer.toString(x1));
					
					
					label[1].setForeground(Color.BLUE); 
					sleep(delay);
					label[1].setForeground(Color.YELLOW);					
					label[1].setText(Integer.toString(x2));
					
					
					label[2].setForeground(Color.BLUE);
					sleep(delay);
					label[2].setForeground(Color.YELLOW);					
					label[2].setText(Integer.toString(x3));	
					
				
					if(x1 == x2 && x2 == x3)
						result.setText("축하합니다!!");
					else 
						result.setText("아쉽군요");
					gambling = false; 
				} catch (InterruptedException e) { return; }
			}
		}	
	}
	
	public static void main(String[] args) {
		new GamblingWithThread();
	}
}
