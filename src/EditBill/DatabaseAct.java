package EditBill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Logger;

import javax.swing.JComboBox;

//import com.mysql.jdbc.PreparedStatement;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import DailyEntries.AccessMySql;
import mainWindow.Logs;

public class DatabaseAct {
	
	private ClientPreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	JComboBox<String> dropDownName;
	JComboBox<String> dropDownBillId;
	Connection conn;
	Logger logger;
	Logs log = new Logs();
	AccessMySql sql = new AccessMySql();
	

	public DatabaseAct(JComboBox<String> dropDownName,JComboBox<String> dropDownBillId) {
		this.dropDownName = dropDownName;
		this.dropDownBillId = dropDownBillId;
		
		logger = log.startLoggiing(this.getClass());
		try {
			this.conn = sql.connectDB();
		} catch (Exception e) {
			//System.out.println("Exception while Connecting to DB : " + e);
			logger.info("Exception while Connecting to DB : " + e);
		}
	}
	
	public DatabaseAct()
	{
		logger = log.startLoggiing(this.getClass());
		try {
			this.conn = sql.connectDB();
		} catch (Exception e) {
			//System.out.println("Exception while Connecting to DB : " + e);
			logger.info("Exception while Connecting to DB : " + e);
		}
	}
	
	/*
	 * Returns the list of entries for the doctors whose entry is done in the DB
	 */
	public void getDrNames(JComboBox<String> dropDownName) {
		
		try {
			preparedStatement = (ClientPreparedStatement) this.conn
					.prepareStatement("Select distinct bill_payee from bill_Details;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dropDownName.addItem(resultSet.getString("bill_payee"));
				//System.out.println(resultSet.getString("bill_payee"));
				logger.info(resultSet.getString("bill_payee"));
			}
			
			

		} catch (Exception e) {
			//System.out.println("Exception ...! : " + e);
			logger.info("Exception : " + e);
			e.printStackTrace();
		}
		
		
	}
	/*
	 * Returns the list of entries for the BillIds for Doctor selected whose entry is done in the DB
	 */
	public void getBillIds(JComboBox<String> dropDownName,JComboBox<String> dropDown) {
		
		try {
			preparedStatement = (ClientPreparedStatement) this.conn
					.prepareStatement("Select distinct bill_id from bill_Details where bill_payee=?;");
			preparedStatement.setString(1, dropDownName.getSelectedItem().toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//this.dropDownName.addItem(resultSet.getString("bill_payee"));
				dropDown.addItem(resultSet.getString("bill_id"));
				System.out.println(resultSet.getString("bill_id"));
				logger.info(resultSet.getString("bill_id"));
			}

		} catch (Exception e) {
			//System.out.println("Exception ...! : " + e);
			logger.info("Exception : " + e);
			e.printStackTrace();
		}
	}
}
