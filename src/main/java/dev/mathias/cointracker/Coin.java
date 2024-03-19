package dev.mathias.cointracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Document(collection = "coins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    @Id
    private ObjectId id;
    private String coinId;
    private String symbol;
    private String color;
    private String iconUrl;
    private String rank;
    private String price;
    private boolean isIncreased;

    public Coin(String coinId, String symbol, String color, String iconUrl, String rank, String price) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.color = color;
        this.iconUrl = iconUrl;
        this.rank = rank;
        this.price = price;
    }

    public Coin(String symbol, String iconUrl, String price, boolean isIncreased){
        this.symbol = symbol;
        this.iconUrl = iconUrl;
        this.price = price;
        this.isIncreased = isIncreased;
    }
}
