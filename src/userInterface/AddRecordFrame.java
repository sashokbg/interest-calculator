package userInterface;

import instrumenti.CheckDate;
import instrumenti.DateDifference;
import instrumenti.FixDateString;
import instrumenti.Numbers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import masiv.Record;
import masiv.WriteMasiv;

public class AddRecordFrame extends JFrame implements ActionListener,FocusListener,KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Record> records;
	private String filePath;
	private JTextField nomer,date1,date2,lihva,lihvaDen,zakLihva,zakLihvaDen,dni,mesec;
	private JTextArea text;
	private JButton b1,b2,b3;
	private JLabel label;
	private Record r;
	private Check check;
	
	
	public AddRecordFrame(ArrayList<Record> records,String filePath){
		this.records=records;
		this.filePath=filePath;
	}
	
	public void init(){
		
		this.setBounds(360,50,600, 500);
		this.setVisible(true);
		this.setTitle("Лихва : Добавяне на запис");
		this.setBackground(Color.white);
		this.setLayout(null);
		
		text = new JTextArea();
		text.setEditable(false);
		

		for(Record r : records){
			text.append(r.toString()+"\n");
		}
		
		JScrollPane scroll = new JScrollPane(text);
		scroll.setBounds(10,10,500,300);
		
		this.add(scroll);
		
		b1 = new JButton("Добави");
		b1.setBounds(10,420,100,30);
		b1.setActionCommand("add");
		b1.addActionListener(this);
		b1.setEnabled(false);
		b1.setToolTipText("Внимание! Изписаните на екрана дати са прочетени от масива и са\n" +
				"във амрикански формат (мм/дд/гггг).\n" +
				"Моля при въвеждане на нов запис, използвайте европейски стил на запис (дд/мм/гггг).");
		this.add(b1);
		
		b2= new JButton("Затвори");
		b2.setBounds(400,420,100,30);
		b2.setActionCommand("close");
		b2.addActionListener(this);
		this.add(b2);
		
		b3= new JButton("Попълни");
		b3.setBounds(10,380,100,30);
		b3.setActionCommand("calc");
		b3.addActionListener(this);
		b3.setToolTipText("Въведете полета дата2 и лихва и натиснете бутона \"попълни\"");
		this.add(b3);
		
		label = new JLabel("Номер     Дата1           Дата2           Лихва     Л./ден     З.Лихва   З.Л/ден     Дни   Месец");
		label.setBounds(10,330,500,20);
		this.add(label);
		
		nomer = new JTextField();
		nomer.setEditable(false);
		nomer.setBackground(Color.white);
		nomer.setBounds(10,350,50,20);
		this.add(nomer);
		
		date1 = new JTextField();
		date1.setEditable(false);
		date1.setBackground(Color.white);
		date1.setBounds(60,350,70,20);
		this.add(date1);
		
		date2 = new JTextField();
		date2.setBackground(Color.white);
		date2.setBounds(130,350,70,20);
		date2.addFocusListener(this);
		date2.addKeyListener(this);
		this.add(date2);
		
		lihva = new JTextField();
		lihva.setBackground(Color.white);
		lihva.setBounds(200,350,50,20);
		lihva.addFocusListener(this);
		lihva.addKeyListener(this);
		this.add(lihva);
		
		lihvaDen = new JTextField();
		lihvaDen.setBackground(Color.white);
		lihvaDen.setEditable(false);
		lihvaDen.setBounds(250,350,60,20);
		this.add(lihvaDen);
		
		zakLihva = new JTextField();
		zakLihva.setBackground(Color.white);
		zakLihva.setEditable(false);
		zakLihva.setBounds(300,350,50,20);
		this.add(zakLihva);
		
		zakLihvaDen = new JTextField();
		zakLihvaDen.setBackground(Color.white);
		zakLihvaDen.setEditable(false);
		zakLihvaDen.setBounds(350,350,50,20);
		this.add(zakLihvaDen);
		
		dni = new JTextField();
		dni.setBackground(Color.white);
		dni.setEditable(false);
		dni.setBounds(400,350,50,20);
		this.add(dni);
		
		mesec = new JTextField();
		mesec.setBackground(Color.white);
		mesec.setEditable(false);
		mesec.setBounds(450,350,50,20);
		this.add(mesec);
		
		check = new Check(this);
		
		preCalculate();
	}

	private void preCalculate(){
		Record last = records.get(records.size()-1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		int d0 =(last.getD0()+1);
		nomer.setText(d0+"");
		
		Calendar temp = new GregorianCalendar();
		temp.setTime(last.getD2());
		temp.set(Calendar.DATE, temp.get(Calendar.DATE)+1);
		Date d1=temp.getTime();
		
		date1.setText(formatter.format(d1));
	}
	
	private void reload(){
		text.append(r.toString()+"\n");
		date2.setText("");
		lihva.setText("");
		b1.setEnabled(false);
		preCalculate();
	}
	
	private void calculate(){
		
		Record last = records.get(records.size()-1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(5);
		
		int d0 =(last.getD0()+1);
		nomer.setText(d0+"");
		
		Calendar temp = new GregorianCalendar();
		temp.setTime(last.getD2());
		temp.set(Calendar.DATE, temp.get(Calendar.DATE)+1);
		Date d1=temp.getTime();
		
		date1.setText(formatter.format(d1));
		
		
		String strD2 = date2.getText();
		
		Date d2=null;
		try {
			d2 = formatter.parse(strD2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		float d3 = Float.parseFloat(lihva.getText());
		
		float d31 = (float) Numbers.round(d3/360,5);
		
		lihvaDen.setText(df.format(d31).toString()+"");
		
		float d4= d3 +10;
		
		zakLihva.setText(d4+"");
		
		float d41 = (float) Numbers.round(d4/360,5);
		
		zakLihvaDen.setText(df.format(d41).toString()+"");
		
		int d5 = (int) DateDifference.getDateDifference360(d1, d2);
		
		dni.setText(d5+"");
		
		int month = temp.get(Calendar.MONTH)+1;
		
		mesec.setText(month+"");
		
		r = new Record(d0,d1,d2,d3,d31,d4,d41,d5,7);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("add")){
			if(check.checkDate(date1, date2) && check.checkLihva(lihva)){
				WriteMasiv.writeRecord(r,filePath );
				records.add(r);
				reload();
			}
		}
		else if(arg0.getActionCommand().equals("close")){
			this.dispose();
		}
		else if(arg0.getActionCommand().equals("calc")){
			if(!date2.getText().equals("") && !lihva.getText().equals("") && CheckDate.isValidDate(date2.getText())){
				try{
					double d = Double.parseDouble(lihva.getText());
					if(d > 0){
						calculate();
						b1.setEnabled(true);
					}
					else{
						b1.setEnabled(false);
					}
				}
				catch(Exception e){
					b1.setEnabled(false);
				}
			}
			else{
				b1.setEnabled(false);
			}
		}
	}

	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(date2)){
			date2.setText(FixDateString.fixDate(date2.getText()));
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		this.b1.setEnabled(false);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
