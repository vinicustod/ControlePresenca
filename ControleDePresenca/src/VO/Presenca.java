/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

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
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPresenca;

    @ManyToOne
    private Aluno idAluno;

    @ManyToOne
    private Evento idEvento;
    
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
     * @return the idAluno
     */
    public Aluno getIdAluno() {
        return idAluno;
    }

    /**
     * @param idAluno the idAluno to set
     */
    public void setIdAluno(Aluno idAluno) {
        this.idAluno = idAluno;
    }

    /**
     * @return the idEvento
     */
    public Evento getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
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
}
