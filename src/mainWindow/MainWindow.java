package mainWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import DailyEntries.AccessMySql;
import DailyEntries.TempFrame1;
import DocDetails.AddDoctor;
import PreviousEntries.GetOldDBEntries;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private static ClientPreparedStatement preparedStatement ;
	private ResultSet resultSet = null;
	boolean user_present=false;
	private JPasswordField password;
	public JProgressBar progressBar = new JProgressBar(0,10);
	
	JButton btnDoAnEntry = new JButton("Daily Entry");
	JButton btnOpenOldBill = new JButton("Detailed Bill");
	JButton btnAddDcotor = new JButton("Add a new Doctor");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ValidateUser(String textUser,String textPassword)
	{
		System.out.println("textUser : "+textUser);
		System.out.println("textPassword : "+textPassword);
		AccessMySql sql = new AccessMySql();
		
		try {
			java.sql.Connection conn=sql.connectDB();
			preparedStatement = (ClientPreparedStatement) conn.prepareStatement("SELECT user, password from user_Det");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String user,password;
				user=resultSet.getString("user");
				password=resultSet.getString("password");
				if(textUser.equals(user))
				{
					if(textPassword.equals(password))
					{
						user_present=true;
						btnDoAnEntry.setBounds(78, 221, 119, 23);
						btnOpenOldBill.setBounds(207, 221, 125, 23);
						btnAddDcotor.setBounds(55, 250, 150, 23);
						contentPane.add(btnDoAnEntry);
						contentPane.add(btnOpenOldBill);
						contentPane.add(btnAddDcotor);
						contentPane.repaint();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Check Your Password");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check Your Username");
				}
			}
			conn.close();
			Task task = new Task();                
            task.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Bhatnagar Dental Art");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar.setBounds(218, 163, 103, 23);
		contentPane.add(progressBar);
		
		//JButton btnDoAnEntry = new JButton("Daily Entry");
		//btnDoAnEntry.setBounds(88, 221, 109, 23);
		
		
		//JButton btnOpenOldBill = new JButton("Make Detailed Bill");
		//btnOpenOldBill.setBounds(218, 221, 109, 23);
		//contentPane.add(btnDoAnEntry);
		//contentPane.add(btnOpenOldBill);
		
		user = new JTextField();
		user.setBounds(180, 90, 141, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				System.out.println("here");
				ValidateUser(user.getText(),password.getText());

				progressBar.setValue(0);
			    progressBar.setStringPainted(true);

				/*if(user_present==true)
				{
					contentPane.add(btnDoAnEntry);
					contentPane.add(btnOpenOldBill);
				}*/
				
			}
		});
		btnLogin.setBounds(88, 163, 89, 23);
		contentPane.add(btnLogin);
		
		btnDoAnEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setVisible(true);
					new TempFrame1().frmCreatebill.setVisible(true);
			}
		});
		
		btnOpenOldBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(true);
					new GetOldDBEntries().setVisible(true);
				} catch (Exception e1) {
					System.out.println("Exception : "+e1);
					e1.printStackTrace();
				}
			}
		});
		
		btnAddDcotor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setVisible(true);
					new AddDoctor().setVisible(true);
				} catch (Exception e1) {
					System.out.println("Exception : "+e1);
					e1.printStackTrace();
				}
			}
		});
		
		
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(88, 91, 82, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(88, 119, 82, 20);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(180, 121, 141, 20);
		contentPane.add(password);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblWelcome.setBounds(132, 29, 141, 30);
		contentPane.add(lblWelcome);
		
	}
	
	private class Task extends Thread {    
	      public Task(){
	      }

	      public void run(){
	         for(int i =0; i<= 500; i+=10){
	            final int progress = i;
	            SwingUtilities.invokeLater(new Runnable() {
	               public void run() {
	                  progressBar.setValue(progress);
	                 // outputTextArea.setText(outputTextArea.getText() 
	                 // + String.format("Completed %d%% of task.\n", progress));
	               }
	            });
	            try {
	               Thread.sleep(100);
	            } catch (InterruptedException e) {}
	         }
	      }
	   }   
	
	
}
