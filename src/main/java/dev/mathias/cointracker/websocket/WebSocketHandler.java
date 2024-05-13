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

        private final ObjectMapper objectMapper = new ObjectMapper();
        private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
        private final CoinPriceService coinPriceService;


    public WebSocketHandler(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            while (session.isOpen()) {

                CoinsDTO coinsDTO = new CoinsDTO();

                coinsDTO.setBitcoinPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("BTC")));
                coinsDTO.setEthereumPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("ETH")));
                coinsDTO.setShibaInuPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("SHIB")));
                coinsDTO.setSolanaPrice(coinPriceService.getNewestCoinPrice(coinPriceService.findCoinIdBySymbol("SOL")));

                TextMessage message = new TextMessage(objectMapper.writeValueAsString(coinsDTO));
                session.sendMessage(message);

            sessions.add(session);
        }

    }
}
