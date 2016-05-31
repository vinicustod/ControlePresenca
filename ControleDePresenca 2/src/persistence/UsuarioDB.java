/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import VO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author viniciuscustodio
 */
public class UsuarioDB {
    public static Usuario selectUser(String usuario, String senha) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Usuario u "
                + "WHERE u.nome = :nome and u.senha = :senha";
        TypedQuery<Usuario> q = em.createQuery(qString, Usuario.class);
        q.setParameter("nome", usuario);
        q.setParameter("senha", senha);
        Usuario result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return (Usuario) result;
    }
}
