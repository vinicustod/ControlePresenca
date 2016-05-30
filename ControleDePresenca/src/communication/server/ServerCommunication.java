package communication.server;

// servidor de eco
// recebe uma linha e ecoa a linha recebida.
import VO.Aluno;
import VO.Evento;
import VO.Usuario;
import VO.VOHelper;
import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.AlunoDB;
import persistence.EventoDB;
import persistence.UsuarioDB;
import view.FormServidor;

public class ServerCommunication extends Thread {

    Socket socketCliente;
    static ServerSocket serverSocket = null;
    String message = "";                        // string para conter informações transferidas
    DataInputStream dutoEntrada;            // cria um duto de entrada
    PrintStream dutoSaida;                  // cria um duto de saída
    int portaServidor;
    String answer = "";
    private FormServidor iServer;

    public ServerCommunication(Socket socketCliente, ServerSocket server, int porta, FormServidor iServer) {
        this.socketCliente = socketCliente;
        this.portaServidor = porta;
        this.serverSocket = server;
        this.iServer = iServer;
    }

    public void closingServer() {
        dutoSaida.close();
    }

    @Override
    public void run() {
        try {

            dutoEntrada = new DataInputStream(socketCliente.getInputStream());    // aponta o duto de entrada para o socket do cliente
            dutoSaida = new PrintStream(socketCliente.getOutputStream());       // aponta o duto de saída para o socket do cliente

            //envia a mensagem de conexão ao cliente
//            ServerManager.iServer.jtMessage.setText(ServerManager.iServer.jtMessage.getText() + "\n"
//                    + "Nova conexao: " + socketCliente.getInetAddress().toString() + ":" + socketCliente.getPort());
//            dutoSaida.println("Conexão efetuada com o servidor 127.0.0.1 Porta " + portaServidor);
            while (true) {
                //aguarda recebimento de dados vindos do cliente
                message = dutoEntrada.readLine();                           // recebe dados do cliente
                iServer.getJtMessage().setText(iServer.getJtMessage().getText() + "\n" + message);
                //System.out.println(message);
                if (message == null) {
                    iServer.getJtMessage().setText(iServer.getJtMessage().getText() + "\n"
                            + "Cliente: " + socketCliente.getInetAddress().toString() + ":" + socketCliente.getPort() + " desconectou.");
                    socketCliente.close();
                    this.stop();
                } else {
                    //System.out.println("mensagem recebida = " + message);
                    analyzeMessage(message.split(";"));
                }
            }
        } catch (Exception e) {
            System.out.println(e + "Exception no");
        }
    }

    private void analyzeMessage(String[] message) {
        if ("01".equals(message[0])) {
            loginRequest(message);
        } else if ("11".equals(message[0])) {
            createEventRequest(message);
        } else if ("13".equals(message[0])) {
            alterEventRequest(message);
        } else if ("15".equals(message[0])) {
            //System.out.println("message 15");
            deleteEventRequest(message);
        } else if ("17".equals(message[0])) {
            createEventList();
        } else if ("21".equals(message[0])) {
            createStudentRequest(message);
        } else if ("23".equals(message[0])) {
            alterStudentRequest(message);
        } else if ("25".equals(message[0])){
            deleteStudentRequest(message);
        }else if ("27".equals(message[0])) {
            createStudentList();
        } else {
            dutoSaida.println("00;Mensagem Invalida");
        }
    }

    private Date newDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");

