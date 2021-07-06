package EditBill;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class BasedOnDoctor extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasedOnDoctor frame = new BasedOnDoctor();
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
	public BasedOnDoctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(123, 61, 130, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(381, 61, 130, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblDocotrName = new JLabel("Doctor Name");
		lblDocotrName.setBounds(33, 64, 70, 14);
		contentPane.add(lblDocotrName);
		
		JLabel lblBillId = new JLabel("Bill Id");
		lblBillId.setBounds(325, 64, 46, 14);
		contentPane.add(lblBillId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 138, 476, 131);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(240, 101, 89, 23);
		contentPane.add(btnLoad);
		
		DatabaseAct dbact = new DatabaseAct();
		dbact.getDrNames(comboBox);
		comboBox.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				dbact.getBillIds(comboBox,comboBox_1);
			}
		});
		
	}
}
