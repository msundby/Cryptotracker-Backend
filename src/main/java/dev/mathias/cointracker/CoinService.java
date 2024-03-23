package dev.mathias.cointracker;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private CoinRepository coinRepository;

    public Coin createCoin(String coinId, String symbol, String color, String iconUrl, String rank, String price) {
        Coin coin = coinRepository.insert(new Coin(coinId, color, symbol, iconUrl, rank, price));
        return coin;
    }

    public List<Coin> allCoins() {
        return coinRepository.findAll();
    }

    public Optional<Coin> singleCoin(ObjectId id) {
        return coinRepository.findById(id);
    }

    public Optional<Coin> findCoinByCoinId(String coinId) {
        return coinRepository.findCoinByCoinId(coinId);
    }


    public Optional<Coin> findCoinByCreateDate() {
        return coinRepository.findFirstByOrderByCreateDateDesc();
    }
}