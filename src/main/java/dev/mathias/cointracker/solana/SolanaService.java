//package dev.mathias.cointracker.solana;
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
//public class SolanaService {
//    @Autowired
//    private SolanaRepository solanaRepository;
//
//    public void createCoin(String price, Date date) {
//        Solana shibaInu = new Solana(price, date);
//        solanaRepository.save(shibaInu);
//    }
//
//    public List<Solana> allCoinsFilteredBy5Minutes() {
//        List<Solana> allCoins = this.allCoins();
//        List<Solana> filteredCoins = new ArrayList<>();
//
//        if (!allCoins.isEmpty()) {
//            Solana lastAdded = allCoins.get(0);
//            filteredCoins.add(lastAdded);
//
//            for (Solana coin : allCoins) {
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
//    public List<Solana> allCoins() {
//        return solanaRepository.findAll();
//    }
//
//
//    public Optional<Solana> findCoinByCreateDate() {
//        return solanaRepository.findFirstByOrderByCreateDateDesc();
//    }
//}