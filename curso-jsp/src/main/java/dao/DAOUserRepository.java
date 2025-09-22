package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import dto.DTOGraficoSalarioUser;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUserRepository {
	
	private Connection connection;
	
	public DAOUserRepository() {
		
		connection = SingleConnection.getConnection();
	}
	
	public ModelLogin salvar(ModelLogin mLogin, Long user) throws SQLException {
		
		if(mLogin.isNovo()) {
		
			String sql = "INSERT INTO \"user\"(login,pass,nome,email,id_user,perfil,sexo,cep,logradouro,bairro,localidade,uf,complemento,numero,dtnsc,renda) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, mLogin.getUser());
			stmt.setString(2, mLogin.getPass());
			stmt.setString(3, mLogin.getNome());
			stmt.setString(4, mLogin.getEmail());
			stmt.setLong(5, user);
			stmt.setString(6, mLogin.getPerfil());
			stmt.setString(7, mLogin.getSexo());
			stmt.setString(8, mLogin.getCep());
			stmt.setString(9, mLogin.getLogradouro());
			stmt.setString(10, mLogin.getBairro());
			stmt.setString(11, mLogin.getLocalidade());
			stmt.setString(12, mLogin.getUf());
			stmt.setString(13, mLogin.getComplemento());
			stmt.setString(14, mLogin.getNumero());
			stmt.setDate(15, mLogin.getDtnsc());
			stmt.setDouble(16, mLogin.getRenda());

			stmt.execute();
			
			connection.commit();
			
			if(mLogin.getFotoUser() != null && mLogin.getFotoUser().isEmpty()) {
				
				sql = "UPDATE UPDATE \"user\" SET fotouser = ?, extensaofoto = ? WHERE login = ?";
				
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, mLogin.getFotoUser());
				stmt.setString(1, mLogin.getExtensaoFoto());
				stmt.setString(1, mLogin.getUser());
				
				stmt.execute();
				
				connection.commit();
				
			}
			
		}else {
			
			String sql = "UPDATE \"user\" SET login = ?, pass = ?, nome = ?, email = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, complemento = ?, numero = ?, dtnsc = ?, renda = ? WHERE id = " + mLogin.getId() + "";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, mLogin.getUser());
			stmt.setString(2, mLogin.getPass());
			stmt.setString(3, mLogin.getNome());
			stmt.setString(4, mLogin.getEmail());
			stmt.setString(5, mLogin.getPerfil());
			stmt.setString(6, mLogin.getSexo());
			stmt.setString(7, mLogin.getCep());
			stmt.setString(8, mLogin.getLogradouro());
			stmt.setString(9, mLogin.getBairro());
			stmt.setString(10, mLogin.getLocalidade());
			stmt.setString(11, mLogin.getUf());
			stmt.setString(12, mLogin.getComplemento());
			stmt.setString(13, mLogin.getNumero());
			stmt.setDate(14, mLogin.getDtnsc());
			stmt.setDouble(15, mLogin.getRenda());
			
			stmt.executeUpdate();
			
			connection.commit();
			
			if(mLogin.getFotoUser() != null && mLogin.getFotoUser().isEmpty()) {
				
				sql = "UPDATE UPDATE \"user\" SET fotouser = ?, extensaofoto = ? WHERE id = ?";
				
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, mLogin.getFotoUser());
				stmt.setString(1, mLogin.getExtensaoFoto());
				stmt.setLong(3, mLogin.getId());
				
				stmt.execute();
				
				connection.commit();
				
			}
			
		}
		
		return this.pesquisar(mLogin.getUser(),user);
		
	}
	
	public int consultaUsuarioListTotalPaginaPaginacao(String nome, Long user) throws SQLException {
		
		String sql = "SELECT COUNT(1) AS total FROM \"user\" WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS false AND id_user = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, "%" + nome + "%");
		stmt.setLong(2, user);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		Double cadastros = rs.getDouble("total");
		
		Double qtdPagina = (cadastros/5) % 2;
		
		if(qtdPagina > 0)
			qtdPagina ++;
		
		return qtdPagina.intValue();
		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long user) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS false AND id_user = ? LIMIT 5";
		
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
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaUsuarioListOffset(String nome, Long user, int offset) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS false AND id_user = ? OFFSET " + offset + " LIMIT 5";
		
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
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaAllPaginada(Long user, Integer offset) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE useradmin IS false AND id_user = " + user + " ORDER BY none OFFSET " + offset + " LIMIT 5";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			//ml.setPass(rs.getNString("pass"));
			ml.setUser(rs.getString("login"));
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaAll(Long user) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE useradmin IS false AND id_user = " + user + "LIMIT 5";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			//ml.setPass(rs.getNString("pass"));
			ml.setUser(rs.getString("login"));
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaAllRelatorio(Long user) throws SQLException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE useradmin IS false AND id_user = " + user;
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			ml.setUser(rs.getString("login"));
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			ml.setFones(this.listaFone(ml.getId()));
			ml.setDtnsc(rs.getDate("dtnsc"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelLogin> consultaAllRelatorio(Long user, String dataI, String dataF) throws SQLException, ParseException {
		
		List<ModelLogin> users = new ArrayList<>();
		
		String sql = "SELECT * FROM \"user\" WHERE useradmin IS false AND id_user = " + user + " AND dtnsc >= ? AND dtnsc <= ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(dataI))));
		stmt.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(dataF))));
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelLogin ml = new ModelLogin();
			
			ml.setEmail(rs.getString("email"));
			ml.setId(rs.getLong("id"));
			ml.setNome(rs.getString("nome"));
			ml.setUser(rs.getString("login"));
			ml.setPerfil(rs.getString("prefil"));
			ml.setSexo(rs.getString("sexo"));
			ml.setFones(this.listaFone(ml.getId()));
			ml.setDtnsc(rs.getDate("dtnsc"));
			
			users.add(ml);
		}
		
		return users ;
	}
	
	public List<ModelTelefone> listaFone(Long id) throws SQLException {
		
		List<ModelTelefone> fones = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM telefone WHERE user_id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			ModelTelefone fone = new ModelTelefone();
			
			fone.setId(rs.getLong("id"));
			fone.setNumero(rs.getNString("numero"));
			fone.setUser_id(this.pesquisarId(rs.getLong("user_id")));
			fone.setUser_cad(this.pesquisarId(rs.getLong("user_cad_id")));
			
			fones.add(fone);
			
		}
		
		return fones;
		
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
			mLogin.setPerfil(rs.getString("perfil"));
			mLogin.setSexo(rs.getString("sexo"));
			mLogin.setFotoUser(rs.getNString("fotouser"));
			mLogin.setCep(rs.getNString("cep"));
			mLogin.setLogradouro(rs.getNString("logradouro"));
			mLogin.setLocalidade(rs.getNString("localidade"));
			mLogin.setBairro(rs.getNString("bairro"));
			mLogin.setUf(rs.getNString("uf"));
			mLogin.setComplemento(rs.getNString("complemento"));
			mLogin.setNumero(rs.getNString("numero"));
			mLogin.setDtnsc(rs.getDate("dtnsc"));
			mLogin.setRenda(rs.getDouble("renda"));
			
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
			mLogin.setPerfil(rs.getString("perfil"));
			mLogin.setSexo(rs.getString("sexo"));
			mLogin.setFotoUser(rs.getNString("fotouser"));
			mLogin.setCep(rs.getNString("cep"));
			mLogin.setLogradouro(rs.getNString("logradouro"));
			mLogin.setLocalidade(rs.getNString("localidade"));
			mLogin.setBairro(rs.getNString("bairro"));
			mLogin.setUf(rs.getNString("uf"));
			mLogin.setComplemento(rs.getNString("complemento"));
			mLogin.setNumero(rs.getNString("numero"));
			mLogin.setDtnsc(rs.getDate("dtnsc"));
			mLogin.setRenda(rs.getDouble("renda"));
			
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
			mLogin.setPerfil(rs.getString("perfil"));
			mLogin.setSexo(rs.getString("sexo"));
			mLogin.setFotoUser(rs.getNString("fotouser"));
			mLogin.setCep(rs.getNString("cep"));
			mLogin.setLogradouro(rs.getNString("logradouro"));
			mLogin.setLocalidade(rs.getNString("localidade"));
			mLogin.setBairro(rs.getNString("bairro"));
			mLogin.setUf(rs.getNString("uf"));
			mLogin.setComplemento(rs.getNString("complemento"));
			mLogin.setNumero(rs.getNString("numero"));
			mLogin.setDtnsc(rs.getDate("dtnsc"));
			mLogin.setRenda(rs.getDouble("renda"));
			
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
			mLogin.setPerfil(rs.getString("perfil"));
			mLogin.setSexo(rs.getString("sexo"));
			mLogin.setFotoUser(rs.getNString("fotouser"));
			mLogin.setExtensaoFoto(rs.getNString("extensaofoto"));
			mLogin.setCep(rs.getNString("cep"));
			mLogin.setLogradouro(rs.getNString("logradouro"));
			mLogin.setLocalidade(rs.getNString("localidade"));
			mLogin.setBairro(rs.getNString("bairro"));
			mLogin.setUf(rs.getNString("uf"));
			mLogin.setComplemento(rs.getNString("complemento"));
			mLogin.setNumero(rs.getNString("numero"));
			mLogin.setDtnsc(rs.getDate("dtnsc"));
			mLogin.setRenda(rs.getDouble("renda"));
			
		}
		
		return mLogin;
	}
	
	public ModelLogin pesquisarId(Long id) throws SQLException {
		
		ModelLogin mLogin = new ModelLogin();
		
		String sql = "SELECT * FROM \"user\" WHERE id = ? AND useradmin IS false";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			mLogin.setId(rs.getLong("id"));
			mLogin.setEmail(rs.getString("email"));
			mLogin.setNome(rs.getString("nome"));
			mLogin.setPass(rs.getString("pass"));
			mLogin.setUser(rs.getString("login"));
			mLogin.setPerfil(rs.getString("perfil"));
			mLogin.setSexo(rs.getString("sexo"));
			mLogin.setFotoUser(rs.getNString("fotouser"));
			mLogin.setExtensaoFoto(rs.getNString("extensaofoto"));
			mLogin.setCep(rs.getNString("cep"));
			mLogin.setLogradouro(rs.getNString("logradouro"));
			mLogin.setLocalidade(rs.getNString("localidade"));
			mLogin.setBairro(rs.getNString("bairro"));
			mLogin.setUf(rs.getNString("uf"));
			mLogin.setComplemento(rs.getNString("complemento"));
			mLogin.setNumero(rs.getNString("numero"));
			mLogin.setDtnsc(rs.getDate("dtnsc"));
			mLogin.setRenda(rs.getDouble("renda"));
			
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
	
	public int totalPagina(Long user) throws SQLException {
		
		String sql = "SELECT COUNT(1) AS total FROM \"user\" WHERE id = " + user;
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		Double cadastros = rs.getDouble("total");
		
		Double qtdPagina = (cadastros/5) % 2;
		
		if(qtdPagina > 0)
			qtdPagina ++;
		
		return qtdPagina.intValue();

	}

	public DTOGraficoSalarioUser montarGraficoMediaSalario(Long user) throws SQLException {

		String sql = "SELECT AVG(renda) AS media_salario, perfil FROM \"user\" WHERE id_user = ? GROUP BY perfil";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setLong(1, user);

		ResultSet rs = stmt.executeQuery();

		List<String> perfils = new ArrayList<>();
		List<Double> salarios = new ArrayList<>();

		DTOGraficoSalarioUser dto = new DTOGraficoSalarioUser();

		while(rs.next()) {

			Double media = rs.getDouble("media_salario");
			String perfil = rs.getString("perfil");

			perfils.add(perfil);
			salarios.add(media);

		}

		dto.setPerfils(perfils);
		dto.setSalarios(salarios);

		return dto;

	}

    public DTOGraficoSalarioUser montarGraficoMediaSalario(Long user, String dataI, String dataF) throws SQLException, ParseException {

        String sql = "SELECT AVG(renda) AS media_salario, perfil FROM \"user\" WHERE id_user = ? AND dtnsc >= ? AND dtnsc <= ? GROUP BY perfil";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setLong(1, user);
        stmt.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(dataI))));
        stmt.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd-mm-yyyy").parse(dataF))));

        ResultSet rs = stmt.executeQuery();

        List<String> perfils = new ArrayList<>();
        List<Double> salarios = new ArrayList<>();

        DTOGraficoSalarioUser dto = new DTOGraficoSalarioUser();

        while(rs.next()) {

            Double media = rs.getDouble("media_salario");
            String perfil = rs.getString("perfil");

            perfils.add(perfil);
            salarios.add(media);

        }

        dto.setPerfils(perfils);
        dto.setSalarios(salarios);

        return dto;

    }
}
