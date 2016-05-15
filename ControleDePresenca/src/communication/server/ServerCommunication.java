package communication.server;

// servidor de eco
// recebe uma linha e ecoa a linha recebida.
import VO.Evento;
import VO.Usuario;
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
import persistence.EventoDB;
import persistence.UsuarioDB;

public class ServerCommunication extends Thread {

    Socket socketCliente;
    static ServerSocket serverSocket = null;
    String message = "";                        // string para conter informações transferidas
    DataInputStream dutoEntrada;            // cria um duto de entrada
    PrintStream dutoSaida;                  // cria um duto de saída
    int portaServidor;
    String answer = "";

    public ServerCommunication(Socket socketCliente, ServerSocket server, int porta) {
        this.socketCliente = socketCliente;
        this.portaServidor = porta;
        this.serverSocket = server;
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
                ServerManager.iServer.getJtMessage().setText(ServerManager.iServer.getJtMessage().getText() + "\n" + message);
                System.out.println(message);
                if (message == null) {
                    ServerManager.iServer.getJtMessage().setText(ServerManager.iServer.getJtMessage().getText() + "\n"
                            + "Cliente: " + socketCliente.getInetAddress().toString() + ":" + socketCliente.getPort() + " desconectou.");
                    socketCliente.close();
                    this.stop();
                } else {
                    System.out.println("mensagem recebida");
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
        } else if ("17".equals(message[0])) {
            createEventList(message);
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

    private void createEventList(String[] message) {
        String listEventsString = "18";
        List<Evento> eventos = EventoDB.selectProducts();
        for (Evento e : eventos) {
            String evento = e.getIdEvento() + ";" + e.getNome() + ";" + e.getDate() + ";" + e.getHoraInicial() + ";" + e.getHoraFinal() + ";" + e.getTipo();
            listEventsString = listEventsString + "|" + e;
        }
        dutoSaida.println(listEventsString);
    }

}
