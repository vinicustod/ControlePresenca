/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import VO.Aluno;
import VO.Evento;
import communication.client.ClientCommunication;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static view.FormEvento.evento;

/**
 *
 * @author viniciuscustodio
 */
public class FormAluno extends javax.swing.JFrame {

    ClientCommunication client = null;
    public static FormAluno aluno = null;
    ArrayList<Aluno> alunos = null;
    Aluno selectedStudent = null;

    /**
     * Creates new form FormAluno
     */
    public FormAluno() {
        initComponents();
    }

    public static void createAluno(ClientCommunication client) {
        if (aluno == null) {
            aluno = new FormAluno();
        }

        aluno.setVisible(true);
        aluno.client = client;

        aluno.updateTable();
    }

    public void setInTable(ArrayList<Aluno> alunos) {

        this.alunos = alunos;
        System.out.println("eventos recebidos");

        // Atualizando a tabela
        DefaultTableModel modelEventos = (DefaultTableModel) jTableAlunos.getModel();
        modelEventos.setRowCount(0);

        alunos.stream().forEach((al) -> {
            modelEventos.addRow(new Object[]{
                al.getRa(), al.getNome(), al.getCurso(),
                al.getEmail(), al.getPeriodo(), al.getTelefone()
            });
        });

    }

    public void updateTable() {
        client.sendMessage("27");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlAcademicRegistry = new javax.swing.JLabel();
        jtAcademicRegistry = new javax.swing.JTextField();
        jlName = new javax.swing.JLabel();
        jtName = new javax.swing.JTextField();
        jlCourse = new javax.swing.JLabel();
        jcbCourse = new javax.swing.JComboBox<>();
        jlPeriod = new javax.swing.JLabel();
        jcbPeriod = new javax.swing.JComboBox<>();
        jlEmail = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jlPhone = new javax.swing.JLabel();
        jtTelefone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlunos = new javax.swing.JTable();
        jbRegister = new javax.swing.JButton();
        jbUpdate = new javax.swing.JButton();
        jbDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlAcademicRegistry.setText("RA: ");

        jtAcademicRegistry.setText("1372475");

        jlName.setText("Nome:");

        jtName.setText("Vinicius Custodio");

        jlCourse.setText("Curso:");

        jcbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciência da Computação", "Engenheria de Produção", "Engenharia Elétrica", "Engenharia Civil", "Engenharia Mecânica", "Ciências Naturais" }));

        jlPeriod.setText("Periodo:");

        jcbPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jlEmail.setText("Email:");

        jtEmail.setText("vinihcius.custodio@gmail.com");

        jlPhone.setText("Telefone:");

        jtTelefone.setText("(42) 8425-9609");

        jTableAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "RA", "Nome", "Curso", "Email", "Periodo", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlunos.getTableHeader().setReorderingAllowed(false);
        jTableAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlunos);

        jbRegister.setText("Cadastrar");
        jbRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegisterActionPerformed(evt);
            }
        });

        jbUpdate.setText("Alterar");
        jbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUpdateActionPerformed(evt);
            }
        });

        jbDelete.setText("Deletar");
        jbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlAcademicRegistry)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtAcademicRegistry))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtName))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlCourse)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbCourse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlPeriod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbRegister)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbUpdate))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlEmail)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbDelete)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlAcademicRegistry)
                            .addComponent(jtAcademicRegistry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlName)
                            .addComponent(jtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlCourse)
                            .addComponent(jcbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEmail)
                            .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlPeriod)
                            .addComponent(jcbPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlPhone)
                            .addComponent(jtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbUpdate)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbRegister)
                        .addComponent(jbDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegisterActionPerformed
        if (client != null) {
            String toSend = "21;" + jtAcademicRegistry.getText()
                    + ";" + jtName.getText()
                    + ";" + jcbCourse.getSelectedItem().toString()
                    + ";" + jcbPeriod.getSelectedItem().toString()
                    + ";" + jtEmail.getText()
                    + ";" + jtTelefone.getText();
            client.sendMessage(toSend);
        }
    }//GEN-LAST:event_jbRegisterActionPerformed

    private void jTableAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunosMouseClicked
        int selection = jTableAlunos.getSelectedRow();

        this.selectedStudent = this.alunos.get(selection);

        jtAcademicRegistry.setText("" + selectedStudent.getRa());
        jtName.setText(selectedStudent.getNome());
        jtEmail.setText(selectedStudent.getEmail());
        jtTelefone.setText(selectedStudent.getTelefone());
        jcbCourse.setSelectedItem(selectedStudent.getCurso());
        jcbPeriod.setSelectedItem("" + selectedStudent.getPeriodo());
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableAlunosMouseClicked

    private void jbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUpdateActionPerformed
        if(client != null){
            if(selectedStudent != null) {
                String toSend = "23;" + selectedStudent.getIdAluno()
                    + ";" + jtAcademicRegistry.getText()
                    + ";" + jtName.getText()
                    + ";" + jcbCourse.getSelectedItem().toString()
                    + ";" + jcbPeriod.getSelectedItem().toString()
                    + ";" + jtEmail.getText()
                    + ";" + jtTelefone.getText();
                client.sendMessage(toSend);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jbUpdateActionPerformed

    private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteActionPerformed
        if(client != null){
            if(selectedStudent != null) {
                client.sendMessage("25;" + selectedStudent.getIdAluno() );
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jbDeleteActionPerformed

    public static void query(boolean bool, String type) {
        String title = "";
        if (type.equals("insert")) {
            title = "Cadastro";
        } else if (type.equals("alter")) {
            title = "Modificação";
        } else if (type.equals("delete")) {
            title = "Exclusão";
        }

        aluno.updateTable();
        if (bool) {
            JOptionPane.showMessageDialog(null, title + " de Aluno realizado(a) com sucesso. ", title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, title + " não realizado(a). ", title, JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlunos;
    private javax.swing.JButton jbDelete;
    private javax.swing.JButton jbRegister;
    private javax.swing.JButton jbUpdate;
    private javax.swing.JComboBox<String> jcbCourse;
    private javax.swing.JComboBox<String> jcbPeriod;
    private javax.swing.JLabel jlAcademicRegistry;
    private javax.swing.JLabel jlCourse;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPeriod;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JTextField jtAcademicRegistry;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtName;
    private javax.swing.JTextField jtTelefone;
    // End of variables declaration//GEN-END:variables
}
