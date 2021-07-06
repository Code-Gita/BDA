package DailyEntries;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

//import com.mysql.jdbc.PreparedStatement;
import com.mysql.cj.jdbc.ClientPreparedStatement;

public class InsertInDB {

	private ClientPreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	static int i=0;
	static int j=0;
	
	private String billNumber;
	private String billDate;
	private String billPayee;
	private JTable table;
	private String billTotalAmt;
	private JLabel JbillNumber;
	private JTextField JbillDate;
	//private JTextField JbillPayee;
	private JComboBox<String> JbillPayee;
	private JTextField JbillTotalAmt;
	
	
	//public InsertInDB(JLabel billNum,JTextField billDate,JTextField billPayee,JTable table,JTextField billTotalAmt){
	public InsertInDB(JLabel billNum,JTextField billDate,JComboBox<String> billPayee,JTable table,JTextField billTotalAmt){
		this.billNumber =billNum.getText();
		this.billDate=billDate.getText();
		//this.billPayee=billPayee.getText();
		System.out.println("billpayee : " + billPayee.getSelectedItem().toString());
		this.billPayee=billPayee.getSelectedItem().toString();
		this.table=table;
		this.billTotalAmt=billTotalAmt.getText();
		
		this.JbillNumber=billNum;
		this.JbillDate=billDate;
		//this.JbillPayee=billPayee;
		this.JbillTotalAmt=billTotalAmt;
		
	}
	
	public InsertInDB()
	{
		
	}
	
	//public void insert(String billDate,String billPayee,JTable table,String billTotalAmt,JLabel billNumber) {
	public void insert() {
		AccessMySql sql = new AccessMySql();
		
		String[][] tableEntries = new String[100][100];
		
		for ( i = 0; i < table.getRowCount(); i++) {
			for ( j = 0; j<table.getColumnCount(); j++) {
				
				if(table.getModel().getValueAt(i, j) != null)
					tableEntries[i][j]=table.getModel().getValueAt(i, j).toString();
			}
		}
		
		try {
			Connection conn =sql.connectDB();
			preparedStatement = (ClientPreparedStatement) conn.prepareStatement("Insert into bill_Details(bill_id,bill_date,bill_payee,bill_sno,bill_description,bill_qty,bill_rate,bill_amount,bill_total_amount) values(?,str_to_date(?,'%d/%m/%Y'),?,?,?,?,?,?,?);");
			preparedStatement.setInt(1, Integer.parseInt(this.JbillNumber.getText()));
			preparedStatement.setString(2, billDate);
			preparedStatement.setString(3, billPayee);
			
			for(int k=0;k<i;k++)
			{
				int x=4;
				for(int m=0;(m<j)&&(x<=8);m++,x++)
				{
					preparedStatement.setString(x, tableEntries[k][m]);
				}
				preparedStatement.setString(9, billTotalAmt);
				preparedStatement.executeUpdate();
			}
			conn.close();
			/**
			 * Setting BillId and Date for Next Bill
			 * **/
			SimpleDateFormat FormatDate = new SimpleDateFormat("dd/MM/yyyy");
			this.JbillNumber.setText(getBillId());
			this.JbillDate.setText(FormatDate.format(new Date()));
			
			
			//TempFrame tmp = new TempFrame(JbillPayee,JbillDate,table,JbillTotalAmt);
			TempFrame tmp = new TempFrame(JbillPayee,table,JbillTotalAmt);
			tmp.clearFields();
			
			} catch (Exception e){
			e.printStackTrace();
			System.out.println("Exception :"+e);
		}
	}
	
	public String getBillId(){
		String newBillId = "";
		AccessMySql sql = new AccessMySql();
		try {
			Connection conn =sql.connectDB();
			preparedStatement = (ClientPreparedStatement) conn.prepareStatement("Select bill_id from bill_Details;");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id;
				id=resultSet.getInt("bill_id");
				newBillId=Integer.toString(id+1);
			}
			
			/**
			 * Set Starting bill Id as 1
			 * */
			if(newBillId.isEmpty())
			{
				newBillId="1";
			}
			
			conn.close();
			} catch (Exception e){
			e.printStackTrace();
			System.out.println("Exception :"+e);
		}
		
		return newBillId;
	}
	
	/**
	 * Adding code to add new doctor in Doctor table
	 * **/
	public int addDoctorInDB(String docName, String docAddr, String docContact)
	{
		int status=0;
		AccessMySql sql = new AccessMySql();
		
		int newDocId= getNewDocId();
		System.out.println("new doc id : "+newDocId);
		System.out.println("docContact : " + docContact);
		try {
			Connection conn =sql.connectDB();
			preparedStatement = (ClientPreparedStatement) conn.prepareStatement("Insert into Doctor(doctor_id,doctor_name,doctor_address,doctor_contact) values(?,?,?,?);");
			preparedStatement.setInt(1, newDocId);
			preparedStatement.setString(2, docName);
			preparedStatement.setString(3, docAddr);
			//preparedStatement.setInt(4, Integer.parseInt(docContact));
			preparedStatement.setLong(4, Long.parseLong(docContact));
			preparedStatement.executeUpdate();
			conn.close();
			} 
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Exception :"+e);
		}
		
		return status;
		
	}
	
	public int getNewDocId()
	{
		int last_doc_id=0;
		AccessMySql sql = new AccessMySql();
		
		try {
			System.out.println("Inside getNewDocID");
			ResultSet rS=null;
			Connection conn1 =sql.connectDB();
			preparedStatement = (ClientPreparedStatement) conn1.prepareStatement("Select max(doctor_id) from doctor;");
			rS = preparedStatement.executeQuery();
			
			while(rS.next())
			{
				System.out.println("result set not null");
				last_doc_id=rS.getInt("max(doctor_id)");
				System.out.println("Result set was not null : "+last_doc_id);
			}
			
			if(last_doc_id==0)
			{
				System.out.println("Result set was null");
				//last_doc_id=0;
			}
			
			
			System.out.println("last doc id : "+last_doc_id);
			
			conn1.close();
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Exception :"+e);
			}
		last_doc_id =last_doc_id+ 1;
		
		return last_doc_id;
	}
	
}
