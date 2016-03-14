package bg.alexander.lihva.userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import bg.alexander.lihva.instrumenti.DateDifference;
import bg.alexander.lihva.instrumenti.FixDateString;
import bg.alexander.lihva.masiv.ReadMasiv;
import bg.alexander.lihva.masiv.Record;
import bg.alexander.lihva.masiv.Search;
import bg.alexander.lihva.programa.Configuration;

public class Window extends JFrame implements ActionListener,FocusListener{
	private static final long serialVersionUID = 8949945076887597904L;
	
	private String filePath=Configuration.getPath(); //Default file path. Can be changed from the menu.
	private JTextField glavnica,date1,date2,zavishenie;
	private JTextArea resultArea;
	private JRadioButton a1,a2,b,b1,b2;
	private JLabel label,labelFile;
	private JButton result,result2;
	private JSeparator separator;
	private Check check;
	
	private ArrayList<Record> records = null;
	
	private JPanel p1,p2,p3;
	
	public void init(){
		
		this.setTitle("Лихва");
		
		//First panel															//First panel
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBorder(new LineBorder(Color.black));
		//end first pannel
		
		
		//label LIHVA															//label LIHVA
		label = new JLabel("ЛИХВА");
		label.setBounds(140, 10, 200, 40);
		label.setFont(new Font("Serif",Font.BOLD,32));
		//end label LIHVA
		p1.add(label);
		
		//Label Glavnica														//Label Glavnica
		label = new JLabel("Главница");
		label.setBounds(20, 50, 60, 20);
		p1.add(label);
		
		//textField glavnica												  //textField glavnica
		glavnica = new JTextField();
		glavnica.setBounds(100, 50, 70, 20);
		glavnica.setToolTipText("Въведете стойността на главницата в лева.");
		p1.add(glavnica);
		
		//separator																separator
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(10,75,370,1);
		p1.add(separator);
		
		//label period or														label period or
		label = new JLabel("Период от");
		label.setBounds(20,80,70,20);
		p1.add(label);
		
		//JtextFeld date1														JtextFeld date1
		date1 = new JTextField();
		date1.setBounds(100,80,70,20);
		date1.addFocusListener(this);
		date1.setToolTipText("Въведете начална дата от периода във формат дд / ММ / гггг");
		date1.setText("дд / ММ / гггг");
		p1.add(date1);
		
		//label do																label do
		label = new JLabel("до");
		label.setBounds(180,80,20,20);
		p1.add(label);
		
		//JtextFeld date2														JtextFeld date2
		date2 = new JTextField();
		date2.setBounds(210,80,70,20);
		date2.addFocusListener(this);
		date2.setToolTipText("Въведете крайна дата от периода във формат дд / ММ / гггг");
		date2.setText("дд / ММ / гггг");
		p1.add(date2);
		
		//separator																separator
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(10,105,370,1);
		p1.add(separator);
		
		//label lihven procent													label lihven procent
		label = new JLabel("Лихвен Процент: ");
		label.setBounds(20, 110, 120,20);
		label.setToolTipText("Изберете типът на лихвения процент.");
		p1.add(label);
		
		//radio button a1 osnoven												radio button a1 osnoven
		a1= new JRadioButton();
		a1.setActionCommand("osnoven");
		a1.setBounds(70,140,20,20);
		a1.setSelected(true);
		a1.addActionListener(this);
		a1.setToolTipText("Изберете типът на лихвения процент.");
		p1.add(a1);
		
		//radio button a2 zakonen												radio button a2 zakonen
		a2= new JRadioButton();
		a2.setActionCommand("zakonen");
		a2.setBounds(240,140,20,20);
		a2.addActionListener(this);
		a2.setToolTipText("Изберете типът на лихвения процент.");
		p1.add(a2);
		
		label = new JLabel("Основен");
		label.setBounds(55,160,150,20);
		p1.add(label);
		
		label = new JLabel("Законен");
		label.setBounds(225,160,150,20);
		p1.add(label);
		
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(10,180,370,1);
		p1.add(separator);
		
		label = new JLabel("Завишение");
		label.setBounds(20, 200, 80, 20);
		label.setToolTipText("Подлежи ли на завишение процентът?");
		p1.add(label);
		
		b = new JRadioButton();
		b.setBounds(100, 200, 20, 20);
		b.setActionCommand("zavishenie");
		b.addActionListener(this);
		b.setToolTipText("Подлежи ли на завишение процентът?");
		p1.add(b);
		
		p2=new JPanel();
		p2.setLayout(null);
		p2.setBounds(150, 190, 200, 90);
		p2.setBorder(new LineBorder(Color.red));
		
		b1 = new JRadioButton();
		b1.setBounds(10, 10, 20,20);
		b1.setActionCommand("dnevno");
		b1.addActionListener(this);
		b1.setSelected(true);
		p2.add(b1);
		
		label = new JLabel("днево");
		label.setBounds(30, 10, 70, 20);
		p2.add(label);
		
		
		label = new JLabel("Стойност");
		label.setBounds(120, 10, 60, 20);
		p2.add(label);
		
		
		zavishenie = new JTextField();
		zavishenie.setBounds(120, 30, 50, 20);
		p2.add(zavishenie);
		
		
		separator = new JSeparator(JSeparator.VERTICAL);
		separator.setBounds(100, 0, 1, 90);
		p2.add(separator);
		
		b2 = new JRadioButton();
		b2.setBounds(10, 50, 20,20);
		b2.setActionCommand("godishno");
		b2.addActionListener(this);
		p2.add(b2);
		
		label = new JLabel("годишно");
		label.setBounds(30, 50, 70, 20);
		p2.add(label);
		
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setBounds(2, 235, 370, 120);
		
		
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(8,15,370,1);
		p3.add(separator);
		
		result = new JButton("Резултат");
		result.setBounds(8, 20, 100, 30);
		result.setActionCommand("result");
		result.addActionListener(this);
		p3.add(result);
		
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setBounds(180,20,180,70);
		resultArea.setBorder(new LineBorder(Color.black));
		p3.add(resultArea);
		
		result2 = new JButton("Подр.Рез.");
		result2.setFont(new Font("gfdg",Font.BOLD,12));
		result2.setBounds(8, 60, 100, 30);
		result2.setActionCommand("podroben result");
		result2.addActionListener(this);
		p3.add(result2);
		
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(8,95,370,1);
		p3.add(separator);
		
		p1.add(p3);
		p2.setVisible(false);
		
		this.add(p2);
		this.add(p1);
		
		Menu menu = new Menu(this);
		this.setJMenuBar(menu.getJMenuBar());
		
		loadDatabase(filePath);
		check= new Check(this);
		
	}

