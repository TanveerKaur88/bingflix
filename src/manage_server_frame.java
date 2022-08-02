
public class manage_server_frame extends javax.swing.JFrame {

    my_web_server obj;

    public manage_server_frame() {

        initComponents();
        bt2.setEnabled(false);
        setSize(500, 500);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt1 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        bt1.setText("START SERVER");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(175, 80, 160, 60);

        bt2.setText("STOP SERVER");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });
        getContentPane().add(bt2);
        bt2.setBounds(180, 200, 160, 60);

        lb.setText("WELCOME TO BINGEFLIX");
        getContentPane().add(lb);
        lb.setBounds(180, 20, 180, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed

        bt2.setEnabled(true);
        try {
            obj = new my_web_server(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bt1.setEnabled(false);

    }//GEN-LAST:event_bt1ActionPerformed

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
         obj.shutdown();
        bt1.setEnabled(true);
       
        bt2.setEnabled(false);

    }//GEN-LAST:event_bt2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manage_server_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
