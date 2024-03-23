package dev.mathias.cointracker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@EnableScheduling
public class CoinExternalAPIService {
    @Autowired
    CoinRepository coinRepository;

    @PostConstruct
    void init() {
//        saveAllCoinsFromAPI();
        saveSingleCoinFromAPI();

    }

    public ResponseEntity<String> fetchData(String url, String apiKey){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;
    }

    public void saveAllCoinsFromAPI() {

        ResponseEntity<String> response = fetchData("https://api.coinranking.com/v2/coins?tiers[]=1", "coinranking96ac1d6ad6e7da2f76f2fef1fa08795f31a7b92448e66176");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode coinsNode = rootNode.path("data").path("coins");

            for (JsonNode coinNode : coinsNode) {
                String coinId = coinNode.path("uuid").asText();
                String symbol = coinNode.path("symbol").asText();
                String color = coinNode.path("color").asText();
                String iconUrl = coinNode.path("iconUrl").asText();
                String rank = coinNode.path("rank").asText();
                String price = coinNode.path("price").asText();

                Optional<Coin> existingCoin = coinRepository.findCoinByCoinId(coinNode.path("uuid").asText());

                Coin coin = new Coin(coinId, symbol, color, iconUrl, rank, price);
                coinRepository.save(coin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Scheduled(fixedDelay = 5000)
    public void saveSingleCoinFromAPI() {

        ResponseEntity<String> response = fetchData("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD", "");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            double priceUSD = rootNode.path("USD").asDouble();

            Coin coin = new Coin(String.valueOf(priceUSD), new Date());
            coinRepository.save(coin);

            Optional<Coin> coinPrice = coinRepository.findFirstByOrderByCreateDateDesc();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
