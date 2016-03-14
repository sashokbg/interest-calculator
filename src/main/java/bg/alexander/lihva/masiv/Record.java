package bg.alexander.lihva.masiv;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Records predstavliava edinichen kortej ot masiva s lihvenite procenti.
 * Poletata na klasa sa 9, kato purvoto e poreden nomer(D0), sledvano ot 2 dati (D1,D2), opredeliashti
 * perioda na korteja, osnoven lihven procent za mesec(D3), osnoven lihven procent za den (D31)
 * zakonen lihven procent za mesec(D4), zakonen lihven procent za den(D41), broi dni na perioda (D5)
 * i mesec (D6).
 * 
 * Klasat ima counter, koito se izpolzva za debug.Counter-a pokazva, kolko pati konkreten kortej e
 * bil povikan ili po tochno metoda isInDatePeriod() e bil povikan.
 * 
 * @author sashok
 *
 */
public class Record {
	private int D0,D5,D6;
	private Date D1,D2;
	private float D3,D31,D4,D41;
	private int counter;
	
	public Record(int d0, Date d1, Date d2, float d3,
			float d31, float d4, float d41,int d5, int d6) {
		D0 = d0;
		D5 = d5;
		D6 = d6;
		D1 = d1;
		D2 = d2;
		D3 = d3;
		D31 = d31;
		D4 = d4;
		D41 = d41;
		counter=0;
	}
	
	public boolean isInDatePeriod(Date d){
		if(D1.before(d) && D2.after(d)){
			counter++;
			return true;
		}
		else if(D1.compareTo(d)==0 || D2.compareTo(d)==0){
			counter++;
			return true;
		}
		return false;
	}
	
	public int getCounter(){
		return counter;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("###.#####");
		df.setMinimumFractionDigits(5);
		df.setMaximumIntegerDigits(2);
		
		DecimalFormat df2 = new DecimalFormat("###.#####");
		df2.setMinimumFractionDigits(2);
		
		DecimalFormat df3 = new DecimalFormat("##");
		df3.setMinimumIntegerDigits(2);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return D0+" "+formatter.format(D1)+"  "+formatter.format(D2)+"" +
				"   "+df2.format(D3)+"   "+df.format(D31)+"   "+df2.format(D4)+"   "+df.format(D41)+"   "+df3.format(D5)+"   "+df3.format(D6);
	}
	
	public String toStringEUDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return D0+"   "+formatter.format(D1)+"  "+formatter.format(D2)+"" +
				"   "+D3+"   "+D31+"   "+D4+"   "+D41+"   "+D5+"   "+D6;
	}
	
	public String toShortStringEUDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return "( "+formatter.format(D1)+" - "+formatter.format(D2)+" ) " +
				"   "+D31+"  "+D41+"   "+D5;
	}
	
	public String toShortStringEUDateOsnLihva(){
		DecimalFormat df = new DecimalFormat("###.#####");
		df.setMinimumFractionDigits(5);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df2 = new DecimalFormat("###.#####");
		df2.setMinimumIntegerDigits(2);
		return "( "+formatter.format(D1)+" - "+formatter.format(D2)+" )        " +
				"             "+df.format(D31)+"            "+df2.format(D5);
	}
	public String toShortStringEUDateZakLihva(){
		DecimalFormat df = new DecimalFormat("###.#####");
		df.setMinimumFractionDigits(5);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df2 = new DecimalFormat("###.#####");
		df2.setMinimumIntegerDigits(2);
		return "( "+formatter.format(D1)+" - "+formatter.format(D2)+" )        " +
				"             "+df.format(D41)+"            "+df2.format(D5);
	}
	
	public void print(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("--"+" "+D0+" "+formatter.format(D1)+" "+formatter.format(D2)+" "+D3+" "+D31+" "
				+D4+" "+D41+" "+D5+" "+D6+" -- "+counter);
	}
	
	public void shortPrint(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("--"+" "+D0+" "+formatter.format(D1)+" - "+formatter.format(D2)+" "+D31+" "+D41+" "+D5+" -- "+counter);
	}
	
	public int getD0() {
		return D0;
	}
	public void setD0(int d0) {
		D0 = d0;
	}
	public int getD5() {
		return D5;
	}
	public void setD5(int d5) {
		D5 = d5;
	}
	public int getD6() {
		return D6;
	}
	public void setD6(int d6) {
		D6 = d6;
	}
	public Date getD1() {
		return D1;
	}
	public void setD1(Date d1) {
		D1 = d1;
	}
	public Date getD2() {
		return D2;
	}
	public void setD2(Date d2) {
		D2 = d2;
	}
	public float getD3() {
		return D3;
	}
	public void setD3(float d3) {
		D3 = d3;
	}
	public float getD31() {
		return D31;
	}
	public void setD31(float d31) {
		D31 = d31;
	}
	public float getD4() {
		return D4;
	}
	
	public void setD4(float d4) {
		D4 = d4;
	}
	public float getD41() {
		return D41;
	}
	public void setD41(float d41) {
		D41 = d41;
	}
	
}
