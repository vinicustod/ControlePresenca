package view;

import communication.client.ClientCommunication;
import communication.client.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author viniciuscustodio
 */
public class InterfaceCliente extends javax.swing.JFrame {
    public static InterfaceCliente login = null;
    
    public static void createLogin(){
        if(login == null){
            login = new InterfaceCliente();
        }
        login.setVisible(true);
        
    }
    
    private ClientCommunication cliente;

    /**
     * Creates new form InterfaceCliente
     */
    public InterfaceCliente() {
        initComponents();
        login = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlServerIp = new javax.swing.JLabel();
        jtServerIp = new javax.swing.JTextField();
        jlServerPort = new javax.swing.JLabel();
        jtServerPort = new javax.swing.JTextField();
        jtUsername = new javax.swing.JTextField();
        jlUsername = new javax.swing.JLabel();
        jbSend = new javax.swing.JButton();
        jtPassword = new javax.swing.JTextField();
        jlPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jlServerIp.setText("IP Servidor:");

        jtServerIp.setText("127.0.0.1");
        jtServerIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtServerIpActionPerformed(evt);
            }
        });

        jlServerPort.setText("Porta Servidor:");

        jtServerPort.setText("10000");
        jtServerPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtServerPortActionPerformed(evt);
            }
        });

        jlUsername.setText("Username:");

        jbSend.setText("Enviar");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        jlPassword.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlServerIp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtServerIp))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlUsername)
                            .addComponent(jlPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSend)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jtUsername))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlServerPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtServerPort)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlServerIp)
                    .addComponent(jtServerIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlServerPort)
                    .addComponent(jtServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbSend)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
        try {
            System.out.println("Interface Send");
            System.out.println(cliente);
            if (getCliente() == null) {
                // Initiate a new session
                //Session newSession = new Session(true);
                
                setCliente(new ClientCommunication(this, Integer.parseInt(this.jtServerPort.getText()), this.jtServerIp.getText()) );
                if (getCliente().createConnection()) {
                    getCliente().start();
                    getCliente().sendMessage("01;" + jtUsername.getText() + ";" + jtPassword.getText());
                }else{
                    setCliente(null);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSendActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (getCliente() != null) {
            getCliente().closeConnection();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jtServerPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtServerPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtServerPortActionPerformed

    private void jtServerIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtServerIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtServerIpActionPerformed

    public static void wrongUserPassword(){
        InterfaceCliente.login.cliente = null;
        JOptionPane.showMessageDialog(null, "Senha ou usuario incorreto", "Erro", JOptionPane.WARNING_MESSAGE);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbSend;
    private javax.swing.JLabel jlPassword;
    private javax.swing.JLabel jlServerIp;
    private javax.swing.JLabel jlServerPort;
    private javax.swing.JLabel jlUsername;
    private javax.swing.JTextField jtPassword;
    private javax.swing.JTextField jtServerIp;
    private javax.swing.JTextField jtServerPort;
    private javax.swing.JTextField jtUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the cliente
     */
    public ClientCommunication getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    

    public void setCliente(ClientCommunication cliente) {
        this.cliente = cliente;
    }
}
