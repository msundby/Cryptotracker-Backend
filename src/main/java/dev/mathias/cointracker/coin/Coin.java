package dev.mathias.cointracker.coin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;


import java.util.Date;
import java.time.LocalDateTime;

@Entity(name = "top50coins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coinId;
    private String symbol;
    private String color;
    private String iconUrl;
    private String rank;
    private String price;
    private String marketCap;
    private boolean isIncreased;
    @CreatedDate
    private Date createDate;

    public Coin(String coinId, String symbol, String color, String iconUrl, String rank, String price, String marketCap) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.color = color;
        this.iconUrl = iconUrl;
        this.rank = rank;
        this.price = price;
        this.marketCap = marketCap;
    }

    public Coin(String symbol, String iconUrl, String price, boolean isIncreased){
        this.symbol = symbol;
        this.iconUrl = iconUrl;
        this.price = price;
        this.isIncreased = isIncreased;
    }

    public Coin(String price, Date createDate) {
        this.price = price;
        this.createDate = createDate;
    }
}
