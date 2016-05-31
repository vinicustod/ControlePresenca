/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author viniciuscustodio
 */
public class VOHelper {


    public static Evento createEvento(long idEvento, String nome, String date, String horaInicial, String horaFinal, String tipo) {
        Evento e = new Evento();
        
        e.setIdEvento(idEvento);
        e.setNome(nome);
        e.setDate(date);
        e.setHoraInicial(horaInicial);
        e.setHoraFinal(horaFinal);
        e.setTipo(tipo);
        
        return e;
    }
    
//    public static Aluno createAluno(){
//        return new Aluno();
//    }

    public static Aluno createStudent(long idAluno, String academicRegistry, String nome, String curso, String periodo, String email, String telefone) {
        Aluno a = new Aluno();
        a.setIdAluno(idAluno);
        a.setRa(Integer.parseInt(academicRegistry));
        a.setNome(nome);
        a.setCurso(curso);
        a.setPeriodo(Integer.parseInt(periodo));
        a.setEmail(email);
        a.setTelefone(telefone);
        
        return a;
    }
}
