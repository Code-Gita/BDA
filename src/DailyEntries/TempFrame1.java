package DailyEntries;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JTextField;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import PreviousEntries.DbActtivities;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class TempFrame1 {

	public JFrame frmCreatebill;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TempFrame1 window = new TempFrame1();
					window.frmCreatebill.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
	public void run() {
		try {
			TempFrame1 window = new TempFrame1();
			window.frmCreatebill.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
*/
	/**
	 * Create the application.
	 */
	public TempFrame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreatebill = new JFrame();
		frmCreatebill.setResizable(false);
		frmCreatebill.getContentPane().setBackground(new Color(255, 255, 51));
		frmCreatebill.setTitle("CreateBill");
		frmCreatebill.setBounds(100, 100, 885, 481);
		frmCreatebill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frmCreatebill.getContentPane().setLayout(null);

		JLabel lblBillNumber = new JLabel("Bill Number :");
		lblBillNumber.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblBillNumber.setBounds(22, 68, 96, 14);
		frmCreatebill.getContentPane().add(lblBillNumber);
		
		JComboBox<String> billPayee_comboBox = new JComboBox<String>();
		billPayee_comboBox.setBounds(158, 124, 489, 22);
		frmCreatebill.getContentPane().add(billPayee_comboBox);
		DbActtivities dbA = new DbActtivities();
		dbA.getDrNames_DocTable(billPayee_comboBox);

		JTable table = new JTable();

		JLabel billNum = new JLabel(new InsertInDB().getBillId());
		billNum.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		billNum.setBounds(118, 69, 46, 14);
		frmCreatebill.getContentPane().add(billNum);
		
		SimpleDateFormat FormatDate = new SimpleDateFormat("dd/MM/yyyy");
		billDate = new JTextField();
		billDate.setBackground(SystemColor.text);
		billDate.setToolTipText("Bill Date");
		billDate.setBounds(701, 65, 127, 20);
		frmCreatebill.getContentPane().add(billDate);
		billDate.setText(FormatDate.format(new Date()));
		billDate.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 180, 659, 172);
		frmCreatebill.getContentPane().add(scrollPane);
		
		totalAmountVal = new JTextField();
		totalAmountVal.setBackground(SystemColor.text);
		totalAmountVal.setBounds(546, 353, 135, 20);
		frmCreatebill.getContentPane().add(totalAmountVal);

		lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(448, 356, 96, 14);
		frmCreatebill.getContentPane().add(lblTotalAmount);

		JButton btnNewButton = new JButton("Insert Entry");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertInDB ins = new InsertInDB(billNum,billDate,billPayee_comboBox,table,totalAmountVal);
				ins.insert();
			}
		});
		btnNewButton.setBounds(306, 384, 127, 23);
		frmCreatebill.getContentPane().add(btnNewButton);
		
		/**
		 * Controls the coding done for the Table DIsplayed 
		 * with Billing Details
		 **/
			
		TableControls tabControl =new TableControls(frmCreatebill, scrollPane,table);
		tabControl.Jtable(totalAmountVal);
		
		JButton btnNewButton_1 = new JButton("Print Bill");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("");
				PdfControls pdf = new PdfControls(billNum,billDate,billPayee_comboBox,table,totalAmountVal);
				pdf.createPdf();
			}
		});
		btnNewButton_1.setBounds(482, 384, 141, 23);
		frmCreatebill.getContentPane().add(btnNewButton_1);

		button = new JButton("+");
		frmCreatebill.getContentPane().add(button);

		button_1 = new JButton("-");
		frmCreatebill.getContentPane().add(button_1);
		
		JLabel lblMs = new JLabel("M/s:");
		lblMs.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMs.setBounds(102, 126, 46, 14);
		frmCreatebill.getContentPane().add(lblMs);
		
		lblControlPanel = new JLabel("Control Panel");
		lblControlPanel.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
		lblControlPanel.setBounds(725, 181, 114, 20);
		frmCreatebill.getContentPane().add(lblControlPanel);
		
		lblBhatnagarDentalArt = new JLabel("BHATNAGAR DENTAL ART");
		lblBhatnagarDentalArt.setFont(new Font("Andalus", Font.BOLD, 24));
		lblBhatnagarDentalArt.setBounds(262, 34, 322, 28);
		frmCreatebill.getContentPane().add(lblBhatnagarDentalArt);
		
		lblBillingDate = new JLabel("Billing Date");
		lblBillingDate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblBillingDate.setBounds(719, 40, 86, 20);
		frmCreatebill.getContentPane().add(lblBillingDate);
		
		JLabel lblcreateBillModule = new JLabel("(Create Bill Module)");
		lblcreateBillModule.setFont(new Font("Arial", Font.PLAIN, 12));
		lblcreateBillModule.setBounds(344, 65, 114, 14);
		frmCreatebill.getContentPane().add(lblcreateBillModule);
		


		//Timer t = new Timer(1000, showTIme);
		//t.start();
	}

	private JTextField billDate;
	private JTextField totalAmountVal;
	private JLabel lblTotalAmount;
	private JButton button;
	private JButton button_1;

	
	/**
	 * Displays Current Date on the Form
	 */
/*	ActionListener showTIme = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar calendar = new GregorianCalendar();
			lblDateAndTime.setText(sdf.format(new Date()).toString() + "  "
					+ (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime())));
		}
	};*/
	private JLabel lblControlPanel;
	private JLabel lblBhatnagarDentalArt;
	private JLabel lblBillingDate;
}
