package VO;

import VO.Presenca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-13T11:47:46")
@StaticMetamodel(Aluno.class)
public class Aluno_ { 

    public static volatile ListAttribute<Aluno, Presenca> presencas;
    public static volatile SingularAttribute<Aluno, String> telefone;
    public static volatile SingularAttribute<Aluno, Integer> periodo;
    public static volatile SingularAttribute<Aluno, String> curso;
    public static volatile SingularAttribute<Aluno, Long> idAluno;
    public static volatile SingularAttribute<Aluno, String> nome;
    public static volatile SingularAttribute<Aluno, String> email;
    public static volatile SingularAttribute<Aluno, Integer> ra;

}