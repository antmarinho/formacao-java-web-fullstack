package br.com.acgm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

//@RequestScoped // toda vez q acontece a requisicao e da a resposta ele termina 
//@ViewScoped // mantem os dados na tela apaga os dados mudar de tela ou redirecionar a tela
//@SessionScoped // cria uma sessao pra cada pessoa e mantido os dados so para se fechar ou acabar o tempo 
//@ApplicationScoped // compartilha os dados com todos usuarios
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private String nome;
	private List<String> nomes = new ArrayList<String>();
	
	
	public String addNome() {
		
		nomes.add(nome);
		
		return "";
	}

	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getNomes() {
		return nomes;
	}
	
	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}
	
}
