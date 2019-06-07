package Telas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import trabalho.Contratos;
import trabalho.Duplicatas;
import trabalho.HibernateUtil;
import trabalho.Pessoas;
import trabalho.Servicoscontrato;


public class ConsultaContrato extends javax.swing.JFrame {

   
    String id;
    int id2;
    
    public ConsultaContrato() {
        initComponents();
        ListarTabela();
        this.setLocationRelativeTo(null);
        
    }
    

    private void ListarTabela() {
        id2 = 0;
        if (jComboBox1.getSelectedItem().toString().equals(" ")){
            Listar();
        } else {
            Listar(jComboBox1.getSelectedItem().toString());
        }
    }
    
    public void Listar(){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        try{          
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Contratos");
            List<Contratos> c = query.list();
            session.close();
            for (int i = 0;i<c.size();i++){
                String funcionario = c.get(i).getPessoas().getNome();
                DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dataInicial = formataData.format(c.get(i).getDataInicial());
                String dataFinal = formataData.format(c.get(i).getDataFim());
                String valor = "R$ " + String.valueOf(c.get(i).getValorContrato());
                String situacao = c.get(i).getSituacao();
                String linha[] = new String[]{funcionario, dataInicial, dataFinal, valor, situacao};
                ((DefaultTableModel)jTable1.getModel()).addRow(linha);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Listar(String filtro){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        try{          
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Contratos where situacao = :filtro");
            query.setParameter("filtro", filtro);
            List<Contratos> c = query.list();
            session.close();
            for (int i = 0;i<c.size();i++){
                String contrato = c.get(i).getCodContrato().toString();
                String funcionario = c.get(i).getPessoas().getNome();
                DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dataInicial = formataData.format(c.get(i).getDataInicial());
                String dataFinal = formataData.format(c.get(i).getDataFim());
                String valor = "R$ " + String.valueOf(c.get(i).getValorContrato());
                String situacao = c.get(i).getSituacao();
                String linha[] = new String[]{contrato, funcionario, dataInicial, dataFinal, valor, situacao};
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
        jLabel1 = new javax.swing.JLabel();
        cmdAlterar = new javax.swing.JButton();
        cmdSair = new javax.swing.JButton();
        cmdExcluir = new javax.swing.JButton();
        button1 = new java.awt.Button();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Consulta de contratos");
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
                "Nº contrato", "Cliente", "Data inicial", "Data final", "Valor", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
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
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Filtro:");

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

        button1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        button1.setLabel("filtrar");
        button1.setName(""); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendente", "Cancelado", "Finalizado", " ", " " }));
        jComboBox1.setSelectedIndex(3);

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
                        .addGap(100, 100, 100)
                        .addComponent(cmdExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSair))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAlterar)
                    .addComponent(cmdSair)
                    .addComponent(cmdExcluir))
                .addGap(15, 15, 15))
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
                    Contratos c = (Contratos) session.get(Contratos.class, id2);
                    
                    Query queryItens = session.createQuery("from Servicoscontrato where codContrato = :contrato");
                    queryItens.setParameter("contrato", c.getCodContrato());
                    List<Servicoscontrato> servicos = queryItens.list();
                    
                    Query queryDuplicatas = session.createQuery("from Duplicatas where codContrato = : contrato");
                    queryDuplicatas.setParameter("contrato", c.getCodContrato());
                    List<Duplicatas> duplicatas = queryDuplicatas.list();
                    
                    Transaction transaction = session.beginTransaction();
                    while(servicos.size() > 0) {
                        session.delete(servicos);
                    }
                    while(duplicatas.size() > 0) {
                        session.delete(duplicatas);
                    }
                    
                    session.delete(c);
                    transaction.commit();
                    session.close();
                    ListarTabela();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_cmdExcluirActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        ListarTabela();
    }//GEN-LAST:event_button1ActionPerformed
    
    AlteracaoContrato ac;
    private void cmdAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAlterarActionPerformed
        if (id2 == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o registro que deseja alterar.");
        } else {
            if (ac == null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Contratos contrato = (Contratos) session.get(Contratos.class, id2);
                session.close();
                ac = new AlteracaoContrato(contrato);
            }
            ac.setVisible(true);
        }
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


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaContrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton cmdAlterar;
    private javax.swing.JButton cmdExcluir;
    private javax.swing.JButton cmdSair;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
