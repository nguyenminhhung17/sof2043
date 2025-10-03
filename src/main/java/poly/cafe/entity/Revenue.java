/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lengh
 */
public class Revenue {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class ByCategory {

        private String category; // Tên loại
        private double revenue; // Doanh thu
        private int quantity; // Số lượng đồ uống đã bán
        private double minPrice; // Giá bán cao nhất
        private double maxPrice; // Giá bán thấp nhất
        private double avgPrice; // Giá bán trung bình
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class ByUser {

        private String user; // Tên đăng nhập của nhân viên bán hàng
        private double revenue; // Doanh thu
        private int quantity; // Số lượng đơn hàng đã bán
        private Date firstTime; // Thời điểm bán đơn hàng đầu tiên
        private Date lastTime; // Thời điểm bán đơn hàng sau cùng
    }


}
