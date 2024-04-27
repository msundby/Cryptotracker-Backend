package dev.mathias.cointracker.websocket;

import dev.mathias.cointracker.bitcoin.BitcoinService;
import dev.mathias.cointracker.ethereum.EthereumService;
import dev.mathias.cointracker.shibainu.ShibaInuService;
import dev.mathias.cointracker.solana.Solana;
import dev.mathias.cointracker.solana.SolanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    BitcoinService bitcoinService;

    @Autowired
    EthereumService ethereumService;

    @Autowired
    ShibaInuService shibaInuService;

    @Autowired
    SolanaService solanaService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(coinWebSocketHandler(), "/price").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler coinWebSocketHandler() {
        return new WebSocketHandler(bitcoinService, ethereumService, shibaInuService, solanaService);
    }
}
