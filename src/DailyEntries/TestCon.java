package DailyEntries;

public class TestCon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccessMySql con = new AccessMySql();
		try {
			//con.readDataBase();
			con.connectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
