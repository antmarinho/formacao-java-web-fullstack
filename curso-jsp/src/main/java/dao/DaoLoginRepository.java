package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import model.ModelLogin;


public class DaoLoginRepository {
	
	private Connection connection;
	
	public DaoLoginRepository() {
		
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin mLogin) throws Exception {
		
		String sql = "SELECT * FROM \"user\" WHERE login = ? AND pass = ?";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, mLogin.getUser());
		statement.setString(2, mLogin.getPass());
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}
		
		return false;
	}

}
