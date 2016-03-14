package bg.alexander.lihva.instrumenti;

public class Numbers {

	/**
	 * Returns the rounded number as a double, where value is the
	 * initial number, and x is the number of digits after the decimal
	 * sign to be rounded.
	 * 
	 * Example: if value = 3.146 and x = 2, the method will return 3.15
	 *
	 **/
	public static double round(double value,int x){
		Double d = new Double(value);
		String s = d.toString();
		int i = 0;
		while(s.charAt(i)!='.'){
			i++;
		}
		try{
			int digit = s.charAt(i+x+1)-48;
			int multiplier = (int) Math.pow(10, x);
			value=value*multiplier;
			if(digit>=5)
				value=Math.ceil(value);
			else
				value=Math.floor(value);
			return value/multiplier;
		}catch(StringIndexOutOfBoundsException e){
			return value;
		}
	}
}
