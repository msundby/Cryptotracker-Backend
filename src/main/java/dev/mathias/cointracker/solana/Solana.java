package dev.mathias.cointracker.solana;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "solanaprice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solana {
    @Id
    private ObjectId id;
    private String price;
    private boolean isIncreased;
    private Date createDate;

    public Solana(String price, Date createDate) {
        this.price = price;
        this.createDate = createDate;
    }
}
