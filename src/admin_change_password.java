
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javax.swing.JOptionPane;


public class admin_change_password extends javax.swing.JFrame {

    
    public admin_change_password(String user) {
        initComponents();
        jp1.setText("");
        jp2.setText("");
        jp3.setText("");
        tf.setEditable(false);
        tf.setText(user);
        
        setSize(800,800);
        setVisible(true);
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        tf = new javax.swing.JTextField();
        jp1 = new javax.swing.JPasswordField();
        jp2 = new javax.swing.JPasswordField();
        jp3 = new javax.swing.JPasswordField();
        bt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setText("USERNAME");
        getContentPane().add(lb1);
        lb1.setBounds(60, 120, 90, 16);

        lb2.setText("OLD PASSWORD");
        getContentPane().add(lb2);
        lb2.setBounds(60, 170, 88, 16);

        lb3.setText("NEW PASSWORD");
        getContentPane().add(lb3);
        lb3.setBounds(60, 220, 91, 16);

        lb4.setText("CONFIRM PASSWORD");
        getContentPane().add(lb4);
        lb4.setBounds(60, 280, 118, 16);
        getContentPane().add(tf);
        tf.setBounds(190, 120, 140, 22);
        getContentPane().add(jp1);
        jp1.setBounds(190, 170, 140, 22);

        jp2.setText("jPasswordField2");
        getContentPane().add(jp2);
        jp2.setBounds(190, 220, 140, 22);

        jp3.setText("jPasswordField3");
        getContentPane().add(jp3);
        jp3.setBounds(190, 280, 140, 22);

        bt.setText("CHANGE PASSWORD");
        bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActionPerformed(evt);
            }
        });
        getContentPane().add(bt);
        bt.setBounds(160, 340, 170, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActionPerformed

        String op = jp1.getText();

        String np = jp2.getText();
        String cp = jp3.getText();
        String u = tf.getText();

        if (op.isEmpty() || np.isEmpty() || cp.isEmpty() || u.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are reqiured...");
        } else if (!np.equals(cp)) {
            JOptionPane.showMessageDialog(this, "New Password and Confirm Password must match...");
        } else {
            try {
                HttpResponse<String> res = Unirest.get("http://localhost:9000/admin_change_password")
                        .queryString("old_password", op)
                        .queryString("new_password", np)
                        .queryString("username", u)
                        .asString();

                if (res.getStatus() == 200) {
                    String ans = res.getBody();
                    System.out.println("-----------------" + ans);
                    if (ans.equals("success")) {
                        JOptionPane.showMessageDialog(this, "Password changed successfully");
                        dispose();
                        admin_home obj = new admin_home(u);
                    } else {
                        JOptionPane.showMessageDialog(this, " Old password is incorrect");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(admin_change_password.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_change_password.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_change_password.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_change_password.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_change_password("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt;
    private javax.swing.JPasswordField jp1;
    private javax.swing.JPasswordField jp2;
    private javax.swing.JPasswordField jp3;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JTextField tf;
    // End of variables declaration//GEN-END:variables
}
