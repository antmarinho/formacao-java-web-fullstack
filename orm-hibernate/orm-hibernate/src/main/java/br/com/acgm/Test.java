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
		pessoa.setNome("acgm66");
		pessoa.setSobrenome("acgm");
		pessoa.setSenha("123344");
		
		dao.salvar(pessoa);
		
		
	}
	
	@org.junit.Test
	public void testBuscar() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setId(1L);
		
		pessoa = dao.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@org.junit.Test
	public void testBuscar2() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		UsuarioPessoa pessoa = dao.pesquisar(4L,UsuarioPessoa.class);
		
		System.out.println(pessoa);
		
	}
	
	@org.junit.Test
	public void testUpdate() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		UsuarioPessoa pessoa = dao.pesquisar(4L,UsuarioPessoa.class);
		
		pessoa.setIdade(50);
		pessoa.setNome("aodj");
		
		dao.update(pessoa);
		
		System.out.println(pessoa);
		
	}

}
