package dev.mathias.cointracker.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BitcoinService {
    @Autowired
    private BitcoinRepository bitcoinRepository;

    public void createCoin(String price, Date date) {
        Bitcoin bitcoin = new Bitcoin(price, date);
        bitcoinRepository.save(bitcoin);
    }

    public List<Bitcoin> allCoinsFilteredBy5Minutes() {
        List<Bitcoin> allCoins = this.allCoins();
        List<Bitcoin> filteredCoins = new ArrayList<>();

        if (!allCoins.isEmpty()) {
            Bitcoin lastAdded = allCoins.get(0);
            filteredCoins.add(lastAdded);

            for (Bitcoin coin : allCoins) {
                long difference = coin.getCreateDate().getTime() - lastAdded.getCreateDate().getTime();
                long oneMinutesInMs = 1 * 60 * 1000;

                if (difference >= oneMinutesInMs) {
                    filteredCoins.add(coin);
                    lastAdded = coin;
                }
            }
        }

        return filteredCoins;
    }


    public List<Bitcoin> allCoins() {
        return bitcoinRepository.findAll();
    }


    public Optional<Bitcoin> findCoinByCreateDate() {
        return bitcoinRepository.findFirstByOrderByCreateDateDesc();
    }
}