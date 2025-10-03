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
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Setter 
public class Bill {
 private Long id;
 private String username;
 private Integer cardId;
 @Builder.Default
 private Date checkin = new Date();
 private Date checkout;
// private int status;
public enum Status {
        Pending, Completed, Canceled // thêm Canceled vào đây
    }

    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

//    public Bill(Long id, String username, Integer cardId, Date checkin, Date checkout, int status) {
//        this.id = id;
//        this.username = username;
//        this.cardId = cardId;
//        this.checkin = checkin;
//        this.checkout = checkout;
//        this.status = status;
//    }
}
