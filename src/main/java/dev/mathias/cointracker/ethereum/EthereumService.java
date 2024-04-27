package dev.mathias.cointracker.ethereum;

import dev.mathias.cointracker.bitcoin.Bitcoin;
import dev.mathias.cointracker.bitcoin.BitcoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EthereumService {
    @Autowired
    private EthereumRepository ethereumRepository;

    public void createCoin(String price, Date date) {
        Ethereum ethereum = new Ethereum(price, date);
        ethereumRepository.save(ethereum);
    }

    public List<Ethereum> allCoinsFilteredBy5Minutes() {
        List<Ethereum> allCoins = this.allCoins();
        List<Ethereum> filteredCoins = new ArrayList<>();

        if (!allCoins.isEmpty()) {
            Ethereum lastAdded = allCoins.get(0);
            filteredCoins.add(lastAdded);

            for (Ethereum coin : allCoins) {
                long difference = coin.getCreateDate().getTime() - lastAdded.getCreateDate().getTime();
                long twentyMinutesInMs = 5 * 60 * 1000;

                if (difference >= twentyMinutesInMs) {
                    filteredCoins.add(coin);
                    lastAdded = coin;
                }
            }
        }

        return filteredCoins;
    }


    public List<Ethereum> allCoins() {
        return ethereumRepository.findAll();
    }


    public Optional<Ethereum> findCoinByCreateDate() {
        return ethereumRepository.findFirstByOrderByCreateDateDesc();
    }
}