package masiv;

import instrumenti.DateDifference;
import instrumenti.Numbers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
/**
 * Tozi klas se naricha Search, tai kato pretarsva vsichki korteji v masiva s lihvenite procenti i
 * opredelia vavedenia ot potrebitelia period, v koi ot tiah popada, kato v sushtoto vreme izchisliava
 * lihvata.
 * @author sashok
 *
 */
public class Search {
	
	double lihva=0;
	double zavishenie=0;
	Vector<Record> midResults;
	long dateDifference;
	Date d1;
	Date d2;
	ArrayList<Record> records;
	double suma;
	boolean osnLihva;
	private double result;
	
	public Search(Date d1, Date d2,ArrayList<Record> records,boolean osnLihva,double suma){
		this.zavishenie=0;
		this.d1=d1;
		this.d2=d2;
		this.records=records;
		this.osnLihva=osnLihva;
		this.suma=suma;
		this.dateDifference=DateDifference.getDateDifference360(d1, d2);
		this.result=0;
	}
	
	public Double getResult(){
		return this.result;
	}
	
	public double getLihva() {
		return lihva;
	}
	public void setLihva(double lihva) {
		this.lihva = lihva;
	}
	public double getZavishenie() {
		return zavishenie;
	}
	public void setZavishenie(double zavishenie) {
		this.zavishenie = zavishenie;
	}
	public long getDateDifference() {
		return dateDifference;
	}
	public void setDateDifference(long dateDifference) {
		this.dateDifference = dateDifference;
	}
	public Date getD1() {
		return d1;
	}
	public void setD1(Date d1) {
		this.d1 = d1;
	}
	public Date getD2() {
		return d2;
	}
	public void setD2(Date d2) {
		this.d2 = d2;
	}
	public ArrayList<Record> getRecords() {
		return records;
	}
	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}
	public double getSuma() {
		return suma;
	}
	public void setSuma(double suma) {
		this.suma = suma;
	}
	public boolean isOsnLihva() {
		return osnLihva;
	}
	public void setOsnLihva(boolean osnLihva) {
		this.osnLihva = osnLihva;
	}
	/**
	 * Postavia se zavishenie na lihvenia procent, kadeto 'dnevno' e flag, dali zavishenieto e
	 * za 1 den ili godishno (za 360).
	 * int zavishenie e samoto zavishenie.
	 * @param dnevno
	 */
	public void setZavishenie(boolean dnevno,double zavishenie){
		if(dnevno)
			this.zavishenie=zavishenie;
		else{
			this.zavishenie=zavishenie/360;
		}
	}
	
	
	public Vector<Record> getResults(){
		return midResults;
	}
	
	/**
	 * Dati d1 i d2 opredeliat perioda za koito shte izchisliavame lihvata.
	 * records e masiv, sadarjasht vsichki zapisi ot faela s lihvite pod formata na
	 * obekti ot tip Record, s koito programata raboti. /VIJ klas ReadMasiv/
	 * osnLihva e flag, koito opredelia dali presmiatame osnoven lihven procent /true/ ili
	 * zakonen /false/.
	 * suma e glavnicata.
	 *  
	 * @param d1
	 * @param d2
	 * @param records
	 * @param osnLihva
	 * @param suma
	 */
	public double calculate(){
		
		midResults = new Vector<Record>();
		Calendar temp = new GregorianCalendar();
		temp.setTime(d1);
		
		try{
			dateDifference = DateDifference.getDateDifference360(d1, d2);
		}catch(Exception e){
			dateDifference = DateDifference.getDateDifference(d1, d2);
		}
		
		
		for(long i=0;i<dateDifference;i++){
			if(temp.get(Calendar.DAY_OF_MONTH)>30){
				i--;
			}
			else{
				for(Record r : records){
					if(r.isInDatePeriod(d1)){
						if(osnLihva){
							lihva=lihva + suma*(r.getD31()+zavishenie)/100;
						}
						else{
							lihva=lihva+suma*(r.getD41()+zavishenie)/100;
						}
						midResults.add(r);
					}
				}
			}
			if(d1.equals(d2))
				break;
			temp.set(Calendar.DATE,temp.get(Calendar.DATE)+1);
			d1 = temp.getTime();
		}
		
		/**
		 * Corigirasht loop
		 */
		for(Record r : records){
			if(r.getCounter()<r.getD5() && r.getCounter()!=0 && r.getD5()==30){
				for(int i=0;i<(30-r.getCounter());i++){
					if(osnLihva){
						lihva=lihva + suma*(r.getD31()+zavishenie)/100;
					}
					else{
						lihva=lihva+suma*(r.getD41()+zavishenie)/100;
					}
					midResults.add(r);
				}
			}
		}
		this.result=Numbers.round(lihva, 2);
		return result;
	}
}
