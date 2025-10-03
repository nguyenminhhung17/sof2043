/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.Date;
import java.util.List;
import poly.cafe.entity.Bill;

/**
 *
 * @author lengh
 */
public interface BillDAO extends CrudDAO<Bill, Long>{
    List<Bill> findByUsername(String username);
    List<Bill> findByCardId(Integer cardId);
    List<Bill> findByTimeRange(Date begin ,Date end );
    public Bill findServicingByCardId(Integer cardId);
    List<Bill> findByUserAndTimeRange(String username, Date begin, Date end);
//    BillDAO billDao = new BillDAO();
    BillDAO billDao = new BillDAOImpl();


}