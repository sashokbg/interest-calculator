package bg.alexander.lihva.programa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Configuration {

	public static void writePath(String path){
		File f= new File("lihva.cfg");
		
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write("\""+path+"\"");
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public static String getPath(){
		File f= new File("lihva.cfg");
		Scanner scan = null;
		if(f.exists()){
			try {
				scan = new Scanner(f);
				scan.useDelimiter("\"");
				String toReturn=scan.next();
				return toReturn;
			}
			catch (NoSuchElementException e1) {
				return "LIHVA.TXT";
			} catch (FileNotFoundException e) {
				return "LIHVA.TXT";
			}
			finally {
				if(scan!=null){
					scan.close();
				}
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
