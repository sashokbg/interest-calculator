package bg.alexander.lihva.masiv;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class WriteMasiv {

	public static void writeRecord(Record r,String path){
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter((path),true));
			w.append("\n"+r.toString());
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
