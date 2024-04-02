package dev.mathias.cointracker.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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


    public List<Bitcoin> allCoins() {
        return bitcoinRepository.findAll();
    }


    public Optional<Bitcoin> findCoinByCreateDate() {
        return bitcoinRepository.findFirstByOrderByCreateDateDesc();
    }
}