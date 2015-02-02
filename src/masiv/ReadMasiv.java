package masiv;

import instrumenti.DateDifference;
import instrumenti.Numbers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Class ReadMasiv se izpolzva za prochitane da daden masiv. Metoda getRecords e statichen,
 * koeto oznachava, che ne se nujdaem ot obekt. getRecords ima za argument String path,koito
 * sadarja patiat do faela vav faelovata sistema.
 * 
 * 
 * @author sashok
 *
 */

public class ReadMasiv {
	
	static FileInputStream f;
	
	public static boolean checkConsistency(String path) throws FileNotFoundException{
		ArrayList<Record> records = getRecords(path);
		
		boolean wrongOrder=false;
		boolean wrong31=false;
		boolean wrong5=false;
		
		for(int i=0;i<records.size();i++){
			Record r = records.get(i);
			int d0 =r.getD0();
			Date d1 = r.getD1();
			Date d2 = r.getD2();
			float d3 = r.getD3();
			float d31 = r.getD31();
			float d4 = r.getD4();
			float d41 = r.getD41();;
			int d5 = r.getD5();
			int d6 =  r.getD6();
			
			if(!wrongOrder && i<records.size()-1 && d0!=records.get(i+1).getD0()-1){
				JOptionPane.showMessageDialog(null, "Masivat ne e sortiran");
				wrongOrder=true;
			}
			if(!wrong31 && Numbers.round(d31,5)!=Numbers.round((d3/360),5)){
				JOptionPane.showMessageDialog(null, "greshna lihva v pole "+d0);
				wrong31=true;
			}
			if(!wrong5 && d5!=DateDifference.getDateDifference360(d1, d2) ){
				JOptionPane.showMessageDialog(null, "greshen period v pole "+d0);
				wrong5=true;
			}
						
		}

		if(wrong31 || wrong5)
			return false;
		else return true;
	}
	
	public static ArrayList<Record> getRecords(String path) throws FileNotFoundException{
		// TODO Auto-generated method stub
		f = new FileInputStream(path);
		Scanner scan = new Scanner(f);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		ArrayList<Record> records = new ArrayList<Record>();
		
		//Ommit first line
		scan.nextLine();
		
		while(scan.hasNext()){
			int D0 = Integer.parseInt(scan.next());
			Date D1 = null;
			Date D2 = null;
			try {
				D1 = formatter.parse(scan.next());
				D2 = formatter.parse(scan.next());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			float D3 = Float.parseFloat(scan.next());
			float D31 = Float.parseFloat(scan.next());
			float D4 = Float.parseFloat(scan.next());
			float D41 = Float.parseFloat(scan.next());
			int D5 = Integer.parseInt(scan.next());
			int D6 = Integer.parseInt(scan.next());
			
			records.add(new Record(D0,D1,D2,D3,D31,D4,D41,D5,D6));
		}
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
}
