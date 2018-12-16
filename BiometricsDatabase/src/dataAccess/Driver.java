package dataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Driver {

	String dbName = "Biometrics";

	Properties properties = new Properties();

	String connectionURL = "jdbc:mysql://localhost:3306/";

	public void initialize() {

		properties.setProperty("user", "root");
		properties.setProperty("password", "computer123");
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");

		createDB(connectionURL, properties, dbName);

		connectionURL = connectionURL + dbName;

		createTables(connectionURL, properties);

	}

	public void createDB(String connectionURL, Properties properties, String dbName) {
		try {

			Connection myConn = myConn = DriverManager.getConnection(connectionURL, properties);
			Statement myStat = myStat = myConn.createStatement();
			int result = myStat.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);

			myStat.close();
			myConn.close();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void createTables(String connectionURL, Properties properties) {

		try {

			Connection myConn = DriverManager.getConnection(connectionURL, properties);

			Statement myStat = myConn.createStatement();

			String user = "CREATE TABLE IF NOT EXISTS USER (\n" + "	id INT PRIMARY KEY,\n"
					+ "	name TEXT NOT NULL,\n" + "	email TEXT NOT NULL,\n" + "img LONGBLOB NOT NULL\n" + ");";

			myStat.execute(user);

			String otherUser = "CREATE TABLE IF NOT EXISTS OTHERUSER (\n" + "id double PRIMARY KEY,\n"
					+ "	name text NOT NULL\n" + ");";

			myStat.execute(otherUser);

			myStat.close();
			myConn.close();

		} catch (SQLException sqlException) {

			sqlException.printStackTrace();

		}
	}

	public void insertUser(String name, String email, String path) {
		try {
			
			initialize();
			
			File image = new File(path);
			FileInputStream fis = new FileInputStream(image);

			String sql = "insert into USER (id, name, email, img) values (?, ?, ?, ?)";

			Connection myConn = myConn = DriverManager.getConnection(connectionURL, properties);
			
			PreparedStatement pst1 = myConn.prepareStatement("SELECT (MAX(id)+1) FROM USER");
            ResultSet rs = pst1.executeQuery();
            String user_id = "";
            while(rs.next())
            {
                user_id = rs.getString(1);
            }
			
			PreparedStatement pst = myConn.prepareStatement(sql);

			if(user_id == null)
				pst.setInt(1, 0);
			else
				pst.setInt(1, Integer.parseInt(user_id));
			pst.setString(2, name);
			pst.setString(3, email);
			pst.setBinaryStream(4, (InputStream) fis, (int) path.length());

			pst.executeUpdate();

			pst.close();
			myConn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
