//package dev.mathias.cointracker.bitcoin;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import jakarta.persistence.Id;
//
//import java.util.Date;
//
//@Entity(name = "bitcoinprice")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Bitcoin {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//    private String price;
//    private boolean isIncreased;
//    private Date createDate;
//
//    public Bitcoin(String price, Date createDate) {
//        this.price = price;
//        this.createDate = createDate;
//    }
//
//
//}
