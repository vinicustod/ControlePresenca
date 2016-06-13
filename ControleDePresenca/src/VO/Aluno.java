/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author viniciuscustodio
 */
@Entity
public class Aluno implements Comparable, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAluno;
    private int ra;
    private String nome;
    private String curso;
    private int periodo;
    private String email;
    private String telefone;
    
    @OneToMany(mappedBy = "aluno")
    private List<Presenca> presencas;
    /**
     * @return the idAluno
     */
    public Long getIdAluno() {
        return idAluno;
    }

    /**
     * @param idAluno the idAluno to set
     */
    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    /**
     * @return the ra
     */
    public int getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(int ra) {
        this.ra = ra;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the periodo
     */
    public int getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int compareTo(Object o) {
        Aluno a = (Aluno) o;
        return this.getRa() - a.getRa();
    }

    /**
     * @return the presencas
     */
    public List<Presenca> getPresencas() {
        return presencas;
    }

    /**
     * @param presencas the presencas to set
     */
    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }
    
    
    
}
