package PreviousEntries;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import org.jdesktop.swingx.JXDatePicker;
import com.itextpdf.text.DocumentException;
import com.mysql.cj.jdbc.ClientPreparedStatement;


import DailyEntries.AccessMySql;
import mainWindow.Logs;

public class DbActtivities {

	private ClientPreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	// Object Declared
	AccessMySql sql = new AccessMySql();
	
	Logs log = new Logs();

	private JComboBox<String> dropDown;
	private JXDatePicker startDate;
	private JXDatePicker endDate;
	String s_startDate="";
	String s_endDate="";
	Connection conn;
	Logger logger;

	public DbActtivities() {

		logger = log.startLoggiing(this.getClass());
		
		
		try {
			this.conn = sql.connectDB();
		} catch (Exception e) {
			logger.info("Exception while Connecting to DB : " + e);
		}
	}

	public DbActtivities(JComboBox<String> dropDown, JXDatePicker startDate, JXDatePicker endDate)  {
		
		this.dropDown = dropDown;
		this.startDate = startDate;
		this.endDate = endDate;
		logger = log.startLoggiing(this.getClass());
		//if(startDate.getDate().toString().isEmpty() || endDate.getDate().toString().isEmpty())
		//{
			//try {
				//throw new Exception("Please enter values for Start Date and End date");
			//} catch (Exception e) {
				
			//	JOptionPane.showMessageDialog(null, "Please enter values for Start Date and End date ");
			//	e.printStackTrace();
			//}
		//}
		//else
		//{
			//this.s_startDate= dateFormat.format(this.startDate.getDate());
			//this.s_endDate = dateFormat.format(this.endDate.getDate());
		//}
		
		try {
			this.conn = sql.connectDB();
		} catch (Exception e) {
			logger.info("Exception while Connecting to DB : " + e);
		}
	}

