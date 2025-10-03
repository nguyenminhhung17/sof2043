/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;

import poly.cafe.entity.Bill;
import poly.cafe.entity.BillDetail;

/**
 *
 * @author lengh
 */
public interface BillDetailDAO extends CrudDAO<BillDetail, Long> {

    List<BillDetail> findByBillId(Long billId);
    List<Bill> getBills();


    List<BillDetail> findByDrinkId(String drinkId);
    BillDetailDAO billDetailDao = new BillDetailDAOImpl(); // hoặc inject từ nơi khác
    Long billId = 1L; // mã hóa đơn muốn lấy chi tiết

    List<BillDetail> billDetails = billDetailDao.findByBillId(billId);

//    for (int i = 0;
//
//    i< tblBillDetails.getRowCount ();
//    i
//
//    
//        ++) {
//    Boolean checked = (Boolean) tblBillDetails.getValueAt(i, 0);
//        if (checked) {
//            billDetailDao.deleteById(billDetails.get(i).getId());
//        }
//    }

}