	/**********************************************************************************************
	 * 
	 * 						KRAI INICIALIZACIA
	 * @return
	 */
	//**********************************************************************************************
	
	/**
	 * 
	 */
	
	
	private void loadDatabase(String filePath){
		try{
			records = ReadMasiv.getRecords(filePath);
			result.setEnabled(true);
			result2.setEnabled(true);
			this.getJMenuBar().getMenu(2).setEnabled(true);
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Проблем при зареждане на базата от данни.","Грешка при инициализацията",JOptionPane.ERROR_MESSAGE);
			filePath=null;
			result.setEnabled(false);
			result2.setEnabled(false);
			this.getJMenuBar().getMenu(2).setEnabled(false);
		}
		
		if(labelFile==null){
			labelFile = new JLabel();
			labelFile.setBounds(5, 0, 400, 20);
			labelFile.setFont(new Font("Serif",Font.BOLD,10));
			p1.add(labelFile);
		}
		
		//label baza ot danni													label baza ot danni
		if(filePath==null)
			labelFile.setText("Заредена база данни: НЯМА ЗАРЕДЕНА БАЗА ОТ ДАННИ !");
		else
			labelFile.setText("Заредена база данни: "+filePath);
		
	}
	
	public boolean check(){
		
		//Check the price value
		boolean correct = true;
		
		correct = correct && check.checkGlavnica(glavnica);
		
		if(b.isSelected()){
			correct= correct && check.checkZavishenie(zavishenie);
		}
		// END Check the price value
		// Check dates
		
		correct = correct && check.checkDate(date1, date2);
		
		// END Check dates
		
		
		return correct;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("osnoven")){
			a1.setSelected(true);
			a2.setSelected(false);
		}
		if(arg0.getActionCommand().equals("zakonen")){
			a1.setSelected(false);
			a2.setSelected(true);
		}
		if(arg0.getActionCommand().equals("zavishenie")){
			if(b.isSelected()){
				p2.setVisible(true);
				p3.setBounds(2, 280, 370, 120);
			}
			else{
				p2.setVisible(false);
				p3.setBounds(2, 235, 370, 120);
			}
		}
		
