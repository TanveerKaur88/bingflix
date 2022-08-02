
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import java.awt.Image;
import java.io.File;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.*;

public class manage_category_frame extends javax.swing.JFrame {
    
    File f;
    ArrayList<CategoryClass> al;
    mytablemodel tm;
   
    
    public manage_category_frame() {
        initComponents();
        
        tm = new mytablemodel();
        
        al = new ArrayList<>();
        setVisible(true);
        setSize(800, 800);
        loadCategories();
        jTable1.setModel(tm);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta = new javax.swing.JTextArea();
        bt1 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        jphotolb = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setText("ADD CATEGORY");
        getContentPane().add(lb1);
        lb1.setBounds(190, 26, 180, 50);

        lb2.setText("Category Name");
        getContentPane().add(lb2);
        lb2.setBounds(10, 130, 90, 16);

        lb3.setText("Description");
        getContentPane().add(lb3);
        lb3.setBounds(10, 190, 90, 16);

        lb4.setText("Photo");
        getContentPane().add(lb4);
        lb4.setBounds(10, 300, 70, 16);
        getContentPane().add(tf1);
        tf1.setBounds(100, 130, 230, 22);

        ta.setColumns(20);
        ta.setRows(5);
        jScrollPane1.setViewportView(ta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(100, 180, 234, 86);

        bt1.setText("Browse");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(0, 360, 80, 22);

        bt2.setText("ADD CATEGORY");
        bt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt2ActionPerformed(evt);
            }
        });
        getContentPane().add(bt2);
        bt2.setBounds(50, 450, 210, 22);

        jphotolb.setText("jLabel1");
        getContentPane().add(jphotolb);
        jphotolb.setBounds(110, 270, 230, 90);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Category Name", "Description", "Photo"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane3);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(370, 80, 330, 370);

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(490, 480, 72, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt2ActionPerformed
        String cn = tf1.getText();
        
        String des = ta.getText();
        
        if (cn.isEmpty() || des.isEmpty() || f == null) {
            JOptionPane.showMessageDialog(this, "All fields are reqiured...");
        } else {
            try {
                HttpResponse<String> res = Unirest.post("http://localhost:9000/admin_add_category")
                        .queryString("category_name", cn)
                        .queryString("description", des)
                        .field("photo", f)
                        .asString();
                if (res.getStatus() == 200) {
                    String ans = res.getBody();
                    System.out.println("-----------------" + ans);
                    if (ans.equals("success")) {
                        JOptionPane.showMessageDialog(this, "Category Added Successfully");
                        loadCategories();
                    } else {
                        JOptionPane.showMessageDialog(this, "Category Already Exists...");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        

    }//GEN-LAST:event_bt2ActionPerformed

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        //chooser code will go here 
        JFileChooser chooser = new JFileChooser();
        int ans = chooser.showOpenDialog(this);
        if (ans == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
        }
        ImageIcon icon = new ImageIcon(f.getPath());
        Image img = icon.getImage().getScaledInstance(jphotolb.getWidth(), jphotolb.getHeight(), Image.SCALE_SMOOTH);
        jphotolb.setIcon(new ImageIcon(img));

    }//GEN-LAST:event_bt1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //get selected row from table --->index
        //check if index == -1
        //please selcet a row first
//       else
// String catname=al.get(index).catName;
// send Unirest get request to delete category from table on resource  admindeletecategory with catname

//get response 
//if success  ---------- category deleted successfully
//else an error occurred
        int index = jTable1.getSelectedRow();
        {
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Please Select a Row");
            } else {
                String category_name = al.get(index).category_name;
                try {
                    HttpResponse<String> res = Unirest.get("http://localhost:9000/admindeletecategory")
                            .queryString("catname", category_name)
                            .asString();
                    
                    if (res.getStatus() == 200) {
                        String ans = res.getBody();
                        System.out.println("-----------------" + ans);
                        if (ans.equals("success")) {
                            JOptionPane.showMessageDialog(this, "Category Deleted Successfully");
                            loadCategories();
                            
                        } else {
                            JOptionPane.showMessageDialog(this, " Error Occurred");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed
    public void loadCategories() {
        String ans = " ";
        try {
            HttpResponse<String> res = Unirest.get("http://localhost:9000/getAllCategories")
                    .asString();
            
            if (res.getStatus() == 200) {
                String ans2 = res.getBody();
                System.out.println("-----------------" + ans2);
                StringTokenizer st = new StringTokenizer(ans2, "#$#");
                al.clear();
                while (st.hasMoreTokens()) {
                    String catDetails = st.nextToken();
                    System.out.println("categoryDetails   " + catDetails);
                    StringTokenizer st2 = new StringTokenizer(catDetails, "**");
                    String catName = st2.nextToken();
                    String desc = st2.nextToken();
                    String photo = st2.nextToken();
                    CategoryClass obj = new CategoryClass(catName, desc, photo);
                    al.add(obj);
                    
                }
                tm.fireTableDataChanged();
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    class mytablemodel extends AbstractTableModel {
        
        @Override
        public int getRowCount() {
            return al.size();
        }
        
        @Override
        public int getColumnCount() {
            return 3;
        }
        
        @Override
        public String getColumnName(int j) {
            String c[] = {"category_name", "description", "photo"};
            return c[j];
        }
        
        @Override
        public Object getValueAt(int i, int j) {
            CategoryClass st = al.get(i);
            
            if (j == 0) {
                return st.category_name;
            } else if (j == 1) {
                return st.description;
            } else {
                return st.photo;
            }
        }
        
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manage_category_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jphotolb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JTextArea ta;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
