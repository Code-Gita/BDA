package DailyEntries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mainWindow.Logs;

public class TableControls {

	JFrame frame;
	JScrollPane scrollPane;
	JTable table_2;
	private double qty;
	private double rate;
	private double amount;
	private String amountstr;
	Logger logger;
	DecimalFormat df = new DecimalFormat("#.00");
	

	Logs log = new Logs();

	public TableControls(JFrame frame, JScrollPane scrollpane, JTable table) {
		this.frame = frame;
		this.scrollPane = scrollpane;
		this.table_2 = table;
		logger = log.startLoggiing(this.getClass());
	}

	/**
	 * The Table displayed for Billing Details
	 * 
	 * @return
	 */
	public JTable Jtable(JTextField amt) {
		Object[] columns = { "SNo", "Product", "Qty", "Rate", "Amount" };

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(columns);

		table_2.setModel(dtm);

		setColumnWidths(table_2, 30, 350, 50, 100, 150);

		JButton btnAdd = new JButton("Add Row");
		JButton btnDelete = new JButton("Remove Row");
		JButton btnDone = new JButton("Done Inserting");

		btnDelete.setLocation(0, -1);

		btnAdd.setBounds(725, 220, 100, 25);
		btnDelete.setBounds(725, 250, 100, 25);
		btnDone.setBounds(700, 280, 150, 25);

		frame.getContentPane().add(scrollPane);

		frame.getContentPane().add(btnAdd);
		frame.getContentPane().add(btnDelete);
		frame.getContentPane().add(btnDone);

		Object[] row = new Object[4];

		/**
		 * Add Button Functionality
		 */

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// row[0] = next_row++;
				dtm.addRow(row);
			}
		});

		/**
		 * Delete Button Functionality
		 */

		// button remove row
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// i = the index of the selected row
				int i = table_2.getSelectedRow();
				if (i >= 0) {
					// remove a row from jtable
					dtm.removeRow(i);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row");
				}
			}
		});

		// button Done
		btnDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// calcTotalAmt();
				double amounts = 0.00d;

				for (int i = 0; i < table_2.getRowCount(); i++) {
					amounts = Double.parseDouble(table_2.getModel().getValueAt(i, 4).toString()) + amounts;
				}
				//amt.setText(Double.toString(amounts));
				
				amt.setText(df.format(amounts));
			}
		});

		setNewTabKeyAction(table_2);

		scrollPane.setViewportView(table_2);

		return table_2;
	}

	/**
	 * Set Column Widths
	 * 
	 * @param table
	 * @param widths
	 */
	public static void setColumnWidths(JTable table, int... widths) {
		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < widths.length; i++) {
			if (i < columnModel.getColumnCount()) {
				columnModel.getColumn(i).setMaxWidth(widths[i]);
			} else
				break;
		}
	}

	/**
	 * Set custom action for tab key pressed.
	 * 
	 * @param table
	 */
	public void setNewTabKeyAction(JTable table) {
		// forward Navigation.
		KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0); // forward
		KeyStroke shiftTab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, KeyEvent.SHIFT_MASK); // backward

		new WrappedAction(table, tab) {

			public void actionPerformed(ActionEvent e) {

				int rows = table.getSelectedRow();
				int cols = table.getSelectedColumn();

				invokeOriginalAction(e);
				
				/**
				 * Open a new window to select the product information
				 * After the tab pressed for 
				 * **/
				if(cols ==0)
				{
					logger.info("Please select the product");
					new SelectProduct().setVisible(true);
				}

				if (cols == 2) {
					qty = Double.parseDouble(table.getModel().getValueAt(rows, cols).toString());
				}

				if (cols == 3 && table.getModel().getValueAt(rows, cols) != null) {
					rate = Double.parseDouble(table.getModel().getValueAt(rows, cols).toString());
					logger.info("rate " + rate + " qty: " + qty);
					amount = qty * rate;
					amountstr = df.format(amount);
					table.getModel().setValueAt(amountstr, rows, 4);
				}

			}
		};

		new WrappedAction(table, shiftTab) {

			public void actionPerformed(ActionEvent e) {
				int rows = table.getSelectedRow();
				int cols = table.getSelectedColumn();
				invokeOriginalAction(e);
				if (cols == 4) {
					// invokeOriginalAction( e );
					table.getModel().setValueAt("", rows, cols);
				}
			}
		};
	}
}
