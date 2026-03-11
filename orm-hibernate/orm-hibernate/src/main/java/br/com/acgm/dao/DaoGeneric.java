package br.com.acgm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.acgm.HibernateUtil;

public class DaoGeneric<E> {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.persist(entidade);
		
		transaction.commit();
		
	}
	
}
