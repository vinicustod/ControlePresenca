package communication.client;

import VO.Aluno;
import VO.Evento;
import VO.VOHelper;
import view.FormLogin;
import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.persistence.internal.jpa.metadata.accessors.classes.InterfaceAccessor;
import view.FormAluno;
import view.FormEvento;
import view.FormMenu;

// Devo ser
public class ClientCommunication extends Thread implements Observer {

    static DataInputStream in;                  // cria um duto de entrada
    static PrintStream out;                     // cria um duto de saída
    static String message;
    Socket ClientSocket = null;
    int serverPort;
    String ipServer;
    FormLogin iCliente;

    private final Session session = new Session(true);

    public ClientCommunication(FormLogin iCliente, int port, String ipServer) {
        this.serverPort = port;
        this.ipServer = ipServer;
        this.iCliente = iCliente;

        // Add this object to the list of Observer of session - Check to not duplicate
        session.deleteObserver(ClientCommunication.this);
        session.addObserver(ClientCommunication.this);

    }

    @Override
    public void run() {
        while (true) {
            try {
                message = in.readLine();
                System.out.println(message);
                if (message == null) {

                    // Alert to the observers that the session is over
                    this.session.setConnection(false);

                    ClientSocket = null;
                    iCliente.setCliente(null);
                    this.stop();
                } else {
                    analyzeMessage(message.split("\\|"));
                }

            } catch (IOException ex) {
                Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public int closeConnection() {
        try {

            this.session.setConnection(false);

            ClientSocket.close();
            ClientSocket = null;
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }

    public boolean createConnection() throws IOException {

        if (ClientSocket == null) {
            try {

                ClientSocket = new Socket(ipServer, serverPort);
                if (!ClientSocket.isConnected()) {
                    ClientSocket = null;
                    return false;
                }
                in = new DataInputStream(ClientSocket.getInputStream());
                out = new PrintStream(ClientSocket.getOutputStream());
                System.out.println("Cliente connection");

            } catch (UnknownHostException e) {
                System.err.println("Host desconhecido: ");
                ClientSocket = null;
                return false;
            } catch (IOException e) {
                System.err.println("IP ou Porta não existe ");
                ClientSocket = null;
                return false;
            } catch (Exception e) {
                System.out.println("Falha na conexão com o servidor");
                ClientSocket = null;
                return false;
            }
        }
        return true;
    }

    public void sendMessage(String message) {
        try {
            out.println(message);
            System.out.println("mensagem enviada  " + message);
            
        } catch (Exception ex) {
            Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // 
    @Override
    public void update(Observable o, Object arg) {
        // Verify if the updated Observable class is Session
        Session newSession = (Session) o;
        //System.out.println("ClientCommunication Update " + newSession.isConnection());

    }

    private void analyzeMessage(String[] message) {
        System.out.println("analyze: " + message.length);
        if (message.length == 1) {
            
            String[] splitMessage = message[0].split(";");
            if ("02".equals(splitMessage[0])) {
                loginResponse(splitMessage);
            } else if ("12".equals(splitMessage[0])) {
                queryEventResponse(splitMessage, "insert");
            } else if ("14".equals(splitMessage[0])) {
                queryEventResponse(splitMessage, "alter");
            } else if ("16".equals(splitMessage[0])) {
                queryEventResponse(splitMessage, "delete");
            } else if ("22".equals(splitMessage[0])) {
                queryStudentResponse(splitMessage, "insert");
            } else if ("24".equals(splitMessage[0])){
                queryStudentResponse(splitMessage, "alter");
            } else if ("26".equals(splitMessage[0])){
                queryStudentResponse(splitMessage, "delete");
            }else if ("00".equals(splitMessage[0])) {
                System.out.println("Erro: " + splitMessage[1]);
            } 
            
        } else if ("18".equals(message[0])) {
            listEventsResponse(message);
        } else if ("28".equals(message[0])) {
            listStudentsResponse(message);
        }
    }

    private void loginResponse(String[] message) {
        if (message.length == 2) {
            if ("1".equals(message[1])) {
                FormMenu.createMenu(session, this);
                FormLogin.login.setVisible(false);
            } else {
                FormLogin.wrongUserPassword();
                this.closeConnection();
            }
        }
    }

    private Date newDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");

        try {
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void listEventsResponse(String[] message) {
        ArrayList<Evento> eventos = new ArrayList();
        
        for (int i = 1; i < message.length; i++) {
            String[] event = message[i].split(";");
            Evento e = VOHelper.createEvento(Long.parseLong(event[0]), event[1], event[2], event[3], event[4], event[5]);
            eventos.add(e);
        }
        //InterfaceCadastrarEvento.createEvento(this);
        FormEvento.evento.setInTable(eventos);
        
    }

    private void queryEventResponse(String[] message, String type) {
        if (message.length == 2) {
            if ("1".equals(message[1])) {
                FormEvento.query(true, type);
            } else {
                FormEvento.query(false, type);
            }
        }
    }

    private void queryStudentResponse(String[] message, String type) {
        if (message.length == 2) {
            if ("1".equals(message[1])) {
                FormAluno.query(true, type);
            } else {
                FormAluno.query(false, type);
            }
        }
    }

    private void listStudentsResponse(String[] message) {
        ArrayList<Aluno> alunos = new ArrayList();
        
        for (int i = 1; i < message.length; i++) {
            String[] student = message[i].split(";");
            Aluno e = VOHelper.createStudent(Long.parseLong(student[0]), student[1], student[2], student[3], student[4], student[5], student[6]);
            alunos.add(e);
        }
        //InterfaceCadastrarEvento.createEvento(this);
        FormAluno.aluno.setInTable(alunos);    
    }
}
