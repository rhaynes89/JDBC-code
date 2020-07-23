package project3EC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.*;

public class DBGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userNameEntry;
	private JPasswordField passwordEntry;
	private JTextField connectionStatus;
	private JTextField SQLCommandEntry;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBGUI frame = new DBGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DBGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter Database Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("JDBC Driver:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Database URL:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox JDBCDriverBox = new JComboBox();
		JDBCDriverBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		JDBCDriverBox.addItem("com.mysql.jdbc.Driver");
		JDBCDriverBox.setSelectedItem(null);
		
		JComboBox DBURLBox = new JComboBox();
		DBURLBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		DBURLBox.addItem("jdbc:mysql://localhost:3306/project3");
		DBURLBox.setSelectedItem(null);
		
		userNameEntry = new JTextField();
		userNameEntry.setFont(new Font("Tahoma", Font.BOLD, 12));
		userNameEntry.setColumns(10);
		
		passwordEntry = new JPasswordField();
		passwordEntry.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNewLabel_5 = new JLabel("Connection Status:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		connectionStatus = new JTextField();
		connectionStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
		connectionStatus.setEditable(false);
		connectionStatus.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Enter An SQL Command");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JTextArea SQLCommandEntry = new JTextArea();
		SQLCommandEntry.setFont(new Font("Monospaced", Font.BOLD, 14));
		SQLCommandEntry.setLineWrap(true);
		SQLCommandEntry.setWrapStyleWord(true);
		SQLCommandEntry.setEnabled(false);
		SQLCommandEntry.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("SQL Execution Result Window");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton clrSQLCommand = new JButton("Clear SQL Command");
		clrSQLCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLCommandEntry.setText(" ");
			}
		});
		clrSQLCommand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clrSQLCommand.setEnabled(false);
		contentPane.add(clrSQLCommand);
		
		JButton exeSQLCommand = new JButton("Execute SQL Command");
		exeSQLCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userName = userNameEntry.getText();
				String passWord = passwordEntry.getText();
				try
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project3", userName, passWord);
					Statement stmt = conn.createStatement();
					ResultSet result = stmt.executeQuery(SQLCommandEntry.getText());
					ResultSetMetaData mData = result.getMetaData();
					//int numOfCols = mData.getColumnCount();
						
				
					
				}
				catch (Exception ex1)
				{
					ex1.printStackTrace();
				}
			}
		});
		exeSQLCommand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exeSQLCommand.setEnabled(false);
		
		JButton clrResultWindow = new JButton("Clear Result Window");
		clrResultWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLResultWindow.setText(" ");
			}
		});
		clrResultWindow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clrResultWindow.setEnabled(false);
		contentPane.add(clrResultWindow);
		
		JButton connToDatabase = new JButton("Connect to Database");
		connToDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameEntry.getText();
				String passWord = passwordEntry.getText();
				
				 try 
				 {
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project3", userName, passWord);

					 if(userName.equals("root"))
					 {
						 connectionStatus.setText("Connected to jdbc:mysql://localhost:3306/project3 as Root.");
						 connectionStatus.setBackground(Color.green);
						 SQLCommandEntry.setEnabled(true);
						 exeSQLCommand.setEnabled(true);
						 clrSQLCommand.setEnabled(true);
						 clrResultWindow.setEnabled(true);
						 SQLResultWindow.setEnabled(true);
						 SQLCommandEntry.setEnabled(true);
						 connToDatabase.setEnabled(false);
					 }
					 else if(userName.equals("client"))
					 {
						 connectionStatus.setText("Connected to jdbc:mysql://localhost:3306/project3 as Client.");
						 connectionStatus.setBackground(Color.green);
						 SQLCommandEntry.setEnabled(true);
						 exeSQLCommand.setEnabled(true);
						 clrSQLCommand.setEnabled(true);
						 clrResultWindow.setEnabled(true); 
						 SQLResultWindow.setEnabled(true);
						 SQLCommandEntry.setEnabled(true);
						 connToDatabase.setEnabled(false);
					 }
					 
				 }				 
				 catch (Exception ex)
				 {
					 connectionStatus.setText("Failure to connect to jdbc:mysql://localhost:3306/project3 as " + userName + ".");
					 connectionStatus.setBackground(Color.RED);
				 }
				
			}
		});
		connToDatabase.setBounds(16, 373, 182, 29);
		contentPane.add(connToDatabase);
		connToDatabase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		table = new JTable();
		
		table_1 = new JTable();
		
		table_2 = new JTable();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(145)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(687))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(connToDatabase)
									.addGap(18)
									.addComponent(exeSQLCommand)
									.addGap(18)
									.addComponent(clrSQLCommand)
									.addGap(18)
									.addComponent(clrResultWindow)
									.addGap(239))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addGap(26)
										.addComponent(JDBCDriverBox, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_2)
											.addComponent(lblNewLabel_3)
											.addComponent(lblNewLabel_4))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(passwordEntry, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
											.addComponent(userNameEntry, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
											.addComponent(DBURLBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(SQLCommandEntry, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_6)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(table_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
											.addComponent(table_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 1089, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(connectionStatus)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(JDBCDriverBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(DBURLBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(userNameEntry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(passwordEntry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SQLCommandEntry, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(connectionStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(lblNewLabel_7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(107)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(236))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addGap(308)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(clrResultWindow)
										.addComponent(clrSQLCommand)
										.addComponent(exeSQLCommand)
										.addComponent(connToDatabase))
									.addGap(50))))))
		);
		
		table_3 = new JTable();
		scrollPane_1.setColumnHeaderView(table_3);
		contentPane.setLayout(gl_contentPane);
	}
}
