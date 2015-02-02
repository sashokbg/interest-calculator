package userInterface;

import instrumenti.CheckDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Check {
	private JFrame window;
	
	public Check(JFrame window){
		this.window=window;
	}
	
	public boolean checkGlavnica(JTextField glavnica){
		double suma;
		
		//Proverka na sumata
		try{
			suma = Double.parseDouble(glavnica.getText());
			if(suma<=0){
				JOptionPane.showMessageDialog(window,"Въвели сте невалидна сума.","Грешка",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(window,"Въвели сте невалидна сума.","Грешка",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// END Proverka na sumata
		return true;
		
	}
	
	public boolean checkLihva(JTextField lihva){
		double l;
		
		//Proverka na sumata
		try{
			l = Double.parseDouble(lihva.getText());
			if(l<=0){
				JOptionPane.showMessageDialog(window,"Въвели сте невалидна лихва.","Грешка",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(window,"Въвели сте невалидна лихва.","Грешка",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// END Proverka na sumata
		return true;
		
	}
	
	public boolean checkZavishenie(JTextField zavishenie){
		
		//Proverka zavishenie
		
			double zav;
			try{
				zav = Double.parseDouble(zavishenie.getText());
				if(zav<=0 ){
					JOptionPane.showMessageDialog(window,"Въвели сте невалидна стойност за завишение на лихвата" +
							".","Грешка",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(window,"Въвели сте невалидна стойност за завишение на лихвата" +
						".","Грешка",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		//END Proverka zavishenie
		return true;
	}
	
	public boolean checkDate(JTextField date1,JTextField date2){
		// Proverka na datite
		
		if(!CheckDate.isValidDate(date1.getText())){
			JOptionPane.showMessageDialog(window,"Въвели сте невалидна дата в поле \"дата1\".","Грешка",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!CheckDate.isValidDate(date2.getText())){
			JOptionPane.showMessageDialog(window,"Въвели сте невалидна дата в поле \"дата2\".","Грешка",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date d1=formatter.parse(date1.getText());
			Date d2=formatter.parse(date2.getText());
			
			if(d2.before(d1)){
				JOptionPane.showMessageDialog(window,"Въвели сте невалидни дати.\n" +
						"Първата дата трябва да е преди втората.","Грешка",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// END proverka na datite
		return true;
	}
}
