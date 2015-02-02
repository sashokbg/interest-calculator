package userInterface;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu {

	private Window frame;
	
	public Menu(Window frame){
		this.frame=frame;
	}
	
	public JMenuBar getJMenuBar(){
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		//Create the menu bar.
		menuBar = new JMenuBar();
		//Build the first menu.
		menu = new JMenu("Файл");
		menuItem = new JMenuItem("Изход");
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(frame);
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu=new JMenu("Настройки");
		menuItem = new JMenuItem("Зареди БД файл");
		menuItem.setActionCommand("openDB");
		menuItem.addActionListener(frame);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu=new JMenu("База Данни");
		menu.setName("bd");
		menu.addActionListener(frame);
		menuItem = new JMenuItem("Добави запис");
		menuItem.setActionCommand("addDB");
		menuItem.addActionListener(frame);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Проверка на масива");
		menuItem.setActionCommand("checkDB");
		menuItem.addActionListener(frame);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menuBar.add(menu);
		return menuBar;

	}
	
}
