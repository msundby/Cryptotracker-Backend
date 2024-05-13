package dev.mathias.cointracker.coinprice;

import dev.mathias.cointracker.coin.Coin;
import dev.mathias.cointracker.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CoinPriceService {

    @Autowired
    CoinPriceRepository coinPriceRepository;

    @Autowired
    CoinService coinService;

    public void createCoinPrice(String price, Date date, boolean isIncreased, String symbol) {
        Coin coin = coinService.getCoinBySymbol(symbol);
        if (coin != null) {
            CoinPrice coinPrice = new CoinPrice(price, isIncreased, date, coin);
            coinPriceRepository.save(coinPrice);
        }
    }

    public List<CoinPrice> allCoinsFilteredBy5Minutes(Long id) {
        List<CoinPrice> allCoins = this.allCoinPricesByCoinId(id);
        List<CoinPrice> filteredCoins = new ArrayList<>();

        if (!allCoins.isEmpty()) {
            CoinPrice lastAdded = allCoins.get(0);
            filteredCoins.add(lastAdded);

            for (CoinPrice coinPrice : allCoins) {
                long difference = coinPrice.getCreateDate().getTime() - lastAdded.getCreateDate().getTime();
                long oneMinutesInMs = 1 * 60 * 1000;

                if (difference >= oneMinutesInMs) {
                    filteredCoins.add(coinPrice);
                    lastAdded = coinPrice;
                }
            }
        }

        return filteredCoins;
    }

    public Double getNewestCoinPrice(Long id) {
        Optional<CoinPrice> coin = findCoinByCreateDate(id);
        if (coin.isPresent()) {
            CoinPrice actualCoin = coin.get();
            return Double.parseDouble(actualCoin.getPrice());
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    public Optional<CoinPrice> findCoinByCreateDate(Long id) {
        return coinPriceRepository.findFirstByCoinIdEqualsOrderByCreateDateDesc(id);
    }


    public List<CoinPrice> allCoinPricesByCoinId(Long coinId) {
        return coinPriceRepository.findByCoinId(coinId);
    }


    public Long findCoinIdBySymbol(String symbol){
        Coin coin = coinService.getCoinBySymbol(symbol);
        if(coin != null){
            return coin.getId();
        } else {
            return null;
        }
    }
}
