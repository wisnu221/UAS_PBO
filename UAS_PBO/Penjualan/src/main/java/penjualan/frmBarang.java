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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class frmBarang extends javax.swing.JFrame {
    
    Connection Con;
    ResultSet RsBrg;
    Statement stm;
    Boolean ada = false;
    String sSatuan;
    Boolean edit = false;
    private Object[][] dataTable = null;
    private String[] header = {"Kode","Nama Barang","Satuan","Harga","Harga Beli","Stok","Stok Min"};
    
    
    public frmBarang() {
      initComponents();
      open_db();
      baca_data();
      aktif(false);
      setTombol(true);
    }
    
    
//method untuk memindahkan data dr table ke form
private void setField()
{
    int row = tblBrg.getSelectedRow();
    txtKode.setText((String)tblBrg.getValueAt(row,0));
    txtNama.setText((String)tblBrg.getValueAt(row,1));
    cmbSatuan.setSelectedItem((String)tblBrg.getValueAt(row,2));
    String harga = Double.toString((Double)tblBrg.getValueAt(row,3));
    txtHarga.setText(harga);
    String hargabeli = Double.toString((Double)tblBrg.getValueAt(row,4));
    txtHargaBeli.setText(harga);
    String stok=Integer.toString((Integer)tblBrg.getValueAt(row,5));
    txtStok.setText(stok);
    String stokmin=Integer.toString((Integer)tblBrg.getValueAt(row,6));
    txtStokMin.setText(stokmin);
}

//method membuka database server, user, pass, database disesuaikan
private void open_db()
{ 
    try{
        KoneksiMysql kon = new KoneksiMysql("localhost","root","","pbo");
        Con = kon.getConnection();
        //System.out.println("Berhasil ");
    }catch (Exception e) {
        System.out.println("Error : "+e);
    }
}

//method baca data dari Mysql dimasukkan ke table pada form
private void baca_data()
{
    try{
        stm = Con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            stm = Con.createStatement();
        RsBrg = stm.executeQuery("select * from barang");
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
//            DefaultTableModel model = (DefaultTableModel) tblBrg.getModel();
//            model.setDataVector(dataTable, header);
    }
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
}

//untuk mengkosongkan isian data
private void kosong()
{
    txtKode.setText("");
    txtNama.setText("");
    cmbSatuan.setSelectedIndex(0);
    txtHarga.setText("");
    txtStok.setText("");
    txtStokMin.setText("");
}

//mengset aktif tidak isian data
private void aktif(boolean x)
{
    txtKode.setEditable(x);
    txtNama.setEditable(x);
    //cmbSatuan.setEditable(x);
    cmbSatuan.setEnabled(x);
    txtHarga.setEditable(x);
    txtStok.setEditable(x);
    txtStokMin.setEditable(x);
}

//mengset tombol on/off
private void setTombol(boolean t)
{
    cmdTambah.setEnabled(t);
    cmdKoreksi.setEnabled(t);
    cmdHapus.setEnabled(t);
    cmdSimpan.setEnabled(!t);
    cmdBatal.setEnabled(!t);
    cmdKeluar.setEnabled(t);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        cmbSatuan = new javax.swing.JComboBox<>();
        txtStok = new javax.swing.JTextField();
        txtStokMin = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBrg = new javax.swing.JTable();
        cmdTambah = new javax.swing.JButton();
        cmdSimpan = new javax.swing.JButton();
        cmdKoreksi = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtHargaBeli = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("Data Barang");

        jLabel2.setText("Kode Barang");

        jLabel3.setText("Nama Barang");

        jLabel4.setText("Satuan");

        jLabel5.setText("Harga");

        jLabel6.setText("Stok");

        jLabel7.setText("Stok Minimal");

        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buah", "Liter", "Kilogram" }));
        cmbSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSatuanActionPerformed(evt);
            }
        });

        tblBrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Barang", "Satuan", "Harga", "Harga Beli", "Stok ", "Stok Min"
            }
        ));
        tblBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBrgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBrg);

        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        cmdSimpan.setText("Simpan");
        cmdSimpan.setEnabled(false);
        cmdSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSimpanMouseClicked(evt);
            }
        });
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdKoreksi.setText("Koreksi");
        cmdKoreksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKoreksiActionPerformed(evt);
            }
        });

        cmdHapus.setText("Hapus");
        cmdHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusActionPerformed(evt);
            }
        });

        cmdBatal.setText("Batal");
        cmdBatal.setEnabled(false);
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        jLabel8.setText("Harga Beli");

        txtHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaBeliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdTambah)
                        .addGap(18, 18, 18)
                        .addComponent(cmdSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(cmdKoreksi)
                        .addGap(18, 18, 18)
                        .addComponent(cmdHapus)
                        .addGap(18, 18, 18)
                        .addComponent(cmdBatal)
                        .addGap(18, 18, 18)
                        .addComponent(cmdKeluar)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(263, 263, 263))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(254, 254, 254)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStokMin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtStokMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdKoreksi)
                    .addComponent(cmdHapus)
                    .addComponent(cmdBatal)
                    .addComponent(cmdKeluar))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
   
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void cmdSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSimpanMouseClicked
String tKode=txtKode.getText();
String tNama=txtNama.getText();
double hrg=Double.parseDouble(txtHarga.getText());
double hrgbeli=Double.parseDouble(txtHargaBeli.getText());
int stk=Integer.parseInt(txtStok.getText());
int stkMin=Integer.parseInt(txtStokMin.getText());


    try{
        if (edit==true)
        {
            stm.executeUpdate("update barang set nm_brg='"+tNama+"',satuan='"+sSatuan+"',"
                    + "harga="+hrg+"harga_beli="+hrgbeli+",stok="+stk+",stok_min="+stkMin+" where kd_brg='" + tKode + "'"
            );
        }else
        {
            stm.executeUpdate(
                    "INSERT into barang(kd_brg, nm_brg, satuan, harga, harga_beli, stok, stok_min) "
                    + "VALUES('"+tKode+"','"+tNama+"','"+sSatuan+"',"+hrg+","+hrgbeli+","+stk+","+stkMin+")"
            );
        }

        tblBrg.setModel(new DefaultTableModel(dataTable,header));
        baca_data();
        aktif(false);
        setTombol(true);
    }catch(SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSimpanMouseClicked

    private void cmbSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSatuanActionPerformed
    JComboBox cSatuan = (javax.swing.JComboBox)evt.getSource();
    //Membaca Item Yang Terpilih — > String
    sSatuan = (String)cSatuan.getSelectedItem();        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSatuanActionPerformed

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
    aktif(true);
    setTombol(false);
    kosong();        // TODO add your handling code here:
    }//GEN-LAST:event_cmdTambahActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
    aktif(false);
    setTombol(true);        // TODO add your handling code here:
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void tblBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBrgMouseClicked
    setField();        // TODO add your handling code here:
    }//GEN-LAST:event_tblBrgMouseClicked

    private void cmdKoreksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKoreksiActionPerformed
    edit=true;
    aktif(true);
    setTombol(false);
    txtKode.setEditable(false);        // TODO add your handling code here:
    }//GEN-LAST:event_cmdKoreksiActionPerformed

    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusActionPerformed
    try{
        String sql="delete from barang where kd_brg='" + txtKode.getText()+ "'";
        stm.executeUpdate(sql);
        baca_data();
        
        edit=false; //set ulang edit agar form tidak masuk ke mode edit
    }
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_cmdHapusActionPerformed

    private void txtHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaBeliActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSimpanActionPerformed

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
            java.util.logging.Logger.getLogger(frmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbSatuan;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdKoreksi;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBrg;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtStokMin;
    // End of variables declaration//GEN-END:variables


}
