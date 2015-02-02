package userInterface;

import java.util.Comparator;

import masiv.Record;

public class MyComparator implements Comparator<Record>{

	@Override
	public int compare(Record arg0, Record arg1) {
		Record r1= arg0;
		Record r2= arg1;
		if(r1.getD0() == r2.getD0())
			return 0;
		else if(r1.getD0() > r2.getD0())
			return 1;
		else
			return 0;
	}
}