	/*
	 * Returns the list of entries for the doctors whose entry is done in the DB
	 */
	public void getDrNames() {
		try {
			preparedStatement = (ClientPreparedStatement) this.conn
					.prepareStatement("Select distinct bill_payee from bill_Details;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				this.dropDown.addItem(resultSet.getString("bill_payee"));
				//System.out.println(resultSet.getString("bill_payee"));
				logger.info(resultSet.getString("bill_payee"));
			}

		} catch (Exception e) {
			//System.out.println("Exception ...! : " + e);
			logger.info("Exception : " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Get doctor names from Doctor table
	 * **/
	public void getDrNames_DocTable(JComboBox<String> drName)
	{
		try {
			preparedStatement = (ClientPreparedStatement) this.conn.prepareStatement("Select distinct doctor_name from Doctor;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				drName.addItem(resultSet.getString("doctor_name"));
				logger.info(resultSet.getString("doctor_name"));
			}

		} catch (Exception e) {
			logger.info("Exception : " + e);
			e.printStackTrace();
		}

	}
	

	/*
	 * Sets all the Entries in the table as RD : Ready IU : In Use CO :
	 * Completed
	 */
	public void updateTableReadStatus(String readStatus, String DrbillPayee) {
		try {
			if((readStatus.equals("RD"))||(readStatus.equals("CO")))
			{
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				this.s_startDate= dateFormat.format(this.startDate.getDate());
				this.s_endDate = dateFormat.format(this.endDate.getDate());
				
				
			preparedStatement = (ClientPreparedStatement) this.conn
					.prepareStatement("Update bill_details set read_status=? where bill_payee=?  and (bill_date between ? and ?);");
			preparedStatement.setString(1, readStatus);
			preparedStatement.setString(2, DrbillPayee);
			preparedStatement.setString(3, s_startDate);
			preparedStatement.setString(4, s_endDate);
			
			preparedStatement.executeUpdate();
			}
			else{
				preparedStatement = (ClientPreparedStatement) this.conn
						.prepareStatement("Update bill_details set read_status=? where bill_payee=? and read_status='RD' and (bill_date between ? and ?) Limit 1;");
				preparedStatement.setString(1, readStatus);
				preparedStatement.setString(2, DrbillPayee);
				preparedStatement.setString(3, s_startDate);
				preparedStatement.setString(4, s_endDate);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			logger.info("SqlException : "+e);
			e.printStackTrace();
		}
	}

	/*
	 * Returns Detailed PDF Bills
	 */
	public void getDetailedBill() throws FileNotFoundException, DocumentException {
		
		logger.info("Inside getDetailedBill");
		
		/* Set all ENtries as RD for processing */
		updateTableReadStatus("RD", this.dropDown.getSelectedItem().toString());
		// Object Created
		PdfCreator pdf = new PdfCreator(this.dropDown.getSelectedItem().toString(),s_startDate,s_endDate);
		
		try {
			
			preparedStatement = (ClientPreparedStatement) this.conn.prepareStatement(
					"Select * from bill_details where bill_payee = ? and read_status='RD' and  (bill_date between ? and ?);");
			preparedStatement.setString(1, this.dropDown.getSelectedItem().toString());
			preparedStatement.setString(2, s_startDate);
			preparedStatement.setString(3, s_endDate);
			ResultSet resultSet1 = preparedStatement.executeQuery();

			try {
				pdf.openFile();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			while (resultSet1.next()) {
				try {
					preparedStatement = (ClientPreparedStatement) this.conn.prepareStatement(
							"Select bill_id,bill_date,bill_payee,bill_sno,bill_description,bill_qty,bill_rate,bill_amount,bill_total_amount from bill_Details where bill_payee=? and read_status='RD'  and (bill_date between ? and ?) Limit 1;");
					preparedStatement.setString(1, this.dropDown.getSelectedItem().toString());
					preparedStatement.setString(2, s_startDate);
					preparedStatement.setString(3, s_endDate);
					resultSet = preparedStatement.executeQuery();
					
					while (resultSet.next()) {
						String billID = resultSet.getString("bill_id");
						String billDate = resultSet.getString("bill_date");
						String billPayee = resultSet.getString("bill_payee");
						String billSno = resultSet.getString("bill_sno");
						String billDesc = resultSet.getString("bill_description");
						String billQty = resultSet.getString("bill_qty");
						String billRate = resultSet.getString("bill_rate");
						String billAmount = resultSet.getString("bill_amount");
						String billTotalAmount = resultSet.getString("bill_total_amount");
						
						int billIdCount = countBillId(billID);
						
						/* Debugers 
						System.out.println("Setting params:");
						System.out.println("bill Id : " + billID);
						System.out.println("billDate : " + billDate);
						System.out.println("billPayee : " + billPayee);
						System.out.println("billSno : " + billSno);
						System.out.println("billDesc : " + billDesc);
						System.out.println("billQty : " + billQty);
						System.out.println("billRate : " + billRate);
						System.out.println("billTotalAmount : " + billTotalAmount);
						 Debugers */
						logger.info("Setting params:");
						logger.info("billId : "+billID);
						logger.info("billDate : "+billDate);
						logger.info("billPayee : "+billPayee);
						logger.info("billSno : "+billSno);
						logger.info("billDesc : "+billDesc);
						logger.info("billQty : "+billQty);
						logger.info("billRate : "+billRate);
						logger.info("billTotalAmount : "+billTotalAmount);

						pdf.writeInDetailedPDF(billIdCount,billID, billDate, billPayee, billSno, billDesc, billQty, billRate,
									billAmount, billTotalAmount);
						
						updateTableReadStatus("IU", this.dropDown.getSelectedItem().toString());
					}
				} catch (Exception x) {
					logger.info("Exception : "+x);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pdf.closeDoc();
		pdf.OpenPdf();
		updateTableReadStatus("CO", this.dropDown.getSelectedItem().toString());
	}
	
	public int countBillId(String billId) throws SQLException
	{
		preparedStatement = (ClientPreparedStatement) this.conn
				.prepareStatement("select count(*) NumberOfBillIds from bill_details where bill_id =? and (bill_date between ? and ?);");
		preparedStatement.setString(1, billId);
		preparedStatement.setString(2, s_startDate);
		preparedStatement.setString(3, s_endDate);
		
		ResultSet rs = preparedStatement.executeQuery();
		int count = 0;
		while(rs.next())
		{
			count=rs.getInt("NumberOfBillIds");
		}
		return count;
	}
}
