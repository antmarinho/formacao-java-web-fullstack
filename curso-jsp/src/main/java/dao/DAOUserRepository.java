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
	
	public ModelLogin salvar(ModelLogin mLogin, Long user) throws SQLException {
		
		if(mLogin.isNovo()) {
		
			String sql = "INSERT INTO \"user\"(login,pass,nome,email,id_user) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, mLogin.getUser());
			stmt.setString(2, mLogin.getPass());
			stmt.setString(3, mLogin.getNome());
			stmt.setString(4, mLogin.getEmail());
			stmt.setLong(5, user);

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
		
		return this.pesquisar(mLogin.getUser(),user);
		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long user) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS false AND id_user = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		stmt.setLong(2, user);
		
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
	
	public List<ModelLogin> consultaAll(Long user) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE useradmin IS false AND id_user = " + user;
		
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
	
	public ModelLogin pesquisarLogado(String user) throws SQLException {
		
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
			mLogin.setUseradmin(rs.getBoolean("useradmin"));
			
		}
		
		return mLogin;
	}
	
	public ModelLogin pesquisar(String user) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE login = '" + user + "' AND useradmin IS false";
		
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
	
	public ModelLogin pesquisar(String user, Long userLogado) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE login = '" + user + "' AND useradmin IS false AND id_user = " + userLogado;
		
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
	
	public ModelLogin pesquisarId(String id, Long user) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE id = ? AND useradmin IS false AND id_user = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		stmt.setLong(2, user);
		
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
		
		String sql = "DELETE FROM \"user\" WHERE id = ? AND useradmin IS false";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, Long.parseLong(id));
		
		stmt.executeUpdate();
		
		connection.commit();
	}
	
}
