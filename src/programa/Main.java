package programa;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import userInterface.Window;


public class Main {
	static Date start;
	static Date end;
	static float suma;
	static float lihva;
	static boolean osnovenLihv; //boolean za osnoven ili zakonen lihven procent
	public static Calendar cal = Calendar.getInstance();
	
	float lihvenProcent; //zima se ot faela
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Window frame = new Window();
		
		frame.init();
		frame.setSize(400, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		
		frame.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		frame.setVisible(true);
	}
}
