package dev.mathias.cointracker.coinprice;

import dev.mathias.cointracker.coin.Coin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity(name = "coinprice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String price;
    private boolean isIncreased;
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    Coin coin;

    public CoinPrice(String price, boolean isIncreased, Date createDate, Coin coin) {
        this.price = price;
        this.isIncreased = isIncreased;
        this.createDate = createDate;
        this.coin = coin;
    }
}
