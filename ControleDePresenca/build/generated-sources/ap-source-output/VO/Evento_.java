package VO;

import VO.Presenca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-25T14:04:40")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, String> date;
    public static volatile ListAttribute<Evento, Presenca> presencas;
    public static volatile SingularAttribute<Evento, String> horaInicial;
    public static volatile SingularAttribute<Evento, String> tipo;
    public static volatile SingularAttribute<Evento, Long> idEvento;
    public static volatile SingularAttribute<Evento, String> horaFinal;
    public static volatile SingularAttribute<Evento, String> nome;

}