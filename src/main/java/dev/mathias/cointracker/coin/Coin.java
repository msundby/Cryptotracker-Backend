package dev.mathias.cointracker.coin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String marketCap;
    @CreatedDate
    private Date createDate;

    public Coin(String coinId, String symbol, String color, String iconUrl, String rank, String marketCap) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.color = color;
        this.iconUrl = iconUrl;
        this.rank = rank;
        this.marketCap = marketCap;
    }

    public Coin(String symbol, String iconUrl, String price, boolean isIncreased){
        this.symbol = symbol;
        this.iconUrl = iconUrl;
    }

    public Coin(String price, Date createDate) {
        this.createDate = createDate;
    }

    public Coin(Coin coinBySymbol) {
    }
}
