package DailyEntries;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import org.omg.Messaging.SyncScopeHelper;

public class TempFrame extends TempFrame1 {

	
	//JTextField billPayee;
	JComboBox<String> billPayee;
	JTextField billDate;
	JTable table;
	JTextField billTotalAmount;
	
	TempFrame(JComboBox<String> billPayee,JTextField billDate,JTable table,JTextField billTotalAmount){
		this.billPayee=billPayee;
		//this.billDate=billDate;
		this.table=table;
		this.billTotalAmount=billTotalAmount;
	}
	TempFrame(JComboBox<String> billPayee,JTable table,JTextField billTotalAmount){
		this.billPayee=billPayee;
		this.table=table;
		this.billTotalAmount=billTotalAmount;
	}
	
	
	/*public void setbillPayee(JTextField billPayee)
	{
		this.billPayee=billPayee;
	}
	
	public JTextField getbillPayee()
	{
		return this.billPayee;
	}
	*/
	
	public void setbillPayee(JComboBox<String> billPayee)
	{
		this.billPayee=billPayee;
	}
	
	public JComboBox<String> getbillPayee()
	{
		return this.billPayee;
	}
	
	public void setbillDate(JTextField billDate)
	{
		this.billDate=billDate;
	}
	
	public JTextField getbillDate()
	{
		return this.billDate;
	}
	
	
	
	public void clearFields()
	{
		//billPayee.setText("");
		//billDate.setText("");
		billTotalAmount.setText("");
		DefaultTableModel model = (DefaultTableModel)this.table.getModel();
		model.setRowCount(0);
	}
	
	
}
