package instrumenti;


/**
 * @author sashok
 *
 */
public class FixDateString {

	public static String fixDate(String d){
		
		
		String a ="",b="",c="";
		
		int i=0;
		while(i<d.length() && !(d.charAt(i)<=57 && d.charAt(i)>=48)){
			i++;
		}
		while(i<d.length() && d.charAt(i)<=57 && d.charAt(i)>=48){
			a=a+d.charAt(i);
			i++;
		}
		while(i<d.length() && !(d.charAt(i)<=57 && d.charAt(i)>=48)){
			i++;
		}
		while(i<d.length() && d.charAt(i)<=57 && d.charAt(i)>=48){
			b=b+d.charAt(i);
			i++;
		}
		while(i<d.length() && !(d.charAt(i)<=57 && d.charAt(i)>=48)){
			i++;
		}
		while(i<d.length() && d.charAt(i)<=57 && d.charAt(i)>=48 ){
			c=c+d.charAt(i);
			i++;
			
		}
		if(a.length()==1){
			a="0"+a;
		}
		if(b.length()==1){
			b="0"+b;
		}
		d=a+"/"+b+"/"+c;
		return d;
	}
	
}
