/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.ui;
import javax.swing.SwingUtilities;

/**
 *
 * @author lengh
 */
public class PolyCafe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Mở login duy nhất tại đây
            LoginJDialog login = new LoginJDialog(null, true);
            login.setVisible(true);

            // Khi login thành công thì mở giao diện chính
            if (login.isLoginSuccessful()) {
                new PolyCafeJFrame().setVisible(true);
            } else {
                System.exit(0); // Thoát nếu login fail hoặc cancel
            }
        });
    }
}
     