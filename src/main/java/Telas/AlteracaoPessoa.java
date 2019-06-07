/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


import java.text.ParseException;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import trabalho.HibernateUtil;
import trabalho.Pessoas;

/**
 *
 * @author Lucas
 */
public class AlteracaoPessoa extends javax.swing.JFrame {
    
    
    /**
     * Creates new form Cadastro
     */
    int id;
    
    public AlteracaoPessoa(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.id = id;
        CarregarDados();
        
    }

//    private AlteracaoPessoa() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }


    
    private void CarregarDados(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Pessoas where codigo = :id");
            query.setParameter("id", id);
            query.setMaxResults(1);
            Pessoas p = (Pessoas) query.uniqueResult();
            txtNome.setText(p.getNome());
            comTipo.setSelectedItem(p.getTipo());
            comCla.setSelectedItem(p.getClassificacao());
            if ((comTipo.getSelectedItem().toString()).equals("Física")){
                jLabel13.setVisible(true);
                jLabel13.setText("CPF:");
                txtCnpj.setText("");
                txtCnpj.setVisible(false);
                txtCpf.setVisible(true);
                txtCpf.setText(p.getCpf());
            } else if ((comTipo.getSelectedItem().toString()).equals("Jurídica")){
                jLabel13.setVisible(true);
                jLabel13.setText("CNPJ:");
                txtCpf.setText("");
                txtCpf.setVisible(false);
                txtCnpj.setVisible(true);
                txtCnpj.setText(p.getCnpj());
            } else {
                jLabel13.setVisible(false);
                jLabel13.setText("");
                txtCpf.setText("");
                txtCnpj.setText("");
                txtCpf.setVisible(false);
                txtCnpj.setVisible(false);
            }
            comEstadoCivil.setSelectedItem(p.getEstadoCivil());
            txtCidade.setText(p.getCidade());
            comEstado.setSelectedItem(p.getEstado());
            txtEndereco.setText(p.getEndereco());
            txtBairro.setText(p.getBairro());
            txtTelefone.setText(p.getTelefone());
            txtCelular.setText(p.getCelular());
            txtLogin.setText(p.getLogin());
            txtSenha.setText(p.getSenha());
            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
             "ERRO!", JOptionPane.ERROR_MESSAGE);
        }     
    }
    
    private boolean ValidaTamanho() {
        try{
            if (txtNome.getText().length() > 60){
                txtNome.requestFocus();
                throw new Exception("Campo NOME maior que o permitido!");
            } else if (txtCidade.getText().length() > 40){
                txtCidade.requestFocus();
                throw new Exception("Campo CIDADE maior que o permitido!");
            } else if (txtEndereco.getText().length() > 40){
                txtEndereco.requestFocus();
                throw new Exception("Campo ENDEREÇO maior que o permitido!");
            } else if (txtBairro.getText().length() > 40){
                txtBairro.requestFocus();
                throw new Exception("Campo BAIRRO maior que o permitido!");
            } else if (txtTelefone.getText().length() > 20){
                txtTelefone.requestFocus();
                throw new Exception("Campo TELEFONE maior que o permitido!");
            } else if (txtCelular.getText().length() > 20){
                txtCelular.requestFocus();
                throw new Exception("Campo CELULAR maior que o permitido!");
            }else if (txtLogin.getText().length() > 10){
                if (temLogin()){
                    txtLogin.requestFocus();
                    throw new Exception("Campo LOGIN maior que o permitido!");
                }
            }else if (txtSenha.getText().length() > 10){
                if (temLogin()){
                    txtSenha.requestFocus();
                    throw new Exception("Campo SENHA maior que o permitido!");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;     
    }
    
    private boolean ValidaValores(){
        try{
            if ("".equals(txtNome.getText())){
                txtNome.requestFocus();
                throw new Exception("Campo NOME obrigatório!");
            } else if ("".equals(comTipo.getSelectedItem())){
                comTipo.requestFocus();
                throw new Exception("Campo TIPO obrigatório!");
            } else if (("".equals(txtCpf.getText())) || ("".equals(txtCnpj.getText()))) {
                txtCpf.requestFocus();
                throw new Exception("Campo CPF/CNPJ obrigatório!");
            } else if ("".equals(comCla.getSelectedItem().toString())){
                comCla.requestFocus();
                throw new Exception("Campo CLASSIFICAÇÃO obrigatório!");
            } else if ("".equals(comEstadoCivil.getSelectedItem().toString())){
                comEstadoCivil.requestFocus();
                throw new Exception("Campo ESTADO CIVIL obrigatório!");
            } else if ("".equals(txtCidade.getText())){
                txtCidade.requestFocus();
                throw new Exception("Campo CIDADE obrigatório!");
            } else if ("".equals(comEstado.getSelectedItem().toString())){
                comEstado.requestFocus();
                throw new Exception("Campo ESTADO obrigatório!");
            } else if ("".equals(txtEndereco.getText())){
                txtEndereco.requestFocus();
                throw new Exception("Campo ENDEREÇO obrigatório!");
            } else if ("".equals(txtBairro.getText())){
                txtBairro.requestFocus();
                throw new Exception("Campo BAIRRO obrigatório!");
            } else if ("".equals(txtTelefone.getText())){
                txtTelefone.requestFocus();
                throw new Exception("Campo TELEFONE inválido!");
            } else if ("".equals(txtCelular.getText())){
                txtCelular.requestFocus();
                throw new Exception("Campo CELULAR inválido!");
            }else if ("".equals(txtLogin.getText())){
                if (temLogin()){
                    txtLogin.requestFocus();
                    throw new Exception("Campo LOGIN inválido!");
                }
            }else if ("".equals(txtSenha.getText())){
                if (temLogin()){
                    txtSenha.requestFocus();
                    throw new Exception("Campo SENHA inválida!");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void tipoChange() {
        if ("CPF".equals(comTipo.getSelectedItem().toString())) {
            txtCnpj.setText("");
            txtCnpj.setVisible(false);
            jLabel13.setText("CPF");
            txtCpf.setText("");
            txtCpf.setVisible(true);
            txtCpf.requestFocus();
        } else {
            txtCpf.setText("");
            txtCpf.setVisible(false);
            jLabel13.setText("CNPJ");
            txtCnpj.setText("");
            txtCnpj.setVisible(true);
            txtCnpj.requestFocus();
        }
    }
    private boolean temLogin(){
        if (    ("Funcionário".equals(comCla.getSelectedItem().toString())) || 
                ("Gerente".equals(comCla.getSelectedItem().toString()))     || 
                ("Diretor".equals(comCla.getSelectedItem().toString())))    {
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jFrame1 = new javax.swing.JFrame();
        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        comTipo = new javax.swing.JComboBox();
        comCla = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        comEstadoCivil = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        comEstado = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtCnpj = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alteração de pessoa");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Conta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Senha:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Login:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nome:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tipo:");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Classificação:");

        comTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Física", "Jurídica", "" }));
        comTipo.setSelectedIndex(2);
        comTipo.setActionCommand("tipocomboBoxChanged");
        comTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comTipoItemStateChanged(evt);
            }
        });

        comCla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cliente", "Funcionário", "Diretor", "Gerente", "" }));
        comCla.setSelectedIndex(4);
        comCla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comClaItemStateChanged(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("CPF:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Estado civil:");

        comEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solteiro", "Casado", "Divorciado", "Viúvo", "" }));
        comEstadoCivil.setSelectedIndex(4);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Cidade:");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Estado:");

        comEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));
        comEstado.setSelectedIndex(27);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Endereço:");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Bairro:");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Telefone:");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Celular:");

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comEstadoCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comCla, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCnpj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(comEstado, 0, 56, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelular)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(comCla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(comEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    ConsultaPessoa cp;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nome = txtNome.getText();
        String tipo = comTipo.getSelectedItem().toString();
        String cpf = txtCpf.getText().replaceAll("\\D", "");;
        String cnpj = txtCnpj.getText().replaceAll("\\D", "");;
        String classificao = comCla.getSelectedItem().toString();
        String estadoCivil = comEstadoCivil.getSelectedItem().toString();
        String cidade = txtCidade.getText();
        String estado = comEstado.getSelectedItem().toString();
        String endereco = txtEndereco.getText();
        String bairro = txtBairro.getText();
        String telefone = txtTelefone.getText();
        String celular = txtCelular.getText();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        try {
            if (ValidaValores()&& ValidaTamanho()) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Pessoas p = (Pessoas) session.get(Pessoas.class, id);
                Transaction transaction = session.beginTransaction();
                p.setNome(nome);
                p.setTipo(tipo);
                if ("".equals(cnpj)){
                    p.setCpf(cpf);
                } else {
                    p.setCnpj(cnpj);
                }
                p.setClassificacao(classificao);
                p.setEstadoCivil(estadoCivil);
                p.setCidade(cidade);
                p.setEstado(estado);
                p.setEndereco(endereco);
                p.setBairro(bairro);
                p.setTelefone(telefone);
                p.setCelular(celular);
                if("Cliente".equals(classificao)) {
                    p.setLogin("");
                    p.setSenha("");
                } else {
                    p.setLogin(login);
                    p.setSenha(senha);
                }
                session.update(p);
                transaction.commit();
                session.close();
                JOptionPane.showMessageDialog(this,"Pessoa alterada com sucesso!",
                                              "Atenção", JOptionPane.INFORMATION_MESSAGE);
                if (cp == null) {
                    cp = new ConsultaPessoa();
                }
                cp.setVisible(true);
                cp.Listar();
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comTipoItemStateChanged
        tipoChange();
    }//GEN-LAST:event_comTipoItemStateChanged

    private void comClaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comClaItemStateChanged
        
        if (("Cliente".equals(comCla.getSelectedItem().toString())) || ("".equals(comCla.getSelectedItem().toString()))){
            txtLogin.setText("");
            txtSenha.setText("");
            jPanel3.setVisible(false);
        } else {
            txtLogin.setText("");
            txtSenha.setText("");
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_comClaItemStateChanged

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        this.requestFocus();
    }//GEN-LAST:event_formFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlteracaoPessoa(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comCla;
    private javax.swing.JComboBox comEstado;
    private javax.swing.JComboBox comEstadoCivil;
    private javax.swing.JComboBox comTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
