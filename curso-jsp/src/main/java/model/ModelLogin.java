package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String user;
	private String pass;
	private boolean useradmin;
	private String perfil;
	
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
	
	
}
