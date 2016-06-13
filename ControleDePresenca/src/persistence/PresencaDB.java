/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import VO.Evento;
import VO.Presenca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author viniciuscustodio
 */
public class PresencaDB {

    public static boolean createPresenca(Presenca presenca) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(presenca);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static List<Presenca> selectPresenca(Evento evento) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p from Presenca p where p.evento = :evento";
        TypedQuery<Presenca> q = em.createQuery(qString, Presenca.class);
        q.setParameter("evento", evento);
        List<Presenca> results = null;

        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    public static boolean alterPresenca(Presenca presenca) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(presenca);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

}
