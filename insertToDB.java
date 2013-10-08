import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class insertToDB {
	Connection conn = null;
	long totTime;
	public insertToDB(long TIME, String EMPLOYEEID){		
		
	}

	public void initateConnection() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "db";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "root";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeConnection(){
		try{
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] insert(long time, String employeeId){
		long[] status = getStatus(employeeId);
	
		if(status[3]!=status[1] || status[1]==0){
			try{
				Statement st = conn.createStatement();
				int val = st.executeUpdate("INSERT INTO `Time_Clock` (`Id_Number`, `Time_In`) VALUES ('"+employeeId+"', '"+time+"')");
			}
			catch (SQLException s){
				s.printStackTrace();
				System.out.println("SQL statement is not executed!");
			}
			String[] re = {"Thank you for checking in!"};
			return re;
		}

		else {
			try{
				Statement st = conn.createStatement();
				st.executeUpdate("UPDATE `Time_Clock` SET Time_Out='"+time+"' WHERE id='" + status[0]  + "'");
				Statement sta = conn.createStatement();
				 totTime = getTotalTime(status[0]);
				sta.executeUpdate("UPDATE `Time_Clock` SET Total_Time='"+totTime+"' WHERE id='" + status[0]  + "'");
			}
			catch (SQLException s){
				s.printStackTrace();
				System.out.println("SQL statement is not executed!");
			}
			String total = ""+totTime;
			String[] re = {"Thank you for checking out!",total};
			return re;
		}
	}


	public long[] getStatus(String str){
		long timeIn = 0;
		long timeOut = 0;
		int index = 0;
		try{
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Time_Clock  WHERE `Id_Number` = '"+str+"' ORDER BY Id DESC LIMIT 1");
			while (res.next()) {
				index = res.getInt("Id");
			}
		}
		catch (SQLException s){
			s.printStackTrace();
		}
		try{
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Time_Clock  WHERE `Id` = '"+index+"'");
			while(res.next()){
				timeIn = res.getLong("Time_In");
			}
		}
		catch (SQLException s){
			s.printStackTrace();
		}

		try{
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Time_Clock  WHERE `Id` = '"+index+"'");
			while(res.next()){
				timeOut = res.getLong("Time_out");
			}
		}
		catch (SQLException s){
			s.printStackTrace();
		}
		long totalTime = Math.abs(timeOut - timeIn);
		long [] returnA = {index,timeIn,timeOut,totalTime};

		return returnA;

	}
	
	public long getTotalTime(long in){
		long timeIn = 0;
		long timeOut = 0;
		int index = (int)in;
		try{
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Time_Clock  WHERE `Id` = '"+index+"'");
			while(res.next()){
				timeIn = res.getLong("Time_In");
			}
		}
		catch (SQLException s){
			s.printStackTrace();
		}

		try{
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM Time_Clock  WHERE `Id` = '"+index+"'");
			while(res.next()){
				timeOut = res.getLong("Time_out");
			}
		}
		catch (SQLException s){
			s.printStackTrace();
		}
		long totalTime = timeOut - timeIn;
		return totalTime;
	}
}
