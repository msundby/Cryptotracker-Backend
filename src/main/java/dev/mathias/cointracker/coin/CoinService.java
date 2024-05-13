package dev.mathias.cointracker.coin;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public Coin createCoin(String symbol, String color, String iconUrl, String rank, String marketCap) {
        Coin coin = coinRepository.save(new Coin(color, symbol, iconUrl, rank, marketCap));
        return coin;
    }

    public Coin getCoinBySymbol(String symbol) {
        return coinRepository.findBySymbol(symbol);
    }

    public List<Coin> allCoins() {
        return coinRepository.findAll();
    }

    public Optional<Coin> singleCoin(Long id) {
        return coinRepository.findById(id);
    }

    public Optional<Coin> findCoinByCreateDate() {
        return coinRepository.findFirstByOrderByCreateDateDesc();
    }
}