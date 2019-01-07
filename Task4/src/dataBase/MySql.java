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

/**
 * Able to access to a database with certain queries 
 *
 */
public class MySql {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private HashMap<Integer, String> hash;

	public MySql() {
		// Create configuration connection
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
	/**
	 * Calculate high score of each examples maps
	 * @param ourScore - true check only our score, false for others
	 * @param id - which id to check
	 * @return String that represent top scores
	 */
	public String getScore(boolean ourScore,String id) {
		String points[] = new String[9];
		
		for(Integer map : hash.keySet()) {
			double point = getScore(map,ourScore,id);
			String s = hash.get(map); // Calculate high score for this map
			map = Integer.parseInt(""+s.charAt(s.length()-1)); // Save in the right place such that example 1=[0], example 2 =[1] etc.
			points[map-1] = s + ": "+point;
		}
		String result = Arrays.toString(points);
		return result.substring(1, result.length()-1);
	}
	/**
	 * change ResultSet accord specific id
	 * @param id - which id to filter
	 * @param myScore - if to include the id or not
	 * @return ArrayList of data query
	 */
	public ArrayList<Object[]> Query(String id, boolean myScore) {
		String s = "WHERE FirstID ";
		if(myScore) s += "= ";
		else s += "!= ";

		s+=id;
		
		try {
			rs = stmt.executeQuery("SELECT * FROM logs "+s);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return QueryDatabase();
	}
	/**
	 * When we finish with the dataBase close connection
	 */
	public void closeConnection() {
		try {
			if(stmt!=null) stmt.close();

			if(rs!=null) rs.close();

			if(con!=null)  con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Calculate high score for specific map
	 * @param map - which map to run
	 * @param ourScore - to include the id or not
	 * @param id - id to calculate
	 * @return double of top point for this map
	 */
	private double getScore(int map,boolean ourScore, String id) {
		double point = 0;
		String s = "WHERE FirstID ";

		if(ourScore) s += "= ";
		else s+= "!= ";
		s+=id;
		
		try {
			rs = stmt.executeQuery("SELECT MAX(Point) FROM logs "+s+" AND SomeDouble = "+map);
			rs.next();
			point = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return point;
	}

	/**
	 * Connect each map to his name
	 * Now we can quickly get the translate
	 */
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

	/**
	 * Just run on the result and insert into a ArrayList
	 * @return ArrayList that represent this dataBase
	 */
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
	/**
	 * Show file name as a readable string name
	 * @param map - map id
	 * @return - String map name 
	 */
	private String translateFile(int map) {
		if(hash.containsKey(map)) return hash.get(map);
		return "Unknown";		
	}

}
