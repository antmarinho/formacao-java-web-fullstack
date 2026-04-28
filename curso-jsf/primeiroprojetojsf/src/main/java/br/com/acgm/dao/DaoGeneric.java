package br.com.acgm.dao;

import br.com.acgm.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.awt.*;
import java.util.List;

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

    public void deletarId(T entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Object id = JPAUtil.getId(entidade);

        entityManager.createQuery("DELETE FROM " + entidade.getClass().getCanonicalName() + " WHERE id = " + id).executeUpdate();

        entityTransaction.commit();

        entityManager.close();

    }

    public List<T> getListaEntidade(Class<T> entidade) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        List<T> entidades = entityManager.createQuery("FROM " + entidade.getName()).getResultList();

        entityTransaction.commit();

        entityManager.close();

        return entidades;
    }
}
