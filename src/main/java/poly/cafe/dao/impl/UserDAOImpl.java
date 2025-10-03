///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package poly.cafe.dao.impl;
//
//import java.util.List;
//import poly.cafe.entity.User;
//import poly.cafe.util.XQuery;
//
///**
// *
// * @author lengh
// */
//public class UserDAOImpl implements UserDAO {
//    String createSql = "…";
//    String updateSql = "…";
//    String deleteSql = "…";
//    String findAllSql = "…";
//    String findByUsernameSql = "SELECT * FROM Users WHERE Username=?";
//
//    @Override
//    public User create(User entity) {
//        return null;
//    }
//
//    @Override
//    public void update(User entity) {
//    }
//
//    @Override
//    public void deleteById(String id) {
//    }
//
//    @Override
//    public List<User> findAll() {
//        return null;
//    }
//
//    @Override
//    public User findById(String id) {
//        return null;
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return XQuery.getSingleBean(User.class, findByUsernameSql, username);
//    }
//}
//

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.ArrayList;
import java.util.List;
import poly.cafe.entity.User;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author user
 */
public class UserDAOImpl implements UserDAO {

    private final String createSql = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager) VALUES(?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? WHERE Username=?";
    private final String deleteByIdSql = "DELETE FROM Users WHERE Username=?";

    private final String findAllSql = "SELECT * FROM Users";
    private final String findByIdSql = "SELECT * FROM Users WHERE Username=?";
    String findByUsernameSql = "SELECT * FROM Users WHERE Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List< User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id);
    }
     @Override
    public User findByUsername(String username) {
        return XQuery.getSingleBean(User.class, findByUsernameSql, username);
    }

}

