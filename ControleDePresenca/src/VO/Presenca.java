/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author viniciuscustodio
 */
@Entity
public class Presenca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPresenca;

   
    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Evento evento;
    
    private boolean presente;

    /**
     * @return the idPresenca
     */
    public Long getIdPresenca() {
        return idPresenca;
    }

    /**
     * @param idPresenca the idPresenca to set
     */
    public void setIdPresenca(Long idPresenca) {
        this.idPresenca = idPresenca;
    }



    /**
     * @return the presente
     */
    public boolean isPresente() {
        return presente;
    }

    /**
     * @param presente the presente to set
     */
    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
