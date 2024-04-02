package dev.mathias.cointracker;

import dev.mathias.cointracker.bitcoin.BitcoinService;
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

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(coinWebSocketHandler(), "/price").setAllowedOrigins("*");
    }

    @Bean
    public org.springframework.web.socket.WebSocketHandler coinWebSocketHandler() {
        return new WebSocketHandler(bitcoinService);
    }
}
