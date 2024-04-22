package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp";
	private static String user = "acgm";
	private static String password = "acgm070191";
	
	private static Connection connection = null;
	
	public SingleConnection() {
		conectar();
	}
	
	static {
		conectar();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");
				
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
