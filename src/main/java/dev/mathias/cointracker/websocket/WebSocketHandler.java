package dev.mathias.cointracker.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mathias.cointracker.coinprice.CoinPriceService;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSocketHandler extends TextWebSocketHandler {

        private static WebSocketHandler instance = null;
        private final ObjectMapper objectMapper = new ObjectMapper();
        private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
        private final CoinPriceService coinPriceService;


    private WebSocketHandler(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

    public static WebSocketHandler getInstance(CoinPriceService coinPriceService){
        if (instance == null){
            instance = new WebSocketHandler(coinPriceService);
        }
        return instance;
    }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            while (session.isOpen()) {

                CoinsDTO coinsDTO = new CoinsDTO.Builder()
                        .setBitcoinPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("BTC")))
                        .setEthereumPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("ETH")))
                        .setShibaInuPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("SHIB")))
                        .setSolanaPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("SOL")))
                        .build();

                TextMessage message = new TextMessage(objectMapper.writeValueAsString(coinsDTO));
                session.sendMessage(message);

            sessions.add(session);
        }

    }
}
