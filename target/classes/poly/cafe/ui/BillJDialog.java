/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poly.cafe.ui;

import java.awt.Frame;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import poly.cafe.dao.impl.BillDAO;
import poly.cafe.dao.impl.BillDAOImpl;
import poly.cafe.entity.Bill;
import poly.cafe.entity.BillDetail;
import poly.cafe.util.XDate;
import poly.cafe.util.XDialog;
import poly.cafe.dao.impl.BillDetailDAO;
import static poly.cafe.dao.impl.BillDetailDAO.billDetailDao;
import static poly.cafe.dao.impl.BillDetailDAO.billDetails;
import poly.cafe.dao.impl.BillDetailDAOImpl;

@Setter
/**
 *
 * @author lengh
 */
public class BillJDialog extends JDialog implements BillController {

    BillDetailDAO billDetailDao = new BillDetailDAOImpl();
    List<BillDetail> details = List.of(); // chi tiết phiếu bán hàng
    BillDAO billDao = new BillDAOImpl();
    private Bill bill;

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public void removeDrinks() { // xóa đồ uống được tích chọn
        for (int i = 0; i < tblBillDetails.getRowCount(); i++) {
            Boolean checked = (Boolean) tblBillDetails.getValueAt(i, 0);
            if (checked) {
                billDetailDao.deleteById(billDetails.get(i).getId());
            }
        }
        this.fillBillDetails();
    }

    @Override
    public void showDrinkJDialog() { // hiển thị cửa sổ chọn và bổ sung đồ uống
        DrinkJDialog dialog = new DrinkJDialog((Frame) this.getOwner(), true);
        dialog.setBill(bill); // Khai báo vào DrinkJDialog @Setter Bill bill
        dialog.setVisible(true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                BillJDialog.this.fillBillDetails();
            }
        });
    }

    @Override
    public void updateQuantity() { // thay đổi số lượng đồ uống
        if (bill.getStatus() == 0) { // chưa thanh toán hoặc chưa bị canceled
            String input = XDialog.prompt("Số lượng mới?");
            if (input != null && input.length() > 0) {
                BillDetail detail = billDetails.get(tblBillDetails.getSelectedRow());
                detail.setQuantity(Integer.parseInt(input));
                billDetailDao.update(detail);
                this.fillBillDetails();
            }
        }
    }

    @Override
    public void checkout() {
        if (XDialog.confirm("Bạn muốn thanh toán phiếu bán hàng?")) {
            bill.setStatus(Bill.Status.Completed.ordinal());
            bill.setCheckout(new Date());
            billDao.update(bill);
            this.setForm(bill);
        }
    }

    @Override
    public void cancel() {
        if (billDetails.isEmpty()) {
            billDao.deleteById(bill.getId());
            this.dispose();
        } else if (XDialog.confirm("Bạn muốn hủy phiếu bán hàng?")) {
            bill.setStatus(Bill.Status.Canceled.ordinal());
            billDao.update(bill);
            this.setForm(bill);
        }
    }

    void setForm(Bill bill) { // hiển thị bill lên form
        txtId.setText(String.valueOf(bill.getId()));
        txtCardId.setText("Card #" + bill.getCardId());
        txtCheckin.setText(XDate.format(bill.getCheckin(), "HH:mm:ss dd-MM-yyyy"));
        txtUsername.setText(bill.getUsername());
        String[] statuses = {"Servicing", "Completed", "Canceled"};
        txtStatus.setText(statuses[bill.getStatus()]);
        if (bill.getCheckout() != null) {
            txtCheckout.setText(XDate.format(bill.getCheckout(), "HH:mm:ss dd-MM-yyyy"));
        }
        boolean editable = (bill.getStatus() == 0);
        btnAdd.setEnabled(editable);
        btnCancel.setEnabled(editable);
        btnCancel.setEnabled(editable);
        btnRemove.setEnabled(editable);
    }
//    @Override

    public void fillBillDetails() {
        DefaultTableModel model = (DefaultTableModel) tblBillDetails.getModel();
        model.setRowCount(0);
        details = List.of();
        if (!txtId.getText().isBlank()) {
            Long billId = Long.valueOf(txtId.getText());
            details = billDetailDao.findByBillId(billId);
        }
        details.forEach(d -> {
            var amount = d.getUnitPrice() * d.getQuantity() * (1 - d.getDiscount());
            Object[] rowData = {
                d.getDrinkName(),
                String.format("%.1f VNĐ", d.getUnitPrice()),
                String.format("%.0f%%", d.getDiscount() * 100),
                d.getQuantity(), String.format("%.1f VNĐ", amount)
            };
            model.addRow(rowData);
        });
    }

    /**
     * Creates new form BillJDialog
     */
    public BillJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCardId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCheckin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCheckout = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Thẻ số", "Thời điểm tạo phiếu ", "Thời điểm thanh toán ", "Trạng thái "
            }
        ));
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBillDetails);

        jLabel7.setText("Mã phiếu ");

        jLabel8.setText("Thẻ số ");

        jLabel9.setText("Thời điểm đặt hàng ");

        jLabel13.setText("Nhân viên ");

        jLabel14.setText("Trạng thái ");

        jLabel15.setText("Thời điểm thanh toán");

        btnRemove.setText("Xóa đồ uống ");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm đồ uống ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCheckout.setText("Thanh toán ");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy phiếu ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtCardId, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnRemove)
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckout)
                .addGap(18, 18, 18)
                .addComponent(btnCancel)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCardId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnAdd)
                    .addComponent(btnCheckout)
                    .addComponent(btnCancel))
                .addGap(16, 16, 16))
        );

        pack();
        this.fillBillDetails();
        this.initLoadDataField();
    }// </editor-fold>//GEN-END:initComponents

    private void initLoadDataField() {
        var listBills = this.billDetailDao.getBills();
        if (listBills != null && !listBills.isEmpty()) {
            // lấy dữ liệu đầu tiên để hiển thị
            Bill firstBill = listBills.get(0);

            txtId.setText(String.valueOf(firstBill.getId()));
            txtCardId.setText("Card #" + firstBill.getCardId());
            txtCheckin.setText(XDate.format(firstBill.getCheckin(), "HH:mm:ss dd-MM-yyyy"));
            txtCheckout.setText(firstBill.getCheckout() != null
                    ? XDate.format(firstBill.getCheckout(), "HH:mm:ss dd-MM-yyyy")
                    : "");
            txtUsername.setText(firstBill.getUsername());
            String[] statuses = { "Servicing", "Completed", "Canceled" };
            txtStatus.setText(statuses[firstBill.getStatus()]);
        } else {
            txtId.setText("");
            txtCardId.setText("");
            txtCheckin.setText("");
            txtCheckout.setText("");
            txtUsername.setText("");
            txtStatus.setText("");
        }

    }

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        if (evt.getClickCount() == 2) {
            this.updateQuantity();
        }
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.showDrinkJDialog();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        this.removeDrinks();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.cancel();

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        this.checkout();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.close();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BillJDialog dialog = new BillJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTextField txtCardId;
    private javax.swing.JTextField txtCheckin;
    private javax.swing.JTextField txtCheckout;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
    }

    @Override
    public void close() {
    }
}
