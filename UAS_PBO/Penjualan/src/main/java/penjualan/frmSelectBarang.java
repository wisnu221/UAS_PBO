/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asusm
 */
public class frmSelectBarang extends javax.swing.JFrame {

    Connection Con;
    ResultSet RsBrg;
    Statement stm;
    public frmTransaksi fAB = null;
    private Object[][] dataTable = null;
    private String[] header = {"Kode","Nama Barang","Satuan","Harga","Harga Beli","Stok","Stok Min"};
            
    
    public frmSelectBarang() {
        initComponents();
        baca_data();
    }

    private void open_db()
    {
        try{
            KoneksiMysql kon = new KoneksiMysql("localhost","root","","pbo");
            Con = kon.getConnection();
        }
        catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    
    private void baca_data()
    {
        open_db();
        try{
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            RsBrg = stm.executeQuery("select * from barang order by kd_brg");
            
            ResultSetMetaData meta = RsBrg.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            while(RsBrg.next()) {
                baris = RsBrg.getRow();
            }

            dataTable = new Object[baris][col];
            int x = 0;
            RsBrg.beforeFirst();
            while(RsBrg.next()) {
                dataTable[x][0] = RsBrg.getString("kd_brg");
                dataTable[x][1] = RsBrg.getString("nm_brg");
                dataTable[x][2] = RsBrg.getString("satuan");
                dataTable[x][3] = RsBrg.getDouble("harga");
                dataTable[x][4] = RsBrg.getDouble("harga_beli");
                dataTable[x][5] = RsBrg.getInt("stok");
                dataTable[x][6] = RsBrg.getInt("stok_min");
                x++;
            }
            tblBrg.setModel(new DefaultTableModel(dataTable,header));
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBrg = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Data Barang");

        tblBrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Barang", "Satuan", "Harga", "Harga Beli", "Stok", "Stok Min"
            }
        ));
        tblBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBrgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBrg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBrgMouseClicked
        int row=tblBrg.getSelectedRow();
        int tabelBarang = tblBrg.getSelectedRow();
        fAB.idBrg = tblBrg.getValueAt(tabelBarang, 0).toString();
        fAB.namaBrg = tblBrg.getValueAt(tabelBarang, 1).toString();
        fAB.hargaBrg = tblBrg.getValueAt(tabelBarang, 3).toString();
        fAB.itemTerpilih();
        this.dispose();
    }//GEN-LAST:event_tblBrgMouseClicked

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
            java.util.logging.Logger.getLogger(frmSelectBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSelectBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSelectBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSelectBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSelectBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBrg;
    // End of variables declaration//GEN-END:variables
}
