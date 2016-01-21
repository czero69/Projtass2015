package tass.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			View frame = this;
			frame.setSize(screenSize.width/2 + 200, screenSize.height/2);
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
		textField.setSize(700, 20);
		//textField.setBounds(10, 10, 546, 20);
		//contentPane.add(textField);
		textField.setColumns(10);
			
		int year = Calendar.getInstance().get(Calendar.YEAR);

	    final JComboBox<String> yearCombo1 = new JComboBox<String>();  
	    final JComboBox<String> yearCombo2 = new JComboBox<String>();    
	    
	    for(int i=0;i<60;i++)
	    {
	    	yearCombo1.addItem(Integer.toString(year-i));
	    	yearCombo2.addItem(Integer.toString(year-i));
	    }
	    
	    
	    yearCombo1.setSelectedIndex(0);
	    yearCombo2.setSelectedIndex(0);
	      	      
	    //yearCombo1.setBounds(800, 9, 99, 23);
	    //yearCombo2.setBounds(1000, 9, 99, 23);
	    //contentPane.add(yearCombo1);
	    //contentPane.add(yearCombo2);
		
  	    
		final JButton btnNewButton = new JButton("Szukaj");
		final JButton btnNewButton_1 = new JButton("Wyczyœæ");
		//btnNewButton.setBounds(566, 9, 99, 23);
		//contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	//btnNewButton.setEnabled(false);
		    	//btnNewButton_1.setEnabled(false);
		    	
		    	String year1 = "", year2 = "";
		    	if (yearCombo1.getSelectedIndex() != -1 && yearCombo2.getSelectedIndex() != -1 ) {                     
		            year1 = yearCombo1.getItemAt(yearCombo1.getSelectedIndex());    
		            year2 = yearCombo2.getItemAt(yearCombo2.getSelectedIndex());
		         }
		    	controller.setYears(year1, year2);
		    	removeAllRows();
		    	displayRowsForInput(textField.getText());
		    }
		});
		//btnNewButton_1.setBounds(675, 9, 99, 23);
		btnNewButton_1.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	textField.setText("");
		    	removeAllRows();
		    }
		});
		
		//contentPane.add(btnNewButton_1);
		
		String[] columnNames = {"Nazwa publikacji",
                "Czasopismo",
                "ImpactFactor"};
		Object[][] data = {};
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("od roku:");
		JLabel jLabel2 = new JLabel();
		jLabel2.setText("do:");
		
		setLayout(new BorderLayout());
		JPanel mypanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		mypanel.add(textField);
		mypanel.add(btnNewButton);
		mypanel.add(btnNewButton_1);
		mypanel.add(jLabel1);
		mypanel.add(yearCombo1);
		mypanel.add(jLabel2);
		mypanel.add(yearCombo2);
		
		contentPane.add(textField, BorderLayout.PAGE_START);
		contentPane.add(mypanel, BorderLayout.PAGE_END);
		
		//setLayout(new BorderLayout());
		
		model = new DefaultTableModel(data, columnNames);
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(800);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		//contentPane.add(table, BorderLayout.SOUTH);
		//table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		scrollPane = new JScrollPane(table);
		//scrollPane.setBounds(10, 41, 764, 459);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private void displayRowsForInput(String userInput){
		List<Row> rows = controller.getDataForValue(userInput);
		Object[] rowArray = null;
		for(Row row:rows){
			rowArray = new Object[3];
			rowArray[0] = row.getArticleName();
			rowArray[1] = row.getMagazineName();
			if(row.getImpactFactor()>=0)
				rowArray[2] = row.getImpactFactor();
			else
				rowArray[2] = "Brak danych";
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

