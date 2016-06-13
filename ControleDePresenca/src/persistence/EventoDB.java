/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import VO.Aluno;
import VO.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author viniciuscustodio
 */
public class EventoDB {

    public static boolean createEvent(Evento evento) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(evento);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static List<Evento> selectProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT e from Evento e";
        TypedQuery<Evento> q = em.createQuery(qString, Evento.class);
        List<Evento> results = null;

        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    public static boolean alterEvent(Evento evento) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            
            Evento queryEvento = em.find(Evento.class, evento.getIdEvento());
 
            
            queryEvento.setNome(evento.getNome());
            queryEvento.setDate(evento.getDate()); 
            queryEvento.setHoraInicial(evento.getHoraInicial()); 
            queryEvento.setHoraFinal(evento.getHoraFinal()); 
            queryEvento.setTipo(evento.getTipo());  
            
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static boolean deleteEvent(Evento evento) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        
        try {
            
            Evento queryEvento = em.find(Evento.class, evento.getIdEvento());
 
            
            em.remove(queryEvento);
            
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static Evento selectEvento(Evento evento) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM Evento a "
                + "WHERE a.idEvento = :idEvento";
        TypedQuery<Evento> q = em.createQuery(qString, Evento.class);
        q.setParameter("idEvento", evento.getIdEvento());
        Evento result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return (Evento) result;
    }
}