		else if(arg0.getActionCommand().equals("dnevno")){
			b1.setSelected(true);
			b2.setSelected(false);
		}
		else if(arg0.getActionCommand().equals("godishno")){
			b1.setSelected(false);
			b2.setSelected(true);
		}
		else if(arg0.getActionCommand().equals("openDB")){
			JFileChooser fc = new JFileChooser(); // Show open dialog; this method does not return until the dialog is closed 
			fc.showOpenDialog(this); 
			File selFile = fc.getSelectedFile(); 
			if(selFile!=null){
				this.filePath=selFile.getAbsolutePath();
				Configuration.writePath(selFile.getAbsolutePath());
			}
			this.loadDatabase(filePath);
		}
		else if(arg0.getActionCommand().equals("exit")){
			System.exit(0);
		}
		
		else if(arg0.getActionCommand().equals("podroben result")){
			if(check()){
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = null;
				Date d2 = null;
				try {
					d1 = formatter.parse(date1.getText());
					d2 = formatter.parse(date2.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				loadDatabase(filePath);
				double suma = Double.parseDouble(glavnica.getText());
				boolean osnLihva = a1.isSelected();
				Search query = new Search(d1, d2, records, osnLihva, suma);
				
				if(b.isSelected()){
					
					boolean dnevno=true;
					
					if(b1.isSelected()){
						dnevno = true;
					}
					else if(b2.isSelected()){
						dnevno = false;
					}
					
					double zav = Double.parseDouble(zavishenie.getText());
					query.setZavishenie(dnevno, zav);
				}
				
				query.calculate();
				
				ResultWindow windowRes = new ResultWindow(query);
				windowRes.init();
			}
		}
		
		else if(arg0.getActionCommand().equals("addDB")){
			AddRecordFrame f2 = new AddRecordFrame(records,filePath);
			f2.init();
		}
		
		else if(arg0.getActionCommand().equals("result")){
			if(check()){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = null;
				Date d2 = null;
				try {
					d1 = formatter.parse(date1.getText());
					d2 = formatter.parse(date2.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				loadDatabase(filePath);
				double suma = Double.parseDouble(glavnica.getText());
				boolean osnLihva = a1.isSelected();
				Search query = new Search(d1, d2, records, osnLihva, suma);
				
				if(b.isSelected()){
					
					boolean dnevno=true;
					
					if(b1.isSelected()){
						dnevno = true;
					}
					else if(b2.isSelected()){
						dnevno = false;
					}
					
					double zav = Double.parseDouble(zavishenie.getText());
					query.setZavishenie(dnevno, zav);
				}
				
				DecimalFormat df = new DecimalFormat("#######.##");
				df.setMinimumFractionDigits(2);
				df.setMaximumFractionDigits(2);
				
				resultArea.setText("Главница "+df.format(query.getSuma())+"" +
						"\nЛихвата е: "+df.format(query.calculate())+" лева\n"+"" +
						"За период от: "+DateDifference.getDateDifference360(d1, d2)+" дни"
					);
			}
		}
		
		else if(arg0.getActionCommand().equals("checkDB")){
			try {
				System.out.println("check");
				ReadMasiv.checkConsistency(filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(date1)){
			if(date1.getText().equals("дд / ММ / гггг")){
				date1.setText("");
			}
		}
		if(arg0.getSource().equals(date2)){
			if(date2.getText().equals("дд / ММ / гггг")){
				date2.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(date1)){
			String d = date1.getText();
			if(d.compareTo("")!=0)
				date1.setText(FixDateString.fixDate(d));
			else
				date1.setText("дд / ММ / гггг");
		}
		if(arg0.getSource().equals(date2)){
			String d = date2.getText();
			if(d.compareTo("")!=0)
				date2.setText(FixDateString.fixDate(d));
			else
				date2.setText("дд / ММ / гггг");
		}
		
		
	}
	
}
