/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Prakash <Prakash>
 */
public class GetOrderId extends javax.swing.JFrame {

   String user = "root";
   String password = "admin";
   String db="jdbc:mysql://localhost/ybbom";
   Statement st = null;        
   Connection conn =null;
    
    public GetOrderId() {
        
        initComponents();
        selectOrderNo();
        OrderNo();
        selectCategory();
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("OrderByMaterial");
        this.setLocationRelativeTo(this);
        this.pack();
    }

    public void selectOrderNo() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,password);
            String Query = "select OrderNo from customerorder";
            PreparedStatement pst =conn.prepareStatement(Query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {      
                     
                   ordernocombo.addItem(rs.getString("OrderNo"));                   
            }
            
        }catch(Exception e) {
            
        }
        
    } 
    
    private void OrderNo(){
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            conn= DriverManager.getConnection(db,user,password);  
            Statement st = conn.createStatement();
            ResultSet r= st.executeQuery("SELECT OrderNo FROM multiple_order GROUP BY OrderNo");
            while (r.next()) {                     
                    combo.addItem(r.getString("OrderNo"));                   
            }           
            conn.close();
           } catch (Exception e) {  
                    JOptionPane.showMessageDialog(null,"Failed to Connect to Database","Error Connection", JOptionPane.WARNING_MESSAGE);  
                      
        }        
    }
    
    public void selectCategory() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db,user,password);
            String Query = "select name from categories";
            PreparedStatement pst =conn.prepareStatement(Query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {                     
                    category.addItem(rs.getString("name"));                   
            }
            
        }catch(Exception e) {
            
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox();
        category = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel1.setText("Select OrderID :");
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12));
        jLabel5.setText("Select Category :");
        
        //JComboBox combo = new JComboBox();
//        combo.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField1ActionPerformed(evt);
//               
//            }
//        });
        category.addItem("All");
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel3.setText("Report Type    :");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Order By Meterial");
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
//        // TODO add your handling code here:
//    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        String id = (String) combo.getSelectedItem();
        String name = (String) category.getSelectedItem();
        MainScreen ms = new MainScreen();
        ms.showSaveFileDialogPdf(id,name);
        //ms.showSaveFileDialogPdf(name);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox combo;
    private javax.swing.JComboBox category;
    private javax.swing.JComboBox ordernocombo;
    // End of variables declaration//GEN-END:variables
}
