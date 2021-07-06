package DailyEntries;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class SelectProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectProduct frame = new SelectProduct();
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
	public SelectProduct() {
		setBackground(new Color(51, 204, 0));
		setTitle("Select My Product");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnRpd = new JRadioButton("RPD");
		rdbtnRpd.setActionCommand("RPD");
		rdbtnRpd.setBounds(28, 67, 130, 23);
		contentPane.add(rdbtnRpd);
		
		JRadioButton rdbtnRetainer = new JRadioButton("Retainer");
		rdbtnRetainer.setActionCommand("Retainer");
		rdbtnRetainer.setBounds(28, 93, 130, 23);
		contentPane.add(rdbtnRetainer);
		
		JRadioButton rdbtnAppliances = new JRadioButton("Appliances");
		rdbtnAppliances.setActionCommand("Appliances");
		rdbtnAppliances.setBounds(28, 119, 130, 23);
		contentPane.add(rdbtnAppliances);
		
		JRadioButton rdbtnCompleteDenture = new JRadioButton("Complete Denture");
		rdbtnCompleteDenture.setActionCommand("Complete Denture");
		rdbtnCompleteDenture.setBounds(28, 145, 130, 23);
		contentPane.add(rdbtnCompleteDenture);
		
		JRadioButton rdbtnMetalCrown = new JRadioButton("Metal Crown");
		rdbtnMetalCrown.setActionCommand("Metal Crown");
		rdbtnMetalCrown.setBounds(28, 171, 130, 23);
		contentPane.add(rdbtnMetalCrown);
		
		JRadioButton rdbtnCeramicCrown = new JRadioButton("Ceramic Crown");
		rdbtnCeramicCrown.setActionCommand("Ceramic Crown");
		rdbtnCeramicCrown.setBounds(28, 197, 130, 23);
		contentPane.add(rdbtnCeramicCrown);
		
		JRadioButton rdbtnZirconiumCrown = new JRadioButton("Zirconium Crown");
		rdbtnZirconiumCrown.setActionCommand("Zirconium Crown");
		rdbtnZirconiumCrown.setBounds(28, 223, 130, 23);
		contentPane.add(rdbtnZirconiumCrown);
		
		JRadioButton rdbtnTemporaryCrown = new JRadioButton("Temporary Crown");
		rdbtnTemporaryCrown.setActionCommand("Temporary Crown");
		rdbtnTemporaryCrown.setBounds(28, 249, 130, 23);
		contentPane.add(rdbtnTemporaryCrown);
		
		JRadioButton rdbtnNightGaurd = new JRadioButton("Night Gaurd");
		rdbtnNightGaurd.setActionCommand("Night Gaurd");
		rdbtnNightGaurd.setBounds(28, 275, 130, 23);
		contentPane.add(rdbtnNightGaurd);
		
		JRadioButton rdbtnSplint = new JRadioButton("Splint");
		rdbtnSplint.setActionCommand("Splint");
		rdbtnSplint.setBounds(28, 301, 130, 23);
		contentPane.add(rdbtnSplint);
		
		JRadioButton rdbtnBasePlate = new JRadioButton("Base Plate");
		rdbtnBasePlate.setActionCommand("Base Plate");
		rdbtnBasePlate.setBounds(28, 327, 130, 23);
		contentPane.add(rdbtnBasePlate);
		
		JRadioButton rdbtnBleachingTray = new JRadioButton("Bleaching Tray");
		rdbtnBleachingTray.setActionCommand("Bleaching Tray");
		rdbtnBleachingTray.setBounds(28, 353, 130, 23);
		contentPane.add(rdbtnBleachingTray);
		
		JRadioButton rdbtnMetalDenture = new JRadioButton("Metal Denture");
		rdbtnMetalDenture.setActionCommand("Metal Denture");
		rdbtnMetalDenture.setBounds(28, 379, 130, 23);
		contentPane.add(rdbtnMetalDenture);
		
		JRadioButton rdbtnFlexibleDenture = new JRadioButton("Flexible Denture");
		rdbtnFlexibleDenture.setActionCommand("Flexible Denture");
		rdbtnFlexibleDenture.setBounds(28, 405, 130, 23);
		contentPane.add(rdbtnFlexibleDenture);
		
		/**
		 * Adding all the radio buttons to one group
		 * **/
		ButtonGroup select_rdbtn = new ButtonGroup();
		select_rdbtn.add(rdbtnFlexibleDenture);
		select_rdbtn.add(rdbtnMetalDenture);
		select_rdbtn.add(rdbtnBleachingTray);
		select_rdbtn.add(rdbtnBasePlate);
		select_rdbtn.add(rdbtnSplint);;
		select_rdbtn.add(rdbtnNightGaurd);;
		select_rdbtn.add(rdbtnTemporaryCrown);
		select_rdbtn.add(rdbtnRpd);
		select_rdbtn.add(rdbtnAppliances);
		select_rdbtn.add(rdbtnZirconiumCrown);
		select_rdbtn.add(rdbtnCeramicCrown);
		select_rdbtn.add(rdbtnMetalCrown);
		select_rdbtn.add(rdbtnRetainer);
		select_rdbtn.add(rdbtnCompleteDenture);
		
		textField = new JTextField();
		textField.setBounds(411, 93, 123, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(411, 146, 123, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(214, 132, 145, 164);
		contentPane.add(panel);
		
		JCheckBox chckbxUpper = new JCheckBox("Upper");
		
		JCheckBox chckbxLower = new JCheckBox("Lower");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxUpper)
						.addComponent(chckbxLower))
					.addGap(75))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(38)
					.addComponent(chckbxUpper)
					.addGap(27)
					.addComponent(chckbxLower)
					.addContainerGap(42, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton selectedAppliance = new JButton(">>");
		selectedAppliance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RadioButtonModel model = (RadioButtonModel)select_rdbtn.getSelection();
				System.out.println("Selected button : "+select_rdbtn.getSelection().getActionCommand());
				//if(select_rdbtn.isSelected(m))
			}
		});
		selectedAppliance.setBounds(164, 198, 40, 21);
		contentPane.add(selectedAppliance);
	}
}
