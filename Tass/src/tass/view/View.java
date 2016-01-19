package tass.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tass.controller.Controller;
import tass.data.Row;

public class View extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	private Controller controller;
	
	
	public void run() {
		try {
			View frame = this;
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
		btnNewButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	removeAllRows();
		    	displayRowsForInput(textField.getText());
		    }
		});
		
		JButton btnNewButton_1 = new JButton("Wyczyść");
		btnNewButton_1.setBounds(675, 9, 99, 23);
		btnNewButton_1.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	textField.setText("");
		    	removeAllRows();
		    }
		});
		
		contentPane.add(btnNewButton_1);
		
		String[] columnNames = {"Nazwa publikacji",
                "Czasopismo",
                "ImpactFactor"};
		Object[][] data = {};
		model = new DefaultTableModel(data, columnNames);
		
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 41, 764, 459);
		contentPane.add(scrollPane);
		
	}
	
	private void displayRowsForInput(String userInput){
		List<Row> rows = controller.getDataForValue(userInput);
		Object[] rowArray = null;
		for(Row row:rows){
			rowArray = new Object[3];
			rowArray[0] = row.getArticleName();
			rowArray[1] = row.getMagazineName();
			rowArray[2] = row.getImpactFactor();
			model.addRow(rowArray);
		}
	}
	
	private void removeAllRows(){
		while(model.getRowCount()>0){
			model.removeRow(0);
		}
		
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}

