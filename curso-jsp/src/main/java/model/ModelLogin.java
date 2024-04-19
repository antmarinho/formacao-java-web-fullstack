package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	private String user;
	private String pass;
	
	public ModelLogin() {}
	
	public ModelLogin(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
