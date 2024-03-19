package dev.mathias.cointracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TradeWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    Random r = new Random();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        float oldPrice = 0.0f;

        //Publishing new stock prices every one second for 100 times
        for (int i=0; i < 100; i ++){
            //Calculating Random stock price between 12$ to 13$
            float stockPrice = 12 + r.nextFloat() * (13 - 12);
            float roundedPrice = (float) (Math.round(stockPrice * 100.0) / 100.0);

            //Creating a Stock Object
            Coin coin = new Coin("BTC","https://cdn.cdnlogo.com/logos/a/77/amazon-dark.svg",
                    String.valueOf(roundedPrice),true);

            //Finding whether the stock price increased or decreased
            if (roundedPrice > oldPrice){
                coin.setIncreased(true);
            }
            oldPrice = roundedPrice;

            //Sending StockPrice
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(coin));
            session.sendMessage(message);
            Thread.sleep(1000);
        }
        sessions.add(session);
    }
}

