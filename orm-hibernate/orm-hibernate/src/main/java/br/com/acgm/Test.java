package br.com.acgm;

import br.com.acgm.dao.DaoGeneric;
import br.com.acgm.model.UsuarioPessoa;

public class Test {
	
	@org.junit.Test
	public void test() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setEmail("acgm@acgm.com");
		pessoa.setIdade(12);
		pessoa.setLogin("acgm");
		pessoa.setNome("acgm");
		pessoa.setSobrenome("acgm");
		pessoa.setSenha("123344");
		
		dao.salvar(pessoa);
		
	}

}
