package dev.mathias.cointracker.ethereum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import java.util.Date;

@Entity(name = "etheruemprice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ethereum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String price;
    private boolean isIncreased;
    private Date createDate;

    public Ethereum(String price, Date createDate) {
        this.price = price;
        this.createDate = createDate;
    }
}
