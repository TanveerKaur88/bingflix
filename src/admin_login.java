import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.event.*;
import javax.swing.*;
public class admin_login extends javax.swing.JFrame {

    
    public admin_login() {
        initComponents();
        jp.setText("");
        setSize(500,500);
        setVisible(true);
       
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        bt = new javax.swing.JButton();
        jp = new javax.swing.JPasswordField();
        tf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setText("USERNAME");
        getContentPane().add(lb1);
        lb1.setBounds(60, 90, 100, 16);

        lb2.setText("PASSWORD");
        getContentPane().add(lb2);
        lb2.setBounds(60, 160, 100, 16);

        bt.setText("LOGIN");
        bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionPerformed(evt);
            }
        });
        getContentPane().add(bt);
        bt.setBounds(160, 252, 130, 60);

        jp.setText("jPasswordField1");
        getContentPane().add(jp);
        jp.setBounds(170, 160, 130, 22);
        getContentPane().add(tf);
        tf.setBounds(170, 90, 130, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActionPerformed
         {
        String u = tf.getText();
        
        String p = jp.getText();

        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are reqiured...");
        } else {
            try {
                HttpResponse<String> res = Unirest.get("http://localhost:9000/login")
                        .queryString("username", u)
                        .queryString("password", p)
                        .asString();
                if (res.getStatus() == 200) {
                    String ans = res.getBody();
                    System.out.println("-----------------"+ans);
                    if (ans.equals("success")) {
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        dispose();
                        admin_home obj = new admin_home(u);
                    } else {
                        JOptionPane.showMessageDialog(this, "Login Fails");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_btActionPerformed

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
            java.util.logging.Logger.getLogger(admin_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt;
    private javax.swing.JPasswordField jp;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables
}
