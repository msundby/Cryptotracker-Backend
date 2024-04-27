package dev.mathias.cointracker.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mathias.cointracker.bitcoin.Bitcoin;
import dev.mathias.cointracker.bitcoin.BitcoinService;
import dev.mathias.cointracker.ethereum.Ethereum;
import dev.mathias.cointracker.ethereum.EthereumService;
import dev.mathias.cointracker.shibainu.ShibaInu;
import dev.mathias.cointracker.shibainu.ShibaInuService;
import dev.mathias.cointracker.solana.Solana;
import dev.mathias.cointracker.solana.SolanaService;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSocketHandler extends TextWebSocketHandler {
        private final ObjectMapper objectMapper = new ObjectMapper();
        private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
        private final BitcoinService bitcoinService;

        private final EthereumService ethereumService;
        private final ShibaInuService shibaInuService;

        private final SolanaService solanaService;

    public WebSocketHandler(BitcoinService bitcoinService, EthereumService ethereumService, ShibaInuService shibaInuService, SolanaService solanaService) {
        this.bitcoinService = bitcoinService;
        this.ethereumService = ethereumService;
        this.shibaInuService = shibaInuService;
        this.solanaService = solanaService;
    }

    public Double getNewestBitcoinPrice() {
        Optional<Bitcoin> coin = bitcoinService.findCoinByCreateDate();
        if (coin.isPresent()) {
            Bitcoin actualCoin = coin.get();
            return Double.parseDouble(actualCoin.getPrice());
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    public Double getNewestEthereumPrice() {
        Optional<Ethereum> coin = ethereumService.findCoinByCreateDate();
        if (coin.isPresent()) {
            Ethereum actualCoin = coin.get();
            return Double.parseDouble(actualCoin.getPrice());
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    public Double getNewestShibaInuPrice() {
        Optional<ShibaInu> coin = shibaInuService.findCoinByCreateDate();
        if (coin.isPresent()) {
            ShibaInu actualCoin = coin.get();
            return Double.parseDouble(actualCoin.getPrice());
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    public Double getNewestSolanaPrice() {
        Optional<Solana> coin = solanaService.findCoinByCreateDate();
        if (coin.isPresent()) {
            Solana actualCoin = coin.get();
            return Double.parseDouble(actualCoin.getPrice());
        } else {
            System.out.println("No coin found!");
        }
        return null;
    }

    @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            while (true) {
                CombinedPrice combinedPrice = new CombinedPrice();
                combinedPrice.setBitcoinPrice(getNewestBitcoinPrice());
                combinedPrice.setEthereumPrice(getNewestEthereumPrice());
                combinedPrice.setShibaInuPrice(getNewestShibaInuPrice());
                combinedPrice.setSolanaPrice(getNewestSolanaPrice());
                TextMessage message = new TextMessage(objectMapper.writeValueAsString(combinedPrice));
                session.sendMessage(message);
                Thread.sleep(5000);

            sessions.add(session);
        }

    }
}
