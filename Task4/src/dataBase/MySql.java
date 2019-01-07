package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class MySql {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private HashMap<Integer, String> hash;

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
		creatKeys();
	}

	private void creatKeys() {
		hash = new HashMap<Integer, String>();

		hash.put(2128259830, "Example 1");
		hash.put(1149748017, "Example 2");
		hash.put(-683317070, "Example 3");
		hash.put(1193961129, "Example 4");
		hash.put(1577914705, "Example 5");
		hash.put(-1315066918, "Example 6");
		hash.put(-1377331871, "Example 7");
		hash.put(306711633, "Example 8");
		hash.put(919248096, "Example 9");		
	}
	
	public String getScore(boolean ourScore,String id) {
		String points[] = new String[9];

		for(Integer map : hash.keySet()) {
			double point = getScore(map,ourScore,id);
			String s = hash.get(map);
			map = Integer.parseInt(""+s.charAt(s.length()-1));
			points[map-1] = s+": "+point;
		}
		String result = Arrays.toString(points);
		System.out.println(Arrays.toString(points));
		return result.substring(1, result.length()-1);
	}
	
	public ArrayList<Object[]> Query(String id, boolean myScore) {
		String s = "WHERE FirstID ";
		if(myScore) s += "= "+id;
		else s += "!= "+id;

		try {
			rs = stmt.executeQuery("SELECT * FROM logs "+s);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return QueryDatabase();
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

	private double getScore(int map,boolean ourScore, String id) {
		double point = 0;
		String s = "WHERE FirstID ";

		if(ourScore) s += "= 315392852";
		else s+= "!= 315392852";
		
		try {
			rs = stmt.executeQuery("SELECT MAX(Point) FROM logs "+s+" AND SomeDouble = "+map);
			rs.next();
			point = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return point;
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


	private String translateFile(int map) {
		if(hash.containsKey(map)) return hash.get(map);
		return "Unknown";		
	}

}
