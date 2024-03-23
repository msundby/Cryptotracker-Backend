package dev.mathias.cointracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CoinWebSocketHandler extends TextWebSocketHandler {
        private final ObjectMapper objectMapper = new ObjectMapper();
        private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

        Random r = new Random();
        CoinService coinService;

    public CoinWebSocketHandler(CoinService coinService) {
        this.coinService = coinService;
    }

    public Coin getNewestCoinPrice() {
        Optional<Coin> coin = coinService.findCoinByCreateDate();
        if (coin.isPresent()) {
            Coin actualCoin = coin.get();
            return actualCoin;
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        double oldPrice = Double.parseDouble(getNewestCoinPrice().getPrice());

            for (int i=0; i < 10000; i ++){

                Coin coin = getNewestCoinPrice();
                double newestPrice = Double.parseDouble(getNewestCoinPrice().getPrice());

                if (newestPrice > oldPrice){
                    coin.setIncreased(true);
                }
                oldPrice = newestPrice;

                TextMessage message = new TextMessage(objectMapper.writeValueAsString(coin));
                session.sendMessage(message);
                Thread.sleep(5000);
            }
            sessions.add(session);
        }
    }
