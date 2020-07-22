package frontend;

import javax.swing.*;
import java.awt.*;



public class SplashScreen extends JWindow {
	Image splashScreen;     
	ImageIcon imageIcon;    
	JFrame frame;
	SplashScreen(){
		splashScreen = Toolkit.getDefaultToolkit().getImage("D:\\splashScreen.jpeg");
		imageIcon = new ImageIcon(splashScreen);
		setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width-getSize().width)/2; 
		int y = (dim.height-getSize().height)/2; 
		setLocation(x,y);
		setVisible(true); 

	}
	public void paint(Graphics g) {
	      super.paint(g);
	      g.drawImage(splashScreen, 0, 0, this);
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplashScreen screen = new SplashScreen();
		try {
			Thread.sleep(5000);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		screen.dispose();
		Login.main(args);
	}

}
