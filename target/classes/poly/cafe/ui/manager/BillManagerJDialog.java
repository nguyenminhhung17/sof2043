/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poly.cafe.ui.manager;

import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import poly.cafe.dao.impl.BillDAOImpl;
import poly.cafe.dao.impl.BillDetailDAOImpl;
import poly.cafe.dao.impl.BillDAO;
import poly.cafe.dao.impl.BillDetailDAO;
import poly.cafe.entity.Bill;
import poly.cafe.entity.BillDetail;
import poly.cafe.util.TimeRange;
import poly.cafe.util.XDate;

/**
 *
 * @author lengh
 */
public class BillManagerJDialog extends JDialog implements BillController {

    BillDAO dao = new BillDAOImpl();
    List<Bill> items = List.of(); // phiếu bán hàng
    BillDetailDAO billDetailDao = new BillDetailDAOImpl();
    List<BillDetail> details = List.of(); // chi tiết phiếu bán hàng

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.selectTimeRange();
        this.clear();
    }

    @Override
    public void setForm(Bill entity) {
        this.fillBillDetails();
    }

    @Override
    public void fillBillDetails() {
        DefaultTableModel model = (DefaultTableModel) tblBills.getModel();
        model.setRowCount(0);
        details = List.of();
        if (!txtMaPhieu.getText().isBlank()) {
            Long billId = Long.valueOf(txtMaPhieu.getText());
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

    @Override
    public void selectTimeRange() {
        TimeRange range = TimeRange.today();
        switch (cboTimeRanges.getSelectedIndex()) {
            case 0 ->
                range = TimeRange.today();
            case 1 ->
                range = TimeRange.thisWeek();
            case 2 ->
                range = TimeRange.thisMonth();
            case 3 ->
                range = TimeRange.thisQuarter();
            case 4 ->
                range = TimeRange.thisYear();
        }
        txtBegin.setText(XDate.format(range.getBegin(), "MM/dd/yyyy"));
        txtEnd.setText(XDate.format(range.getEnd(), "MM/dd/yyyy"));
        this.fillToTable();
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblBillDetails.getModel();
        model.setRowCount(0); // Xóa các dòng cũ
        try {
            Date begin = XDate.parse(txtBegin.getText(), "yyyy-MM-dd HH:mm:ss.SSS");
            Date end = XDate.parse(txtEnd.getText(), "yyyy-MM-dd HH:mm:ss.SSS");
            items = dao.findByTimeRange(begin, end); // Truy vấn dữ liệu
            for (Bill item : items) {
                String statusStr;
                if (item.getStatus() == 0) {
                    statusStr = "Chưa thanh toánS";
                } else if (item.getStatus() == 1) {
                    statusStr = "Đã thanh toán";
                } else {
                    statusStr = "Đã hủy";
                }
                String email = item.getUsername() + "@example.com";
                model.addRow(new Object[]{
                    item.getId(),
                    item.getUsername(),
                    item.getCardId(),
                    XDate.toString(item.getCheckin(), "yyyy-MM-dd HH:mm:ss"),
                    statusStr,
                    email,
                    false // for the checkbox column
                });
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi nếu có
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu hóa đơn!");
        }
    }

    public BillManagerJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtBegin.setText("2025-05-14 22:41:26.557");
        txtEnd.setText("2025-06-10 13:00:00.000");
        fillBillDetails();
        fillToTable();
    }

    public void showDetail() {
        try {
            int index = tblBillDetails.getSelectedRow();
            if (index < 0) {
                return;
            }

            Bill b = items.get(index); // dsBill là List<Bill>

            txtMaPhieu.setText(b.getId().toString());
            txtTheSo.setText(b.getCardId().toString());
            txtThoiDiemTao.setText(b.getCheckin().toString());

            if (b.getCheckout() != null) {
                txtThoiDiemThanhToan.setText(b.getCheckout().toString());
            } else {
                txtThoiDiemThanhToan.setText("");
            }

            txtNguoiTao.setText(b.getUsername());

            // Trang thai: 0 - Servicing, 1 - Completed, 2 - Canceled
            if (b.getStatus() == 0) {
                rdoServicing.setSelected(true);
                rdoCompleted.setSelected(false);
                rdoCanceled.setSelected(false);
            } else if (b.getStatus() == 1) {
                rdoServicing.setSelected(false);
                rdoCompleted.setSelected(true);
                rdoCanceled.setSelected(false);
            } else {
                rdoServicing.setSelected(false);
                rdoCompleted.setSelected(false);
                rdoCanceled.setSelected(true);
            }

//            // Load chi tiet neu co
//            loadChiTietPhieu(b.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        btnCheckAll = new javax.swing.JButton();
        btnUncheckAll = new javax.swing.JButton();
        btnDeleteCheckedItems = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBegin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cboTimeRanges = new javax.swing.JComboBox<>();
        txtEnd = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCreate1 = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        btnMoveFirst1 = new javax.swing.JButton();
        btnMovePrevious1 = new javax.swing.JButton();
        btnMoveNext1 = new javax.swing.JButton();
        btnMoveLast1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtThoiDiemTao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoServicing = new javax.swing.JRadioButton();
        rdoCompleted = new javax.swing.JRadioButton();
        txtTheSo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtThoiDiemThanhToan = new javax.swing.JTextField();
        rdoCanceled = new javax.swing.JRadioButton();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBills = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        jLabel11.setText("Trạng thái ");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu ", "Thẻ số", "Thời điểm tạo", "Thời điểm thanh toán ", "Trạng thái ", "Email", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBillDetails);

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

        jLabel7.setText("Từ ngày ");

        jLabel8.setText("Đến ngày ");

        jButton1.setText("Lọc ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboTimeRanges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCheckAll)
                .addGap(18, 18, 18)
                .addComponent(btnUncheckAll)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCheckedItems)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton1)
                    .addComponent(cboTimeRanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckAll)
                    .addComponent(btnUncheckAll)
                    .addComponent(btnDeleteCheckedItems))
                .addGap(15, 15, 15))
        );

        tabs.addTab("DANH SÁCH ", jPanel2);

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

        jLabel1.setText("Mã phiếu ");

        jLabel2.setText("Thẻ số ");

        jLabel6.setText("Trạng thái ");

        buttonGroup1.add(rdoServicing);
        rdoServicing.setText("Servicing");

        buttonGroup1.add(rdoCompleted);
        rdoCompleted.setText("Completed");

        txtTheSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTheSoActionPerformed(evt);
            }
        });

        jLabel4.setText("Thời điểm tạo ");

        jLabel9.setText("Thời điểm thanh toán ");

        buttonGroup1.add(rdoCanceled);
        rdoCanceled.setText("Canceled");

        jLabel10.setText("Người tạo ");

        tblBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Đồ uống ", "Đơn giá ", "GIảm giá ", "Số lượng ", "Thành tiền "
            }
        ));
        jScrollPane2.setViewportView(tblBills);

        jLabel12.setText("Phiếu chi tiết ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreate1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                        .addComponent(btnMoveFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMovePrevious1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMoveNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMoveLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1)
                                            .addComponent(txtMaPhieu)
                                            .addComponent(jLabel4)
                                            .addComponent(txtThoiDiemTao, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTheSo, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(rdoServicing)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoCompleted)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoCanceled)))
                                        .addGap(69, 69, 69)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtThoiDiemThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel2))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTheSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThoiDiemTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThoiDiemThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoServicing)
                            .addComponent(rdoCompleted)
                            .addComponent(rdoCanceled)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        //        // TODO add your handling code here:
        //        if (evt.getClickCount() == 2) {
        //            this.edit();
        //        }
        showDetail();
        fillBillDetails();
    }//GEN-LAST:event_tblBillDetailsMouseClicked

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
        //        this.deleteCheckedItems();
    }//GEN-LAST:event_btnDeleteCheckedItemsActionPerformed

    private void btnMoveLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveLast1ActionPerformed
        // TODO add your handling code here:
        //        this.moveLast();
    }//GEN-LAST:event_btnMoveLast1ActionPerformed

    private void btnMoveNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveNext1ActionPerformed
        // TODO add your handling code here:
        //        this.moveNext();
    }//GEN-LAST:event_btnMoveNext1ActionPerformed

    private void btnMovePrevious1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovePrevious1ActionPerformed
        // TODO add your handling code here:
        //        this.movePrevious();
    }//GEN-LAST:event_btnMovePrevious1ActionPerformed

    private void btnMoveFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveFirst1ActionPerformed
        // TODO add your handling code here:
        //        this.moveFirst();
    }//GEN-LAST:event_btnMoveFirst1ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        //        this.clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        //        this.delete();
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        //        this.update();
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate1ActionPerformed
        // TODO add your handling code here:
        //        this.create();
    }//GEN-LAST:event_btnCreate1ActionPerformed

    private void txtTheSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTheSoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTheSoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BillManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillManagerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BillManagerJDialog dialog = new BillManagerJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnCreate1;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDeleteCheckedItems;
    private javax.swing.JButton btnMoveFirst1;
    private javax.swing.JButton btnMoveLast1;
    private javax.swing.JButton btnMoveNext1;
    private javax.swing.JButton btnMovePrevious1;
    private javax.swing.JButton btnUncheckAll;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTimeRanges;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoCanceled;
    private javax.swing.JRadioButton rdoCompleted;
    private javax.swing.JRadioButton rdoServicing;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTable tblBills;
    private javax.swing.JTextField txtBegin;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtTheSo;
    private javax.swing.JTextField txtThoiDiemTao;
    private javax.swing.JTextField txtThoiDiemThanhToan;
    // End of variables declaration//GEN-END:variables

    @Override
    public Bill getForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEditable(boolean editable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
//    public void checkAll() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void uncheckAll() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    @Override
    public void deleteCheckedItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void movePrevious() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveNext() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void moveTo(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkAll() {
        int rowCount = tblBillDetails.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tblBillDetails.setValueAt(true, i, 6);
        }

    }

    @Override
    public void uncheckAll() {
        int rowCount = tblBillDetails.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tblBillDetails.setValueAt(false, i, 6);
        }
    }
}
