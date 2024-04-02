package dev.mathias.cointracker.bitcoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bitcoinprice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bitcoin {
    @Id
    private ObjectId id;
    private String price;
    private boolean isIncreased;
    @CreatedDate
    private Date createDate;

    public Bitcoin(String price, Date createDate) {
        this.price = price;
        this.createDate = createDate;
    }
}
