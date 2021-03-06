
package Telas;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import trabalho.Contratos;
import trabalho.HibernateUtil;
import trabalho.Pessoas;
import trabalho.Servicos;
import trabalho.Servicoscontrato;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class ServicoContrato extends javax.swing.JFrame {
    
    int id_servico = 0;
    Contratos contrato;
    
    public ServicoContrato(Contratos contrato) {
        initComponents();
        this.contrato = contrato;
        this.setLocationRelativeTo(null);
        if (contrato != null){
            jTextField1.setText(contrato.getCodContrato().toString());
        }
        CarregarCombos();
        configuraCampoValor();
        Limpar();
        CarregaTabela();
    }
    
    private void configuraCampoValor() {
        Locale brazil = new Locale("pt", "BR");
        DecimalFormatSymbols real = new DecimalFormatSymbols(brazil);
        DecimalFormat realMoney = new DecimalFormat("###,###,##0.00", real);
        NumberFormatter formatter = new NumberFormatter(realMoney);
        
        formatter.setFormat(realMoney);
        formatter.setAllowsInvalid(false);
        
        jFormattedTextField1.setFormatterFactory(new DefaultFormatterFactory(formatter));
    }
    
    private void Limpar(){
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextArea1.setText("");
        jFormattedTextField1.setText("0,00");
    }
    
    private void CarregarCombos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Pessoas where classificacao = :classificacao");
        query.setParameter("classificacao", "Funcionário");
        List<Pessoas> funcionarios = query.list();
        List<Servicos> servicos = session.createQuery("from Servicos").list();
        session.close();

        jComboBox1.setModel(new DefaultComboBoxModel(servicos.toArray())); 
        jComboBox2.setModel(new DefaultComboBoxModel(funcionarios.toArray())); 
    }
    private void CarregaTabela(){
        ((DefaultTableModel)jTable1.getModel()).setRowCount(0);
        try{          
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Servicoscontrato where codContrato = :contrato");
            query.setParameter("contrato", contrato.getCodContrato());
            List<Servicoscontrato> servicos = query.list();
            for (int i = 0;i<servicos.size();i++){
                String servico = servicos.get(i).getServicos().getCodServico().toString();
                String descricaoservico = servicos.get(i).getServicos().getDescricao();
                String funcionario = servicos.get(i).getPessoas().getNome();
                Double valor = servicos.get(i).getValor();
                String valorS = "R$ " + String.valueOf(valor);
                valorS = valorS.replace(".", ",");
                String linha[] = new String[]{servico, descricaoservico, funcionario, valorS};
                ((DefaultTableModel)jTable1.getModel()).addRow(linha);
            }
            session.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serviço de contratos");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jButton1.setText("Inserir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serviço", "Descrição", "Funcionário", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Valor (R$):");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Serviço:");

        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Funcionário:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Observação:");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Contrato:");

        jButton4.setText("Excluir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Alterar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Sair");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jButton1)
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addGap(28, 28, 28)
                .addComponent(jButton3)
                .addGap(40, 40, 40)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            Pessoas funcionario = (Pessoas)jComboBox2.getSelectedItem();
            Servicos servico = (Servicos)jComboBox1.getSelectedItem();
            String valor = jFormattedTextField1.getText();
            String observacao = jTextArea1.getText();
            Locale ptBR = new Locale("pt", "BR");
            NumberFormat format = NumberFormat.getInstance(ptBR);
            Number number = format.parse(valor);
            double valorContrato = number.doubleValue();
            
            if(valorContrato <= 0) {
                jFormattedTextField1.requestFocus();
                throw new Exception("Campo VALOR não poder ser menor ou igual a zero!");
            }
            
            Servicoscontrato sc = new Servicoscontrato(contrato, funcionario, servico, valorContrato, observacao);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(sc);
            transaction.commit();
            session.close();
            JOptionPane.showMessageDialog(this,"Serviço cadastrado com sucesso!",
                                          "Atenção", JOptionPane.INFORMATION_MESSAGE); 
            Limpar();
            CarregaTabela();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Limpar();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    Servicoscontrato as;
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            id_servico = Integer.parseInt(
                ((DefaultTableModel) jTable1.getModel()).getValueAt(jTable1.getSelectedRow(), 0).toString());
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from Servicoscontrato where codContrato = :codigo and codServico = :servico");
                query.setParameter("codigo", contrato.getCodContrato());
                query.setParameter("servico", id_servico);
                query.setMaxResults(1);
                
                as = (Servicoscontrato) query.uniqueResult();
                
                Query queryServico = session.createQuery("from Servicos where codServico = :servico");
                queryServico.setParameter("servico", id_servico);
                queryServico.setMaxResults(1);
                
                Servicos servico = (Servicos) queryServico.uniqueResult();
                
                jComboBox1.setSelectedIndex(as.getServicos().getCodServico()-1);
                jComboBox1.setSelectedItem(as.getServicos().getCodServico().toString() + "-" + as.getServicos().getDescricao());
                jComboBox2.setSelectedItem(as.getPessoas().getNome());
                jFormattedTextField1.setValue(new Float(as.getValor()));
                jTextArea1.setText(as.getObservacao());
                session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged
        jTable1.clearSelection();
    }//GEN-LAST:event_jTable1MouseDragged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (as == null) {
            JOptionPane.showMessageDialog(null, "Selecione o registro que deseja excluir.");
        }else {
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transation = session.beginTransaction();
                session.delete(as);
                transation.commit();
                session.close();
                Limpar();
                CarregaTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (as == null) {
            JOptionPane.showMessageDialog(null, "Selecione o registro que deseja alterar.");
        }else {
            try {
                Pessoas funcionario = (Pessoas)jComboBox2.getSelectedItem();
                Servicos servico = (Servicos)jComboBox1.getSelectedItem();
                String valor = (jFormattedTextField1.getText()).replaceAll(",", ".");
                String observacao = jTextArea1.getText();
                Double valorContato = Double.parseDouble(valor);
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transation = session.beginTransaction();
                as.setPessoas(funcionario);
                as.setServicos(servico);
                as.setObservacao(observacao);
                as.setValor(valorContato);
                session.update(as);
                transation.commit();
                session.close();
                Limpar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        this.requestFocus();
    }//GEN-LAST:event_formFocusLost
    
    DuplicataContrato dc;
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        calculaTotal();
        if ("a prazo".equals(contrato.getCondPagamento())){
            if (dc == null){
                dc = new DuplicataContrato(contrato, String.valueOf(contrato.getValorContrato()));
            }
            dc.setVisible(true);
            this.setVisible(false);
        } else {
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        CarregarCombos();
    }//GEN-LAST:event_jComboBox1FocusGained
    private void calculaTotal(){
        double valor = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Servicoscontrato where codContrato = :contrato");
        query.setParameter("contrato", contrato.getCodContrato());
        List<Servicoscontrato> servicos = query.list();
        for(int i=0; i<servicos.size();i++){
            valor = valor + servicos.get(i).getValor();
        }
        Transaction transaction = session.beginTransaction();
        contrato.setValorContrato(valor);
        session.update(contrato);
        transaction.commit();
        session.close();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServicoContrato(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
