package programa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Configuration {

	public static void writePath(String path){
		// TODO Auto-generated method stub
		
		File f= new File("lihva.cfg");
		
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write("\""+path+"\"");
			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public static String getPath(){
		File f= new File("lihva.cfg");
		
		if(f.exists()){
			Scanner scan;
			try {
				scan = new Scanner(f);
				scan.useDelimiter("\"");
				String toReturn=scan.next();
				return toReturn;
			}
			catch (NoSuchElementException e1) {
				// TODO Auto-generated catch block
				return "LIHVA.TXT";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				return "LIHVA.TXT";
			}
		}
		else{
			System.out.println("File doesnt exist");
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				return "LIHVA.TXT";
			}
		}
	}
}
