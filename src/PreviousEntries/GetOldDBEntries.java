package PreviousEntries;

import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import org.jdesktop.swingx.JXDatePicker;

import com.itextpdf.text.DocumentException;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class GetOldDBEntries extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6267090103151270350L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetOldDBEntries frame = new GetOldDBEntries();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public GetOldDBEntries() throws Exception {
		setTitle("GET DETAILED BILL");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 333);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JXDatePicker datePickerStart = new JXDatePicker();
        datePickerStart.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        datePickerStart.setBounds(130, 140, 118, 20);
        contentPane.add(datePickerStart);
        
        JXDatePicker datePickerEnd = new JXDatePicker();
        datePickerEnd.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
        datePickerEnd.setBounds(130, 180, 118, 20);
        contentPane.add(datePickerEnd);
		
		JLabel lblName = new JLabel("Name ");
		lblName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblName.setBounds(48, 102, 46, 14);
		contentPane.add(lblName);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 100, 223, 20);
		contentPane.add(comboBox);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblStartDate.setBounds(48, 143, 68, 14);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblEndDate.setBounds(48, 182, 68, 14);
		contentPane.add(lblEndDate);
		
		//Object Declared
		DbActtivities dbAct = new DbActtivities(comboBox,datePickerStart,datePickerEnd);
		
		dbAct.getDrNames();
		
		Button button = new Button("Get Detailed Bill");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dbAct.getDetailedBill();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(130, 220, 108, 30);
		contentPane.add(button);
		
		JLabel lblBhatnagarDentalArt = new JLabel("Bhatnagar Dental Art");
		lblBhatnagarDentalArt.setFont(new Font("Andalus", Font.BOLD, 24));
		lblBhatnagarDentalArt.setBounds(80, 25, 242, 30);
		contentPane.add(lblBhatnagarDentalArt);
		
		JLabel lbldetailedBillModule = new JLabel("(Detailed Bill Module)");
		lbldetailedBillModule.setBounds(151, 60, 137, 14);
		contentPane.add(lbldetailedBillModule);
	}
}