        try {
            Date date = formatter.parse(dateString);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Date toTime(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date hora = sdf.parse(time);
            return hora;
        } catch (ParseException ex) {
            Logger.getLogger(ServerCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createEventRequest(String[] message) {
        if (message.length == 6) {
            Evento e = new Evento();
            e.setNome(message[1]);
            e.setDate(message[2]);
            e.setHoraInicial(message[3]);
            e.setHoraFinal(message[4]);
            e.setTipo(message[5]);
            if (EventoDB.createEvent(e)) {
                dutoSaida.println("12;1");
            } else {
                dutoSaida.println("12;0");
            }
        } else {
            dutoSaida.println("12;0");
        }
    }

    private void alterEventRequest(String[] message) {
        if (message.length == 7) {
            Evento e = new Evento();
            e.setIdEvento(Long.parseLong(message[1]));
            e.setNome(message[2]);
            e.setDate(message[3]);
            e.setHoraInicial(message[4]);
            e.setHoraFinal(message[5]);
            e.setTipo(message[6]);
            if (EventoDB.alterEvent(e)) {
                dutoSaida.println("14;1");
            } else {
                dutoSaida.println("14;0");
            }
        } else {
            dutoSaida.println("14;0");
        }
    }

    private void deleteEventRequest(String[] message) {
        if (message.length == 2) {
            Evento e = new Evento();
            e.setIdEvento(Long.parseLong(message[1]));
            if (EventoDB.deleteEvent(e)) {
                dutoSaida.println("16;1");
            } else {
                dutoSaida.println("16;0");
            }
        } else {
            dutoSaida.println("16;0");
        }
    }

    private void createEventList() {

        String listEventsString = "18";
        List<Evento> eventos = EventoDB.selectProducts();
        for (Evento e : eventos) {
            String evento = e.getIdEvento() + ";" + e.getNome() + ";" + e.getDate() + ";" + e.getHoraInicial() + ";" + e.getHoraFinal() + ";" + e.getTipo();
            listEventsString = listEventsString + "|" + evento;
        }
        //System.out.println(listEventsString);
        dutoSaida.println(listEventsString);
    }

    private void loginRequest(String[] message) {
        if (message.length == 3) {
            Usuario u = UsuarioDB.selectUser(message[1], message[2]);
            System.out.println("ID user: " + u.getIdUsuario());
            if (UsuarioDB.selectUser(message[1], message[2]) != null) {
                dutoSaida.println("02;1");
            } else {
                dutoSaida.println("02;0");
            }
        } else {
            dutoSaida.println("02;0");
        }
    }

    private void createStudentRequest(String[] message) {
        if (message.length == 7) {
            Aluno a = new Aluno();
            a.setRa(Integer.parseInt(message[1]));
            a.setNome(message[2]);
            a.setCurso(message[3]);
            a.setPeriodo(Integer.parseInt(message[4]));
            a.setEmail(message[5]);
            a.setTelefone(message[6]);
            if (AlunoDB.createStudent(a)) {
                dutoSaida.println("22;1");
            } else {
                dutoSaida.println("22;0");
            }
        } else {
            dutoSaida.println("22;0");
        }
    }

    private void createStudentList() {
        String listEventsString = "28";
        List<Aluno> alunos = AlunoDB.selectProducts();
        for (Aluno a : alunos) {
            String evento = a.getIdAluno() + ";" + a.getRa() + ";" + a.getNome() + ";" + a.getCurso() + ";" + a.getPeriodo() + ";" + a.getEmail() + ";" + a.getTelefone();
            listEventsString = listEventsString + "|" + evento;
        }
        //System.out.println(listEventsString);
        dutoSaida.println(listEventsString);
    }

    private void alterStudentRequest(String[] message) {
        if (message.length == 8) {
            //Aluno a = new Aluno();
            Aluno a = VOHelper.createStudent(Long.parseLong(message[1]), message[2], message[3],message[4], message[5], message[6], message[7]);
            if (AlunoDB.alterAluno(a)) {
                dutoSaida.println("24;1");
            } else {
                dutoSaida.println("24;0");
            }
        } else {
            dutoSaida.println("24;0");
        }
    }

    private void deleteStudentRequest(String[] message) {
        if (message.length == 2) {
            Aluno a = new Aluno();
            a.setIdAluno(Long.parseLong(message[1]));
            if (AlunoDB.deleteStudent(a)) {
                dutoSaida.println("26;1");
            } else {
                dutoSaida.println("26;0");
            }
        } else {
            dutoSaida.println("26;0");
        }    }

}
