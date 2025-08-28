package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private ModelLogin user_id;
	private ModelLogin user_cad;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public ModelLogin getUser_id() {
		return user_id;
	}
	
	public void setUser_id(ModelLogin user_id) {
		this.user_id = user_id;
	}
	
	public ModelLogin getUser_cad() {
		return user_cad;
	}
	
	public void setUser_cad(ModelLogin user_cad) {
		this.user_cad = user_cad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id, other.id);
	}
	
}
