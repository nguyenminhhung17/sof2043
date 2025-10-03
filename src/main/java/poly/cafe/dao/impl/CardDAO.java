/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.cafe.dao.impl;

import poly.cafe.entity.Card;

/**
 *
 * @author lengh
 */
public interface CardDAO extends CrudDAO<Card, Integer>{

    public void deleteById(String id);
    
}
