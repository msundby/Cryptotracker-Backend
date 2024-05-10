package dev.mathias.cointracker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mathias.cointracker.bitcoin.BitcoinService;
import dev.mathias.cointracker.coin.Coin;
import dev.mathias.cointracker.coin.CoinRepository;
import dev.mathias.cointracker.coin.CoinService;
import dev.mathias.cointracker.ethereum.EthereumService;
import dev.mathias.cointracker.shibainu.ShibaInuService;
import dev.mathias.cointracker.solana.SolanaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Service
@EnableScheduling
public class CoinExternalAPIService {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    CoinService coinService;
    @Autowired
    BitcoinService bitcoinService;
    @Autowired
    EthereumService ethereumService;
    @Autowired
    ShibaInuService shibaInuService;
    @Autowired
    SolanaService solanaService;


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
                String marketCap = coinNode.path("marketCap").asText();

                Optional<Coin> existingCoin = coinRepository.findCoinByCoinId(coinNode.path("uuid").asText());

                Coin coin = new Coin(coinId, symbol, color, iconUrl, rank, price, marketCap);
                coinRepository.save(coin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Scheduled(fixedDelay = 5000)
    public void saveSingleCoinFromAPI() {

        ResponseEntity<String> responseBitcoin = fetchData("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD", "");
        ResponseEntity<String> responseEthereum = fetchData("https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD", "");
        ResponseEntity<String> responseShibaInu = fetchData("https://min-api.cryptocompare.com/data/price?fsym=SHIB&tsyms=USD", "");
        ResponseEntity<String> responseSolana = fetchData("https://min-api.cryptocompare.com/data/price?fsym=SOL&tsyms=USD", "");

        ObjectMapper objectMapperBitcoin = new ObjectMapper();
        ObjectMapper objectMapperEthereum = new ObjectMapper();
        ObjectMapper objectMapperShibaInu = new ObjectMapper();
        ObjectMapper objectMapperSolana = new ObjectMapper();

        try {
            JsonNode rootNodeBitcoin = objectMapperBitcoin.readTree(responseBitcoin.getBody());
            JsonNode rootNodeEthereum = objectMapperEthereum.readTree(responseEthereum.getBody());
            JsonNode rootNodeShibaInu = objectMapperShibaInu.readTree(responseShibaInu.getBody());
            JsonNode rootNodeSolana = objectMapperSolana.readTree(responseSolana.getBody());

            double priceBitcoin = rootNodeBitcoin.path("USD").asDouble();
            double priceEthereum = rootNodeEthereum.path("USD").asDouble();
            double priceShibaInu = rootNodeShibaInu.path("USD").asDouble();
            double priceSolana = rootNodeSolana.path("USD").asDouble();

            bitcoinService.createCoin(String.valueOf(priceBitcoin), new Date());
            ethereumService.createCoin(String.valueOf(priceEthereum), new Date());
            shibaInuService.createCoin(String.valueOf(priceShibaInu), new Date());
            solanaService.createCoin(String.valueOf(priceSolana), new Date());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
