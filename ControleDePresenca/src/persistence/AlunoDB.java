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
public class AlunoDB {

    public static boolean createStudent(Aluno aluno) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            System.out.println(aluno.getCurso());
            em.persist(aluno);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static List<Aluno> selectProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT e from Aluno e";
        TypedQuery<Aluno> q = em.createQuery(qString, Aluno.class);
        List<Aluno> results = null;

        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    public static boolean alterAluno(Aluno aluno) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            System.out.println("here update");
            Aluno queryAluno = em.find(Aluno.class, aluno.getIdAluno());

            queryAluno.setNome(aluno.getNome());
            queryAluno.setCurso(aluno.getCurso());
            queryAluno.setEmail(aluno.getEmail());
            queryAluno.setRa(aluno.getRa());
            queryAluno.setTelefone(aluno.getTelefone());
            queryAluno.setPeriodo(aluno.getPeriodo());

            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static boolean deleteStudent(Aluno aluno) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {

            Aluno queryAluno = em.find(Aluno.class, aluno.getIdAluno());

            em.remove(queryAluno);

            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public static Aluno selectStudent(Aluno aluno) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM Aluno a "
                + "WHERE a.idAluno = :idAluno";
        TypedQuery<Aluno> q = em.createQuery(qString, Aluno.class);
        q.setParameter("idAluno", aluno.getIdAluno());
        Aluno result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return (Aluno) result;
    }
}
