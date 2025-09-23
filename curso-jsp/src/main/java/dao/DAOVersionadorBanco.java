package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class DAOVersionadorBanco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Connection connection;
	
	public DAOVersionadorBanco() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void salvaArquivoRodado(String nome) throws SQLException {
		
		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, nome);
		
		stmt.execute();
		
	}
	
	
	//aqui verifica se o arquivo sql ja foi usado
	public boolean arquivoSqlRodado(String nome) throws SQLException {
		
		String sql = "SELECT COUNT(1) > 0 as rodado FROM versionadorbranco WHERE arquivo_sql = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, nome);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("rodado");
		
	}

}
