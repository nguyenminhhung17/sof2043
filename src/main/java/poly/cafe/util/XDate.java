/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package poly.cafe.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_SHORT = "MM/dd/yyyy";
    
    private static final SimpleDateFormat formater = new SimpleDateFormat();
    
    public static Date now(){
        return new Date();
    }

    public static Date parse(String dateTime, String pattern) {
    if (dateTime == null || dateTime.trim().isEmpty()) {
        return null; // hoặc throw new IllegalArgumentException("Chuỗi ngày giờ bị rỗng");
    }
    try {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(dateTime);
    } catch (ParseException e) {
        e.printStackTrace(); // Ghi log để debug
        return null;
    }
}

    
    public static Date parse(String dateTime){
        return parse(dateTime, PATTERN_SHORT);
    }
    
    public static String format(Date dateTime, String pattern){
        if(dateTime == null){
            return "";
        }
        formater.applyPattern(pattern);
        return formater.format(dateTime);
    }

    public static String format(Date dateTime){
        return format(dateTime, PATTERN_SHORT);
    }
            
    public static void main(String[] args) {
        Date date = XDate.parse("Jan 21, 2024", "MMM dd, yyyy");
        String text = XDate.format(date, "dd-MMM-yyyy");
        System.out.println(text); // => 21-Jan-2024
    }
    
    public static String toString(Date date, String pattern) {
        if (date == null) return "";
        return new SimpleDateFormat(pattern).format(date);
    }

}
//package poly.cafe.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class XDate {
//
//    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
//    public static final String PATTERN_SHORT = "MM/dd/yyyy";
//
//    // Dùng riêng formatter trong mỗi hàm để tránh lỗi đa luồng
//    public static Date now() {
//        return new Date();
//    }
//
//    /**
//     * Chuyển chuỗi thành Date theo định dạng
//     * @param dateTime chuỗi ngày
//     * @param pattern định dạng, ví dụ "MM/dd/yyyy"
//     * @return java.util.Date hoặc null nếu lỗi
//     */
//    public static Date parse(String dateTime, String pattern) {
//        if (dateTime == null || dateTime.trim().isEmpty()) {
//            System.err.println("Chuỗi ngày bị rỗng hoặc null");
//            return null;
//        }
//
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//            formatter.setLenient(false); // không cho phép định dạng sai
//            return formatter.parse(dateTime);
//        } catch (ParseException e) {
//            System.err.println("Không thể phân tích chuỗi ngày: " + dateTime);
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * Chuyển chuỗi thành Date theo định dạng mặc định MM/dd/yyyy
//     */
//    public static Date parse(String dateTime) {
//        return parse(dateTime, PATTERN_SHORT);
//    }
//
//    /**
//     * Định dạng ngày thành chuỗi theo pattern
//     */
//    public static String format(Date dateTime, String pattern) {
//        if (dateTime == null) {
//            return "";
//        }
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        return formatter.format(dateTime);
//    }
//
//    /**
//     * Định dạng ngày theo định dạng mặc định MM/dd/yyyy
//     */
//    public static String format(Date dateTime) {
//        return format(dateTime, PATTERN_SHORT);
//    }
//
//    /**
//     * Hàm thay thế format() – viết rõ tên hơn
//     */
//    public static String toString(Date dateTime, String pattern) {
//        return format(dateTime, pattern);
//    }
//
//    /**
//     * Test nhanh lớp XDate
//     */
//    public static void main(String[] args) {
//        // Test parse
//        Date date = XDate.parse("01/21/2024", "MM/dd/yyyy");
//        System.out.println("Parsed date: " + date);
//
//        // Test format
//        String text = XDate.format(date, "dd-MMM-yyyy");
//        System.out.println("Formatted: " + text);
//
//        // Test lỗi định dạng
//        Date bad = XDate.parse("2024-99-99", "yyyy-MM-dd");
//        System.out.println("Result with bad date: " + bad);
//    }
//}
