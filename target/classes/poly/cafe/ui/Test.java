/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.ui;

import java.sql.Connection;
import java.sql.ResultSet;
import poly.cafe.util.XJdbc;
import java.util.List;
import poly.cafe.entity.Category;
import poly.cafe.util.XQuery;
public class Test {
    public static void main(String[] args) {
//        String sql = "INSERT INTO Categories (Id, Name) VALUES (?, ?)";
//        
//        // them moi
//        XJdbc.executeUpdate(sql, "C01", "Loai 1");
//        XJdbc.executeUpdate(sql, "C02", "Loai 2");
//        System.out.println("Them moi thanh cong!");
//
//        // Truy van nhieu ban ghi
//        String selectSql = "SELECT * FROM Categories WHERE Name LIKE ?";
//        ResultSet rs = XJdbc.executeQuery(selectSql, "%Loai%");
        // Truy xuat nhieu ban ghi va chuyen sang List<Bean>
//        String sqlToList = "SELECT * FROM Categories WHERE Name LIKE ?";
//        List<Category> beans = XJdbc.getBeanList(Category.class, sqlToList, "%Loai%");
        // Truy xuat mot ban ghi va chuyen sang Bean
//        String sqlSingle = "SELECT * FROM Categories WHERE Id=?";
//        Category bean = XJdbc.getSingleBean(Category.class, sqlSingle, "C02");

           //Truy van 1 gia tri
//        String sqlMaxId = "SELECT max(Id) FROM Categories WHERE Name LIKE ?";
//        String maxId = (String) XJdbc.getValue(sqlMaxId, "%Loai%");
//        System.out.printf("Max Id: %s\n", maxId);
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

