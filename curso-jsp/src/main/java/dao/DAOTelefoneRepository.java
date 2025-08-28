package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelTelefone;

public class DAOTelefoneRepository {
	
	private Connection connection;
	
	private DAOUserRepository dao = new DAOUserRepository();
	
	public DAOTelefoneRepository() {
		
		connection = SingleConnection.getConnection();
		
	}
	
	public void salvar(ModelTelefone telefone) throws SQLException {
		
		String sql = "INSERT INTO telefone(numero,user_id,user_cad_id) VALUE(?,?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, telefone.getNumero());
		stmt.setLong(2, telefone.getUser_id().getId());
		stmt.setLong(3, telefone.getUser_cad().getId());
		
		stmt.execute();
		
		connection.commit();
		
	}
	
	
	public List<ModelTelefone> listaFone(Long id) throws SQLException {
		
		List<ModelTelefone> fones = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM telefone WHERE user_id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelTelefone fone = new ModelTelefone();
			
			fone.setId(rs.getLong("id"));
			fone.setNumero(rs.getNString("numero"));
			fone.setUser_id(dao.pesquisarId(rs.getLong("user_id")));
			fone.setUser_cad(dao.pesquisarId(rs.getLong("user_cad_id")));
			
			fones.add(fone);
			
		}
		
		return fones;
		
	}
	
	public void deletar(Long id) throws SQLException {
		
		String sql = "DELETE FROM telefone where id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, id);
		
		stmt.executeUpdate();
		
		connection.commit();
		
	}

}
