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

    private double price;
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    Coin coin;

    public CoinPrice(double price, Date createDate, Coin coin) {
        this.price = price;
        this.createDate = createDate;
        this.coin = coin;
    }
}

