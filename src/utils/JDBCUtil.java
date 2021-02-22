package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	 private static String driver;
	 private static String url;
	 private static String user;
	 private static String password ;
	 private static Connection conn = null;
	 

		public static void init(){
			Properties params=new Properties();
			String configFile = "database.properties";
			InputStream is=JDBCUtil.class.getClassLoader().getResourceAsStream(configFile);
			try {
				params.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver=params.getProperty("driver");
			url=params.getProperty("url");
			user=params.getProperty("username");
			password=params.getProperty("password");
		} 
	 
 
	 public static Connection getConnection() {
		 try {
			 init();
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println(1);
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return null;
	 }
	 
		public static void closeResources(Connection conn)  { 
			try {
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	 
		 public static void closeResources(ResultSet rs) {
			   try {
				    if(rs != null) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 }
		 
		 public static void closeResources(Statement stmt) {
			   try {
				    if(stmt != null) stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 }
}