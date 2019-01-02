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

	public ArrayList<Object[]> QueryAll() {
		try {
			rs = stmt.executeQuery("SELECT * FROM logs");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QueryDatabase();
	}

	private ArrayList<Object[]> QueryDatabase()    {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		
		try {
			while (rs.next()) {				
				Object[] o = {rs.getInt("FirstID"),rs.getInt("SecondID"),
						rs.getInt("ThirdID"),rs.getTimestamp("LogTime"),
						rs.getDouble("Point"),rs.getDouble("SomeDouble")};
				list.add(o);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

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
