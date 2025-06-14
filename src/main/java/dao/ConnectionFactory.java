package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

        public static Connection getConnection() {
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgestorescolar"
						,"root",
						"");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
	}
}
