package br.com.acgm;

import br.com.acgm.dao.DaoGeneric;
import br.com.acgm.entity.Pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

//@RequestScoped // toda vez q acontece a requisicao e da a resposta ele termina 
@ViewScoped // mantem os dados na tela apaga os dados mudar de tela ou redirecionar a tela
//@SessionScoped // cria uma sessao pra cada pessoa e mantido os dados so para se fechar ou acabar o tempo 
//@ApplicationScoped // compartilha os dados com todos usuarios
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();

	private DaoGeneric<Pessoa> dao = new DaoGeneric<>();

    public String salvar() {
        dao.salvar(pessoa);

        return "";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DaoGeneric<Pessoa> getDao() {
        return dao;
    }

    public void setDao(DaoGeneric<Pessoa> dao) {
        this.dao = dao;
    }

    /*private String nome;
	private List<String> nomes = new ArrayList<String>();
	
	private String senha;
	private String texto;
	
	private HtmlCommandButton commandButton;
	
	
	public String addNome() {
		
		nomes.add(nome);
		
		if(nomes.size() > 3) {
			
			commandButton.setDisabled(true);
			
			//return "paginanavegacao"; //nevegacao dinamica
			return "paginanavegacao?faces-redirect=true"; //redirecionamento de url
		}
		
		return ""; // null ou vazio fica na msm pagina -> outcome
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
	
	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}
	
	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	} */
}
