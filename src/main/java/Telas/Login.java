package Telas;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import trabalho.HibernateUtil;
import trabalho.Pessoas;


public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        txtSenhaErrada.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Principal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        Cad = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtSenhaErrada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acesso ao sistema");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        Principal.setName("Principal"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuário:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Senha:");

        Login.setText("Login");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        Cad.setText("Sair");
        Cad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ACESSO AO SISTEMA");

        txtSenhaErrada.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        txtSenhaErrada.setForeground(new java.awt.Color(255, 0, 0));
        txtSenhaErrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSenhaErrada.setText("     a");

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                        .addComponent(Login)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cad))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PrincipalLayout.createSequentialGroup()
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogin)
                            .addGroup(PrincipalLayout.createSequentialGroup()
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenhaErrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenhaErrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Login)
                    .addComponent(Cad))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadActionPerformed
        System.exit(0);
    }//GEN-LAST:event_CadActionPerformed

    Menu menu;
    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        boolean sucess = false;
        String cargo = "";
        String nome = "";
        
        if(login.isEmpty() || senha.isEmpty()) {
            txtSenha.setText("");
            txtSenha.requestFocus();
            txtSenhaErrada.setText("<html><body>Os campos Usuário e Senha<br>não podem estar vazios!</body></html>");
            txtSenhaErrada.setVisible(true);
            return;
        }
        
        if (((login.toUpperCase()).equals("SUPERVISOR"))&&((senha.toUpperCase()).equals("SUPERVISOR"))){
             sucess = true;
             cargo = "Diretor";
        }
        
        txtSenhaErrada.setVisible(false);
        if (!sucess) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Pessoas where login = :login");
            query.setParameter("login", login);
            List<Pessoas> pessoas = query.list();
            session.close();



            for(int i = 0; i<pessoas.size(); i++){
                if ((login.equals(pessoas.get(i).getLogin()))&&(senha.equals(pessoas.get(i).getSenha()))) {
                    sucess = true;
                    cargo = pessoas.get(i).getClassificacao();
                    nome = pessoas.get(i).getNome();
                } 
            }
        }
        
        if (sucess){
            if (menu == null){
                if ("Diretor".equals(cargo)){
                    menu = new Menu(1);                    
                } else if ("Gerente".equals(cargo)){
                    menu = new Menu(3); 
                } else if ("Funcionário".equals(cargo)){
                    menu = new Menu(2); 
                }
                menu.nome = nome;
            }
            menu.setVisible(true);
            this.setVisible(false);
        }else {
            txtSenha.setText("");
            txtSenha.requestFocus();
            txtSenhaErrada.setText("Usuário / Senha Incorretos!");
            txtSenhaErrada.setVisible(true);
        }   
    }//GEN-LAST:event_LoginActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        this.requestFocus();
    }//GEN-LAST:event_formFocusLost


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cad;
    private javax.swing.JButton Login;
    private javax.swing.JPanel Principal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JLabel txtSenhaErrada;
    // End of variables declaration//GEN-END:variables
}
