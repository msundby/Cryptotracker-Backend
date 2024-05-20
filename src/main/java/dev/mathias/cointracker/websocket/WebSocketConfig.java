package dev.mathias.cointracker.websocket;
import dev.mathias.cointracker.coin.CoinService;
import dev.mathias.cointracker.coinprice.CoinPriceService;
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
    CoinPriceService coinPriceService;

    @Autowired
    CoinService coinService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(coinWebSocketHandler(), "/price").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler coinWebSocketHandler() {
        return WebSocketHandler.getInstance(coinPriceService);
    }
}
