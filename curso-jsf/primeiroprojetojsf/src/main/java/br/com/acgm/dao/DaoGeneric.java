package br.com.acgm.dao;

import br.com.acgm.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoGeneric<T> {

    public void salvar(T entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.persist(entidade);

        entityTransaction.commit();

        entityManager.close();

    }

    public T merge(T entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        T ent = entityManager.merge(entidade);

        entityTransaction.commit();

        entityManager.close();

        return ent;

    }

    /*public void deletar(T entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.remove(entidade);

        entityTransaction.commit();

        entityManager.close();

    }*/

    public void deletarId(T entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Object id = JPAUtil.getId(entidade);

        entityManager.createQuery("DELETE FROM " + entidade.getClass().getCanonicalName() + " WHERE id = " + id).executeUpdate();

        entityTransaction.commit();

        entityManager.close();

    }

}
