/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cp.data.crud.interfaces;

import java.util.Collections;
import java.util.List;
import com.cp.util.AppLog;

import jakarta.persistence.EntityManager;

/**
 *
 * @author utfpr
 */
public abstract class AbstractCrud<T> {
    
    private Class<T> entityClass;

    protected AbstractCrud(Class<T> entityClass) {
        this.entityClass = entityClass;
    }



    protected abstract EntityManager getEntityManager();


    protected abstract  void close();



    public Exception persist(T entity) {
        try {
            System.out.println("iniciando ########");
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            System.out.println("foi o persist $$$$$$");
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
            AppLog.getInstance().info("Registro inserido com sucesso pela classe: " + this.getClass().getName());
            return null;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            AppLog.getInstance().warn("Erro ao inserir no banco de dados: " + this.getClass().getName() + "==>" + e.getMessage());
            return e;
        }
    }

    public Exception merge(T entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
            AppLog.getInstance().info("Registro alterado com sucesso pela classe: " + this.getClass().getName());
            return null;
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            return e;
        }
    }

    public Exception remove(T entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(getEntityManager().merge(entity));
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
            AppLog.getInstance().info("Registro removido com sucesso pela classe: " + this.getClass().getName());
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> getAll() {
        try {
            jakarta.persistence.criteria.CriteriaQuery cq;
            cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return Collections.emptyList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


}
