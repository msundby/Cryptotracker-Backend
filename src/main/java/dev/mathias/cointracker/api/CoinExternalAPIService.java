package dev.mathias.cointracker.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mathias.cointracker.coin.Coin;
import dev.mathias.cointracker.coin.CoinRepository;
import dev.mathias.cointracker.coin.CoinService;
import dev.mathias.cointracker.coinprice.CoinPriceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Date;

@Service
@EnableScheduling
public class CoinExternalAPIService {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    CoinService coinService;
    @Autowired
    CoinPriceService coinPriceService;

    @Value("${cryptocompare.api.key}")
    private String cryptoCompareApiKey;

    @Value("${coinranking.api.key}")
    private String coinRankingApiKey;


    @PostConstruct
    void init() {
          saveAllCoinsFromAPI();
          saveCoinPricesFromAPI();
    }


    public ResponseEntity<String> fetchData(String url, String apiKey){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Apikey " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;
    }



    public void saveAllCoinsFromAPI() {

        ResponseEntity<String> response = fetchData("https://api.coinranking.com/v2/coins?tiers[]=1", coinRankingApiKey);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode coinsNode = rootNode.path("data").path("coins");

            for (JsonNode coinNode : coinsNode) {
                String symbol = coinNode.path("symbol").asText();
                String color = coinNode.path("color").asText();
                String iconUrl = coinNode.path("iconUrl").asText();
                String rank = coinNode.path("rank").asText();
                String marketCap = coinNode.path("marketCap").asText();

                Coin coin = new Coin(symbol, color, iconUrl, rank, marketCap);
                coinRepository.save(coin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Scheduled(fixedDelay = 5000)
        public void saveCoinPricesFromAPI() {

          extractPriceFromJsonAndSaveToDatabase("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD",cryptoCompareApiKey,"BTC");
          extractPriceFromJsonAndSaveToDatabase("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD",cryptoCompareApiKey,"ETH");
          extractPriceFromJsonAndSaveToDatabase("https://min-api.cryptocompare.com/data/price?fsym=SHIB&tsyms=USD",cryptoCompareApiKey,"SHIB");
          extractPriceFromJsonAndSaveToDatabase("https://min-api.cryptocompare.com/data/price?fsym=SOL&tsyms=USD",cryptoCompareApiKey,"SOL");
    }



    public void extractPriceFromJsonAndSaveToDatabase(String apiUrl, String apiKey, String symbol){

        ResponseEntity<String> responseCoin = fetchData(apiUrl, apiKey);
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode rootNode = objectMapper.readTree(responseCoin.getBody());
            double price = rootNode.path("USD").asDouble();
            coinPriceService.createCoinPrice(price, new Date(), false, symbol);

        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e );
        }

    }
}
