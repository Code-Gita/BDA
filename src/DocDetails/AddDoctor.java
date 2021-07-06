package DocDetails;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DailyEntries.InsertInDB;

import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField docName;
	private JTextField docAddress;
	private JTextField docContact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDoctor frame = new AddDoctor();
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
	public AddDoctor() {
		setForeground(new Color(204, 255, 153));
		setTitle("Doctor Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 261);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddDoctorDetails = new JLabel("Add Doctor Details");
		lblAddDoctorDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblAddDoctorDetails.setBounds(164, 11, 168, 24);
		contentPane.add(lblAddDoctorDetails);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblName.setBounds(40, 60, 46, 14);
		contentPane.add(lblName);
		
		docName = new JTextField();
		docName.setToolTipText("Please enter Name of the doctor");
		docName.setBounds(108, 58, 320, 20);
		contentPane.add(docName);
		docName.setColumns(10);
		
		JButton btnAddDoctor = new JButton("Add Doctor");
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status=0;
				InsertInDB ins = new InsertInDB();
				
				System.out.println("docName : "+docName.getText()+" doc Addr : "+docAddress.getText()+" docContact : "+docContact.getText());
				
				status = ins.addDoctorInDB(docName.getText(),docAddress.getText(),docContact.getText());
				System.out.println("status= " + status);
			}
		});
		btnAddDoctor.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnAddDoctor.setBounds(108, 153, 133, 23);
		contentPane.add(btnAddDoctor);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblAddress.setBounds(40, 85, 68, 14);
		contentPane.add(lblAddress);
		
		docAddress = new JTextField();
		docAddress.setToolTipText("Enter the address of the doctor");
		docAddress.setBounds(108, 83, 320, 20);
		contentPane.add(docAddress);
		docAddress.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblContact.setBounds(40, 112, 68, 14);
		contentPane.add(lblContact);
		
		docContact = new JTextField();
		docContact.setToolTipText("Contcat : Ph/Mobile number\r\nOnly numeric ");
		docContact.setBounds(108, 110, 127, 20);
		contentPane.add(docContact);
		docContact.setColumns(10);
	}
}
