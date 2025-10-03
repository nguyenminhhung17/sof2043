/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;

import poly.cafe.entity.Bill;
import poly.cafe.entity.BillDetail;
import poly.cafe.util.XQuery;

/**
 *
 * @author lengh
 */
public class BillDetailDAOImpl implements BillDetailDAO {
    String createSql = "…";
    String updateSql = "…";
    String deleteSql = "…";
    String findAllSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id=bd.DrinkId ";
    String findByIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id=bd.DrinkId WHERE bd.Id=?";
    String findByBillIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id=bd.DrinkId WHERE bd.BillId=?";
    String findByDrinkIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id=bd.DrinkId WHERE bd.DrinkId=?";
    String getAllBill = "select * from BillDetails inner Join Bills on BillDetails.BillId = Bills.Id";

    @Override
    public List<BillDetail> findByBillId(Long billId) {
     return XQuery.getBeanList(BillDetail.class, findByBillIdSql, billId);
    }

    @Override
    public List<BillDetail> findByDrinkId(String drinkId) {
        return null;
    }

    @Override
    public BillDetail create(BillDetail entity) {
        return null;
    }

    @Override
    public void update(BillDetail entity) {
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public List<BillDetail> findAll() {
        return XQuery.getBeanList(BillDetail.class, findAllSql);
    }

    @Override
    public BillDetail findById(Long id) {
        return null;
    }

    @Override
    public BillDetail findByUsername(Long username) {
        return null;
    }

    @Override
    public List<Bill> getBills() {
     return XQuery.getBeanList(Bill.class, getAllBill);
    }

}
