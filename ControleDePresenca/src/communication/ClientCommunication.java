package communication;

import view.InterfaceCliente;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.InterfaceMenu;

// Devo ser
public class ClientCommunication extends Thread implements Observer{

    static DataInputStream in;                  // cria um duto de entrada
    static PrintStream out;                     // cria um duto de saída
    static String message;
    Socket ClientSocket = null;
    int serverPort;
    String ipServer;
    InterfaceCliente iCliente;
    
    private final Session session = new Session(true);

    public ClientCommunication(InterfaceCliente iCliente, int port, String ipServer) {
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
                    String[] splitMessage = message.split(";");
                    System.out.println(splitMessage.length);
                    
                    if ("02".equals(splitMessage[0])) {
                        if (splitMessage.length == 2) {
                            if ("1".equals(splitMessage[1])) {
                                // Envia a interface atual
                                InterfaceMenu.createMenu(session);
                                
                                iCliente.setVisible(false);
                            } else {
                                iCliente.wrongUserPassword();
                                this.closeConnection();

                            }
                        }
                    }else if("00".equals(splitMessage[0])){
                        System.out.println("Erro: " + splitMessage[1]);
                    }
                }

                // String received = in.readLine();
                //iCliente.jtAnswer.setText(iCliente.jtAnswer.getText() + "\n" + message);
            } catch (IOException ex) {
                Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public int closeConnection() {
        try {
            
            // Alert to the observers that the session is over
            this.session.setConnection(false);
            
            ClientSocket.close();
            ClientSocket = null;
            iCliente.setCliente(null);
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }

    public boolean createConnection() throws IOException {
        //Geração do socket
        // Socket ClientSocket = null;

        // cria o stream do teclado
        //BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        if (ClientSocket == null) {
            try {
                /* cria o socket do cliente para conexao com o servidor
                 que esta na maquina work1 operando na porta 4444 */

                ClientSocket = new Socket(ipServer, serverPort);
                if (!ClientSocket.isConnected()) {
                    ClientSocket = null;
                    return false;
                }

                /* associa um buffer de entrada e outro de saida ao socket */
                in = new DataInputStream(ClientSocket.getInputStream());    // aponta o duto de entrada para o socket do cliente
                out = new PrintStream(ClientSocket.getOutputStream());       // aponta o duto de saída para o socket do cliente
                System.out.println("Cliente connection");
                //imprime a resposta de conexão do servidor
                //iCliente.jtAnswer.setText(iCliente.jtAnswer.getText() + "\n" + in.readLine());
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
            //aguarda uma digitação pelo teclado para enviar ao servidor
            // lê uma linha do teclado
            //System.out.print("Digite uma mensagem: ");
            //message = teclado.readLine();
            out.println(message);
            //imprime a resposta do servidor
        } catch (Exception ex) {
            Logger.getLogger(ClientCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    // 
    @Override
    public void update(Observable o, Object arg) {
        // Verify if the updated Observable class is Session
        Session newSession = (Session) o;
        System.out.println("ClientCommunication Update " + newSession.isConnection());

    }
}
