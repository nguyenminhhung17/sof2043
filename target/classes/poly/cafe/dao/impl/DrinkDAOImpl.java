/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.entity.Drink;
import poly.cafe.util.XQuery;

/**
 *
 * @author lengh
 */
public class DrinkDAOImpl implements DrinkDAO {
 String createSql = "…";
 String updateSql = "…";
 String deleteSql = "…";
 String findAllSql = "…";
 String findByIdSql = "…";
 String findByCategoryIdSql = "SELECT * FROM Drinks WHERE CategoryId=?";

    @Override
    public List<Drink> findByCategoryId(String categoryId) {
     return XQuery.getBeanList(Drink.class, findByCategoryIdSql, categoryId);
    }

    @Override
    public Drink create(Drink entity) {
     return null;
    }

    @Override
    public void update(Drink entity) {
    }

    @Override
    public void deleteById(String id) {
    }

    @Override
    public List<Drink> findAll() {
     return null;
    }

    @Override
    public Drink findById(String id) {
     return null;
    }

    @Override
    public Drink findByUsername(String username) {
     return null;
    }
    
    
}
