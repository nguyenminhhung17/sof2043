/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poly.cafe.ui.manager;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import poly.cafe.dao.impl.CardDAO;
import poly.cafe.dao.impl.CardDAOImpl;
import poly.cafe.entity.Card;
import poly.cafe.util.XDialog;

/**
 *
 * @author lengh
 */
public class CardManagerJDialog extends JDialog implements CardController {

    CardDAO dao = new CardDAOImpl();
    List<Card> items = List.of();

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void setForm(Card entity) {
        txtMaThe.setText(entity.getId().toString());
        int status = entity.getStatus();
        if (status == 0) {
            rdoOperating.setSelected(true);
        } else if (status == 1) {
            rdoError.setSelected(true);
        } else {
            rdoLose.setSelected(true);
        }
    }

    @Override
    public Card getForm() {
        Card entity = new Card();
        entity.setId("" + Integer.parseInt(txtMaThe.getText()));
        //entity.setId(Integer.valueOf(txtMaThe.getText()));
        int status = 0;
        if (rdoError.isSelected()) {
            status = 1;
        } else if (rdoLose.isSelected()) {
            status = 2;
        }
        entity.setStatus(status);
        return entity;
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblCards.getModel();
        model.setRowCount(0);
        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId(), item.getStatus(), false
            };
            model.addRow(rowData);
        });
    }

    @Override
    public void edit() {
        Card entity = items.get(tblCards.getSelectedRow());
        this.setForm(entity);
        this.setEditable(true);
        tabs.setSelectedIndex(1);
    }

    @Override
    public void create() {
        Card entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Card entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
        if (XDialog.confirm("Bạn thực sự muốn xóa?")) {
            String id = txtMaThe.getText();
            dao.deleteById(Integer.valueOf(id));
            this.fillToTable();
            this.clear();
        }
    }

    @Override
    public void clear() {
        this.setForm(new Card());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {
        txtMaThe.setEnabled(!editable);
        btnCreate.setEnabled(!editable);
        btnUpdate.setEnabled(editable);
        btnDelete.setEnabled(editable);

        int rowCount = tblCards.getRowCount();
        btnMoveFirst.setEnabled(editable && rowCount > 0);
        btnMovePrevious.setEnabled(editable && rowCount > 0);
        btnMoveNext.setEnabled(editable && rowCount > 0);
        btnMoveLast.setEnabled(editable && rowCount > 0);
    }

    @Override
    public void checkAll() {
        this.setCheckedAll(true);
    }

    @Override
    public void uncheckAll() {
        this.setCheckedAll(false);

    }

    private void setCheckedAll(boolean checked) {
        for (int i = 0; i < tblCards.getRowCount(); i++) {
            tblCards.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblCards.getRowCount(); i++) {
                if ((Boolean) tblCards.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId());
                }
            }
            this.fillToTable();
        }
    }

    @Override
    public void moveFirst() {
        this.moveTo(0);
    }

    @Override
    public void movePrevious() {
        this.moveTo(tblCards.getSelectedRow() - 1);

    }

    @Override
    public void moveNext() {
        this.moveTo(tblCards.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblCards.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblCards.getRowCount()) {
            this.moveFirst();
        } else {
            tblCards.clearSelection();
            tblCards.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    /**
     * Creates new form CardManagerJDialog
     */
    public CardManagerJDialog(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnMoveFirst = new javax.swing.JButton();
        btnMovePrevious = new javax.swing.JButton();
        btnMoveNext = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnMoveLast = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCards = new javax.swing.JTable();
        btnCheckAll = new javax.swing.JButton();
        btnUncheckAll = new javax.swing.JButton();
        btnDeleteCheckedItems = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaThe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdoOperating = new javax.swing.JRadioButton();
        rdoError = new javax.swing.JRadioButton();
        rdoLose = new javax.swing.JRadioButton();
        btnCreate1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        btnMoveFirst1 = new javax.swing.JButton();
        btnMovePrevious1 = new javax.swing.JButton();
        btnMoveNext1 = new javax.swing.JButton();
        btnMoveLast1 = new javax.swing.JButton();

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

        jLabel3.setText("Tên loại ");

        btnCreate.setText("Tạo mới");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnMoveFirst.setText("|<");
        btnMoveFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveFirstActionPerformed(evt);
            }
        });

        btnMovePrevious.setText("<<");
        btnMovePrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovePreviousActionPerformed(evt);
            }
        });

        btnMoveNext.setText(">>");
        btnMoveNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveNextActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã loại ");

        btnMoveLast.setText(">|");
        btnMoveLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveLastActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblCards.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thẻ", "Trạng thái ", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCards.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCardsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCards);

        btnCheckAll.setText("Chọn tất cả");
        btnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAllActionPerformed(evt);
            }
        });

        btnUncheckAll.setText("Bỏ chọn tất cả");
        btnUncheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUncheckAllActionPerformed(evt);
            }
        });

        btnDeleteCheckedItems.setText("Xóa các mục chọn");
        btnDeleteCheckedItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCheckedItemsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(btnCheckAll)
                .addGap(18, 18, 18)
                .addComponent(btnUncheckAll)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCheckedItems)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckAll)
                    .addComponent(btnUncheckAll)
                    .addComponent(btnDeleteCheckedItems))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH ", jPanel2);

        jLabel1.setText("Mã thẻ ");

        jLabel2.setText("Trạng thái ");

        buttonGroup1.add(rdoOperating);
        rdoOperating.setText("Operating");

        buttonGroup1.add(rdoError);
        rdoError.setText("Error");

        buttonGroup1.add(rdoLose);
        rdoLose.setText("Lose");

        btnCreate1.setText("Tạo mới");
        btnCreate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate1ActionPerformed(evt);
            }
        });

        btnUpdate1.setText("Cập nhật");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        btnClear1.setText("Làm mới");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnMoveFirst1.setText("|<");
        btnMoveFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveFirst1ActionPerformed(evt);
            }
        });

        btnMovePrevious1.setText("<<");
        btnMovePrevious1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovePrevious1ActionPerformed(evt);
            }
        });

        btnMoveNext1.setText(">>");
        btnMoveNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveNext1ActionPerformed(evt);
            }
        });

        btnMoveLast1.setText(">|");
        btnMoveLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveLast1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdoOperating)
                        .addGap(18, 18, 18)
                        .addComponent(rdoError)
                        .addGap(18, 18, 18)
                        .addComponent(rdoLose))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCreate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(btnMoveFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMovePrevious1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoveNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoveLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtMaThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoOperating)
                    .addComponent(rdoError)
                    .addComponent(rdoLose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate1)
                    .addComponent(btnUpdate1)
                    .addComponent(btnDelete1)
                    .addComponent(btnClear1)
                    .addComponent(btnMoveFirst1)
                    .addComponent(btnMovePrevious1)
                    .addComponent(btnMoveNext1)
                    .addComponent(btnMoveLast1))
                .addGap(19, 19, 19))
        );

        tabs.addTab("BIỂU MẪU ", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCardsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCardsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.edit();
        }
    }//GEN-LAST:event_tblCardsMouseClicked

    private void btnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAllActionPerformed
        // TODO add your handling code here:
        this.checkAll();
    }//GEN-LAST:event_btnCheckAllActionPerformed

    private void btnUncheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUncheckAllActionPerformed
        // TODO add your handling code here:
        this.uncheckAll();
    }//GEN-LAST:event_btnUncheckAllActionPerformed

    private void btnDeleteCheckedItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCheckedItemsActionPerformed
        // TODO add your handling code here:
        this.deleteCheckedItems();
    }//GEN-LAST:event_btnDeleteCheckedItemsActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        this.create();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnMoveFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveFirstActionPerformed
        // TODO add your handling code here:
        this.moveFirst();
    }//GEN-LAST:event_btnMoveFirstActionPerformed

    private void btnMovePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovePreviousActionPerformed
        // TODO add your handling code here:
        this.movePrevious();
    }//GEN-LAST:event_btnMovePreviousActionPerformed

    private void btnMoveNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveNextActionPerformed
        // TODO add your handling code here:
        this.moveNext();
    }//GEN-LAST:event_btnMoveNextActionPerformed

    private void btnMoveLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveLastActionPerformed
        // TODO add your handling code here:
        this.moveLast();
    }//GEN-LAST:event_btnMoveLastActionPerformed

    private void btnCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate1ActionPerformed
        // TODO add your handling code here:
        this.create();
    }//GEN-LAST:event_btnCreate1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnMoveFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveFirst1ActionPerformed
        // TODO add your handling code here:
        this.moveFirst();
    }//GEN-LAST:event_btnMoveFirst1ActionPerformed

    private void btnMovePrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovePrevious1ActionPerformed
        // TODO add your handling code here:
        this.movePrevious();
    }//GEN-LAST:event_btnMovePrevious1ActionPerformed

    private void btnMoveNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveNext1ActionPerformed
        // TODO add your handling code here:
        this.moveNext();
    }//GEN-LAST:event_btnMoveNext1ActionPerformed

    private void btnMoveLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveLast1ActionPerformed
        // TODO add your handling code here:
        this.moveLast();
    }//GEN-LAST:event_btnMoveLast1ActionPerformed

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
            java.util.logging.Logger.getLogger(CardManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CardManagerJDialog dialog = new CardManagerJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCheckAll;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreate1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDeleteCheckedItems;
    private javax.swing.JButton btnMoveFirst;
    private javax.swing.JButton btnMoveFirst1;
    private javax.swing.JButton btnMoveLast;
    private javax.swing.JButton btnMoveLast1;
    private javax.swing.JButton btnMoveNext;
    private javax.swing.JButton btnMoveNext1;
    private javax.swing.JButton btnMovePrevious;
    private javax.swing.JButton btnMovePrevious1;
    private javax.swing.JButton btnUncheckAll;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoError;
    private javax.swing.JRadioButton rdoLose;
    private javax.swing.JRadioButton rdoOperating;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblCards;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaThe;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
