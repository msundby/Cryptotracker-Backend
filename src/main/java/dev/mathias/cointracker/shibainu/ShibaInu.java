//package dev.mathias.cointracker.shibainu;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import jakarta.persistence.Id;
//import java.util.Date;
//
//@Entity(name = "shibainuprices")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class ShibaInu {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String price;
//    private boolean isIncreased;
//    private Date createDate;
//
//    public ShibaInu(String price, Date createDate) {
//        this.price = price;
//        this.createDate = createDate;
//    }
//}
