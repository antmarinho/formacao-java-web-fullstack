package model;

import java.io.Serializable;
import java.sql.Date;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String user;
	private String pass;
	private Date dtnsc;
	private boolean useradmin;
	private String perfil;
	private String sexo;
	private String fotoUser;
	private String extensaoFoto;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String complemento;
	private String numero;
	
	
	public ModelLogin() {}
	
	public ModelLogin(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public ModelLogin(Long id, String nome, String email, String user, String pass) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.user = user;
		this.pass = pass;
	}

	public boolean isNovo() {
		
		if(this.id == null)
			return true; // inserir
		else if(this.id != null && this.id > 0)
			return false; // atualizar
		
		return id == null;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getExtensaoFoto() {
		return extensaoFoto;
	}

	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDtnsc() {
		return dtnsc;
	}

	public void setDtnsc(Date dtnsc) {
		this.dtnsc = dtnsc;
	}
	
}
