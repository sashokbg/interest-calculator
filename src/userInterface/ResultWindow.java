package userInterface;

import instrumenti.Numbers;

import java.awt.Insets;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import masiv.Record;
import masiv.Search;

public class ResultWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380645712687329016L;
	private double suma;
	private Vector<Record> results;
	private JTextArea text;
	private Search query;
	
	public ResultWindow(Search query){
		this.query=query;
		this.results=query.getResults();
		this.suma=query.getSuma();
	}
	
	public void init(){
		this.setBounds(100, 100, 600, 300);
		this.setVisible(true);
		text = new JTextArea(100,100);
		text.setEditable(false);
		text.setMargin(new Insets(30,30,10,10));
		JScrollPane scroll = new JScrollPane(text);
		this.add(scroll);
		this.setTitle("Подробен Резултат");
		
		int i =0;
		double lihva=0;
		
		Collections.sort(results, new MyComparator()) ;
		
		if(query.isOsnLihva())
			text.append("Изчисляване на ОСНОВЕН ЛИХВЕН ПРОЦЕНТ\n");
		else
			text.append("Изчисляване на ЗАКОНЕН ЛИХВЕН ПРОЦЕНТ\n");
		text.append("Главница "+ query.getSuma()+" лева\nЗа период от "+query.getDateDifference()+" дни.\n\n");
		text.append("--------- период -------------------- лихвен процент ------ дни --------- лихва\n");
		
		int count = 0;
		
		while(i<results.size()){
			while((i+1)<results.size() &&results.get(i).getD0()==results.get(i+1).getD0()){
				i++;
			}
			if(++count%30==0)
				text.append("--------- период -------------------- лихвен процент ------ дни --------- лихва\n");
			
			if(query.isOsnLihva()){
				lihva=suma*results.get(i).getD31()*results.get(i).getD5()/100;
				text.append(results.get(i).toShortStringEUDateOsnLihva()+"            "+Numbers.round(lihva, 2)+"\n");
			}
			else{
				lihva=suma*results.get(i).getD41()*results.get(i).getD5()/100;
				text.append(results.get(i).toShortStringEUDateZakLihva()+"            "+Numbers.round(lihva, 2)+"\n");
			}
			i++;
			lihva =0;

		}
		text.append("------------------------------------------------------------------------"+ query.getDateDifference() +"---------- "+query.getResult());
		if(query.isOsnLihva())
			text.append("\nИзчисляване на ОСНОВЕН ЛИХВЕН ПРОЦЕНТ\n");
		else
			text.append("\nИзчисляване на ЗАКОНЕН ЛИХВЕН ПРОЦЕНТ\n");
		text.append("Главница "+ query.getSuma()+" лева\nЗа период от "+query.getDateDifference()+" дни.\n\n");
	}
}
