package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUserRepository {
	
	private Connection connection;
	
	public DAOUserRepository() {
		
		connection = SingleConnection.getConnection();
	}
	
	public ModelLogin salvar(ModelLogin mLogin) throws SQLException {
		
		if(mLogin.isNovo()) {
		
			String sql = "INSERT INTO \"user\"(login,pass,nome,email) VALUES (?, ?, ?, ?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, mLogin.getUser());
			stmt.setString(2, mLogin.getPass());
			stmt.setString(3, mLogin.getNome());
			stmt.setString(4, mLogin.getEmail());
			
			stmt.execute();
			
			connection.commit();
			
		}else {
			
			String sql = "UPDATE \"user\" SET login = ?, pass = ?, nome = ?, email = ? WHERE id = " + mLogin.getId() + "";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, mLogin.getUser());
			stmt.setString(2, mLogin.getPass());
			stmt.setString(3, mLogin.getNome());
			stmt.setString(4, mLogin.getEmail());
			
			stmt.executeUpdate();
			
			connection.commit();
			
		}
		
		return this.pesquisar(mLogin.getUser());
		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE UPPER(nome) LIKE UPPER(?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			//ml.setPass(rs.getNString("pass"));
			ml.setUser(rs.getString("login"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaAll() throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\"";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			//ml.setPass(rs.getNString("pass"));
			ml.setUser(rs.getString("login"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public ModelLogin pesquisar(String user) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE login = '" + user + "'";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			mLogin.setId(rs.getLong("id"));
			mLogin.setEmail(rs.getString("email"));
			mLogin.setNome(rs.getString("nome"));
			mLogin.setPass(rs.getString("pass"));
			mLogin.setUser(rs.getString("login"));
			
		}
		
		return mLogin;
	}
	
	public ModelLogin pesquisarId(String id) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			mLogin.setId(rs.getLong("id"));
			mLogin.setEmail(rs.getString("email"));
			mLogin.setNome(rs.getString("nome"));
			mLogin.setPass(rs.getString("pass"));
			mLogin.setUser(rs.getString("login"));
			
		}
		
		return mLogin;
	}
	
	public boolean validarLogin(String login) throws SQLException {
		
		String sql = "SELECT count(1) > 0 AS existe from \"user\" where login = '" + login + "'";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("existe");

	}
	
	public void deletar(String id) throws SQLException {
		
		String sql = "DELETE FROM \"user\" WHERE id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		connection.commit();
	}
	
}
