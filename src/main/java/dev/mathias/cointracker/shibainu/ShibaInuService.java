//package dev.mathias.cointracker.shibainu;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ShibaInuService {
//
//    @Autowired
//    private ShibaInuRepository shibaInuRepository;
//
//    public void createCoin(String price, Date date) {
//        ShibaInu shibaInu = new ShibaInu(price, date);
//        shibaInuRepository.save(shibaInu);
//    }
//
//    public List<ShibaInu> allCoinsFilteredBy5Minutes() {
//        List<ShibaInu> allCoins = this.allCoins();
//        List<ShibaInu> filteredCoins = new ArrayList<>();
//
//        if (!allCoins.isEmpty()) {
//            ShibaInu lastAdded = allCoins.get(0);
//            filteredCoins.add(lastAdded);
//
//            for (ShibaInu coin : allCoins) {
//                long difference = coin.getCreateDate().getTime() - lastAdded.getCreateDate().getTime();
//                long twentyMinutesInMs = 5 * 60 * 1000;
//
//                if (difference >= twentyMinutesInMs) {
//                    filteredCoins.add(coin);
//                    lastAdded = coin;
//                }
//            }
//        }
//
//        return filteredCoins;
//    }
//
//
//    public List<ShibaInu> allCoins() {
//        return shibaInuRepository.findAll();
//    }
//
//
//    public Optional<ShibaInu> findCoinByCreateDate() {
//        return shibaInuRepository.findFirstByOrderByCreateDateDesc();
//    }
//}