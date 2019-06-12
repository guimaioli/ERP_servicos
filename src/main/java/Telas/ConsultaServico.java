/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import trabalho.HibernateUtil;
import trabalho.Pessoas;
import trabalho.Servicos;

/**
 *
 * @author Lucas
 */
public class ConsultaServico extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaDoador
     */
    String id;
    int id2;
    
    public ConsultaServico() {
        initComponents();
        this.setLocationRelativeTo(null);
        Listar();
    }
    

    
    public void Listar(){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        try{          
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Servicos");
            List<Servicos> s = query.list();
            session.close();
            for (int i = 0;i<s.size();i++){
                String codigo = s.get(i).getCodServico().toString();
                String descricao = s.get(i).getDescricao();
                
                String linha[] = new String[]{codigo, descricao};
                ((DefaultTableModel)jTable1.getModel()).addRow(linha);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmdAlterar = new javax.swing.JButton();
        cmdSair = new javax.swing.JButton();
        cmdExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Consultar de Serviço");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTable1MouseDragged(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        cmdAlterar.setText("Alterar");
        cmdAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAlterarActionPerformed(evt);
            }
        });

        cmdSair.setText("Sair");
        cmdSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSairActionPerformed(evt);
            }
        });

        cmdExcluir.setText("Exluir");
        cmdExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAlterar)
                        .addGap(77, 77, 77)
                        .addComponent(cmdExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAlterar)
                    .addComponent(cmdSair)
                    .addComponent(cmdExcluir))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSairActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cmdSairActionPerformed

    private void cmdExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExcluirActionPerformed
        if (id2 == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o registro que deseja excluir.");
        } else {
            try {
                if (JOptionPane.showConfirmDialog(this, "Confirma a exclusão? ", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Servicos s = (Servicos) session.get(Servicos.class, id2);
                    Transaction transaction = session.beginTransaction();
                    session.delete(s);
                    transaction.commit();
                    session.close();
                    Listar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }//GEN-LAST:event_cmdExcluirActionPerformed
    
    AlteracaoServico as;
    private void cmdAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAlterarActionPerformed
        if (as == null){
            as = new AlteracaoServico(id2);
        }
        as.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cmdAlterarActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        this.requestFocus();
    }//GEN-LAST:event_formFocusLost

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        id2 = Integer.parseInt(
                    ((DefaultTableModel) jTable1.getModel()).getValueAt(jTable1.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged
        jTable1.clearSelection();
    }//GEN-LAST:event_jTable1MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAlterar;
    private javax.swing.JButton cmdExcluir;
    private javax.swing.JButton cmdSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
