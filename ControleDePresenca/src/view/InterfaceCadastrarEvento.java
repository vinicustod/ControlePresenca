/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import VO.Evento;
import communication.client.ClientCommunication;
import communication.client.Session;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author viniciuscustodio
 */
public class InterfaceCadastrarEvento extends javax.swing.JFrame {
    ArrayList<Evento> eventos = null;
    ClientCommunication client = null;
    
    private Evento selectedEvent;
    
    public static InterfaceCadastrarEvento evento = null;
    /**
     * Creates new form InterfaceCadastrarEvento
     * @param eventos
     */
    public void setInTable(ArrayList<Evento> eventos) {

        this.eventos = eventos;
        System.out.println("eventos recebidos");
        
        
        // Atualizando a tabela
        DefaultTableModel modelEventos = (DefaultTableModel) jTEventos.getModel();
        modelEventos.setRowCount(0);
        
        eventos.stream().forEach((ev) -> {
            modelEventos.addRow(new Object[]{
                ev.getIdEvento(), ev.getNome(), ev.getDate(), 
                ev.getHoraInicial(), ev.getHoraFinal(), ev.getTipo()
            });
        });
        
        
        
    }
    
    private void updateTable(){
        //System.out.println("InterfaceCadastrarEvento atualizando");
        client.sendMessage("17");
    }
    
    public InterfaceCadastrarEvento() {
        this.selectedEvent = null;
        
        initComponents();
    }

    
    public static void createEvento(ClientCommunication client){
        if(evento == null){
            evento = new InterfaceCadastrarEvento();
        }
        
        evento.setVisible(true);
        evento.client = client;
        
        evento.updateTable();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtNome = new javax.swing.JTextField();
        jtDate = new javax.swing.JTextField();
        jtInitialHour = new javax.swing.JTextField();
        jtFinalHour = new javax.swing.JTextField();
        jtCadastrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTEventos = new javax.swing.JTable();
        jtAlterar = new javax.swing.JButton();
        jtDeletar = new javax.swing.JButton();
        jcbTypeEvent = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome:");

        jLabel2.setText("Data:");

        jLabel3.setText("Hora Inicial:");

        jLabel4.setText("Hora Final:");

        jLabel5.setText("TipoEvento:");

        jtFinalHour.setText(" ");

        jtCadastrar.setText("Cadastrar");
        jtCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCadastrarActionPerformed(evt);
            }
        });

        jTEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Data", "Hora Inicial", "Hora Final", "Tipo Evento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTEventos);

        jtAlterar.setText("Alterar");
        jtAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtAlterarActionPerformed(evt);
            }
        });

        jtDeletar.setText("Deletar");
        jtDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtDeletarActionPerformed(evt);
            }
        });

        jcbTypeEvent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Palestra", "Minicurso", "Mesa Redonda", "Oficina", "Outro" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtFinalHour)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcbTypeEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtDate))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtInitialHour))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtNome)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtDeletar)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtInitialHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtFinalHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcbTypeEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCadastrar)
                    .addComponent(jtAlterar)
                    .addComponent(jtDeletar))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCadastrarActionPerformed
        if(client != null){
            client.sendMessage("11;" + jtNome.getText() + ";" + jtDate.getText() + ";" + jtInitialHour.getText() + ";" + jtFinalHour.getText() + ";" + jcbTypeEvent.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jtCadastrarActionPerformed

    private void jtAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtAlterarActionPerformed
        // TODO add your handling code here:
        if(client != null){
            if(selectedEvent != null) {
                client.sendMessage("13;" + selectedEvent.getIdEvento() + ";" + jtNome.getText() + ";" + jtDate.getText() + ";" + jtInitialHour.getText() + ";" + jtFinalHour.getText() + ";" + jcbTypeEvent.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_jtAlterarActionPerformed

    private void jtDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtDeletarActionPerformed
        // TODO add your handling code here:
        if(client != null){
            if(selectedEvent != null) {
                client.sendMessage("15;" + selectedEvent.getIdEvento() );
            }
        }
    }//GEN-LAST:event_jtDeletarActionPerformed

    private void jTEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTEventosMouseClicked
        int selection = jTEventos.getSelectedRow();
        
        this.selectedEvent = this.eventos.get(selection);
        
        
        jtNome.setText(selectedEvent.getNome());
        jtDate.setText(selectedEvent.getDate());
        jtInitialHour.setText(selectedEvent.getHoraInicial());
        jtFinalHour.setText(selectedEvent.getHoraFinal());
        jcbTypeEvent.setSelectedItem(selectedEvent.getTipo());
        
    }//GEN-LAST:event_jTEventosMouseClicked

    public static void query(Boolean bool, String type){
        
        String title = "";
        if(type.equals("insert"))
            title = "Cadastro";
        else if(type.equals("alter")){
            title = "Modificação";
        }
        else if(type.equals("delete")){
            title = "Exclusão";
        }
        
        evento.updateTable();
            
        if(bool){
            JOptionPane.showMessageDialog(null, title +  " de Evento realizado(a) com sucesso. ", title, JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, title +  " não realizado(a). ", title, JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(InterfaceCadastrarEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceCadastrarEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceCadastrarEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceCadastrarEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCadastrarEvento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTEventos;
    private javax.swing.JComboBox<String> jcbTypeEvent;
    private javax.swing.JButton jtAlterar;
    private javax.swing.JButton jtCadastrar;
    private javax.swing.JTextField jtDate;
    private javax.swing.JButton jtDeletar;
    private javax.swing.JTextField jtFinalHour;
    private javax.swing.JTextField jtInitialHour;
    private javax.swing.JTextField jtNome;
    // End of variables declaration//GEN-END:variables
}
