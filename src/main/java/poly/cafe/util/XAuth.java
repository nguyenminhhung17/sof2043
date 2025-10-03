/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import poly.cafe.entity.User;

/**
 *
 * @author leaf

 */
public class XAuth {
    public static User user = User.builder()
            .username("sa")
            .password("123123")
            .enabled(true)
            .manager(true)
            .fullname("Leaf")
            .photo("logo2.png")
            .build(); // biến user này sẽ được thay thế sau khi đăng nhập
}
