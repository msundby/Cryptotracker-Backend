package dev.mathias.cointracker;

import lombok.Data;

@Data
public class CoinDTO {
    private String uuid;
    private String symbol;
    private String color;
    private String iconUrl;
    private String rank;
    private String price;
}
