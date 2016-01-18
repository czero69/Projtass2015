package tass.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tass.controller.Controller;

public class View extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	
	private Controller controller;
	
	
	public void run() {
		try {
			View frame = new View();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*TODO: dodac listenery do przyciskow(jeden podaje input do controllera i dostaje listę wierszy do wstawienia do tabeli, a drugi czyści aktualnie wyświetlone infromacje)
			dodac obsługę wierszy - przerabianie z klasy Row na wiersz tabeli
	*/
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 546, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Szukaj");
		btnNewButton.setBounds(566, 9, 99, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wyczyść");
		btnNewButton_1.setBounds(675, 9, 99, 23);
		contentPane.add(btnNewButton_1);
		
		String[] columnNames = {"Nazwa publikacji",
                "Czasopismo",
                "ImpactFactor"};
		
		
		//test dodawania wierszy
		Object[][] data = {
			    {"TestName", "TestMagName", 2.0},
			    {"TestName1", "TestMagName1", 3.0}
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		Object[] row = {"TestName2", "TestMagName2", 3.0};
		table = new JTable(model);
		model.addRow(row);
		model.addRow(row);
		model.addRow(row);	
		//koniec testu
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 764, 459);
		contentPane.add(scrollPane);
		

	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}

