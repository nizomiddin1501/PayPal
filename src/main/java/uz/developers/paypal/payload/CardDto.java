package uz.developers.paypal.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CardDto {

    private String cardNumber;
    private Date expiredDate;
    private double balance;

}
