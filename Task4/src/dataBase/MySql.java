package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class MySql {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public MySql() {
		Properties properties = new Properties();
		properties.setProperty("user", "student");
		properties.setProperty("password", "student");
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");

		try {
			con =  DriverManager.getConnection(
					"jdbc:mysql://ariel-oop.xyz:3306/oop", properties);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<Object[]> QueryWhereId(String id) {
		String s = "WHERE FirstID = "+id;
		try {
			rs = stmt.executeQuery("SELECT * FROM logs "+s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QueryDatabase();
	}

	public ArrayList<Object[]> QueryAllNotId(String id) {
		String s = "WHERE FirstID != "+id;
		try {
			rs = stmt.executeQuery("SELECT * FROM logs "+s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QueryDatabase();
	}

	private ArrayList<Object[]> QueryDatabase()  {
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		try {
			while (rs.next()) {				
				Object[] o = {rs.getInt("FirstID"),rs.getInt("SecondID"),
						rs.getInt("ThirdID"),rs.getTimestamp("LogTime"),
						rs.getDouble("Point"), translateFile(rs.getInt("SomeDouble"))};
				list.add(o);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private String translateFile(int x) {
		switch(x) {
		case 2128259830 : return "Example 1";

		case 1149748017 : return "Example 2";

		case -683317070 : return "Example 3";

		case 1193961129 : return "Example 4";

		case 1577914705 : return "Example 5";

		case -1315066918 : return "Example 6";

		case -1377331871 : return "Example 7";

		case 306711633 : return "Example 8";

		case 919248096 : return "Example 9";
		
		default : return "Unknown";
		}
	}

	public void closeConnection() {
		try {
			if(stmt!=null) stmt.close();

			if(rs!=null) rs.close();

			if(con!=null)  con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
