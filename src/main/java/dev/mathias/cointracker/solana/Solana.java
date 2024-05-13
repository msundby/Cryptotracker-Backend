//package dev.mathias.cointracker.solana;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.util.Date;
//
//@Entity(name = "solanaprices")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Solana {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String price;
//    private boolean isIncreased;
//    private Date createDate;
//
//    public Solana(String price, Date createDate) {
//        this.price = price;
//        this.createDate = createDate;
//    }
//}
