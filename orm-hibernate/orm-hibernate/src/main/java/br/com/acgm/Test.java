package br.com.acgm;

import java.util.List;

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
	
	@org.junit.Test
	public void testeDeletar() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		UsuarioPessoa pessoa = dao.pesquisar(1L,UsuarioPessoa.class);
		
		dao.deletarId(pessoa);
		
	}
	
	@org.junit.Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> dao = new DaoGeneric<>();
		
		List<UsuarioPessoa> list = dao.listar(UsuarioPessoa.class);
		
		for(UsuarioPessoa pessoa : list) {
			
			System.out.println(pessoa);
			System.out.println("--------------------------------");
			
		}
		
	}

}
