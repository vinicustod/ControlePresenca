package communication.server;



import view.FormServidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author viniciuscustodio
 */
public class ServerManager extends Thread {

 

    int porta;
    FormServidor iServer;
    private static ServerSocket serverSocket = null;
    ArrayList<Thread> athreads = new ArrayList();

    public ServerManager(FormServidor iServer, int portaServidor) {
        this.porta = portaServidor;
        this.iServer = iServer;
    }
    
    public void stopAll(){
        Iterator i = athreads.iterator();
        while(i.hasNext()){
            ServerCommunication thread = (ServerCommunication) i.next();
            thread.closingServer();
            thread.stop();
        }
    }
    
    public void run() {
        iServer.getJtMessage().setText(iServer.getJtMessage().getText() + "\n" + "Servidor carregado no IP 127.0.0.1 e na porta " + porta);

        //ServerSocket servidorEco = null;        // cria o socket do servidor
        Socket socketCliente = null;            // cria o socket do cliente

        try {

            serverSocket = new ServerSocket(porta);  // instancia o socket do servidor na porta 9999

        } catch (IOException e) {

            System.out.println(e);
        }

        try {
            while (true) {
                                System.out.println("esperando");

                socketCliente = serverSocket.accept();                         // aguarda conexão do cliente
                    System.out.println("aceitou");
                ServerCommunication t = new ServerCommunication( socketCliente, getServerSocket(), porta, iServer);
                athreads.add(t);
                t.start();

            }
        } catch (IOException e) {
        }
    }
    
       /**
     * @return the serverSocket
     */
    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * @param aServerSocket the serverSocket to set
     */
    public static void setServerSocket(ServerSocket aServerSocket) {
        serverSocket = aServerSocket;
    }
}
