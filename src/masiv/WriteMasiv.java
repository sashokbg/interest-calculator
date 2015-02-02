package masiv;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class WriteMasiv {

	public static void writeRecord(Record r,String path){
		// TODO Auto-generated method stub
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter((path),true));
			w.append("\n"+r.toString());
			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
