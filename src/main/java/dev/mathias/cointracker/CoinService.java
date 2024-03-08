package dev.mathias.cointracker;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {
    @Autowired
    private CoinRepository coinRepository;
    @Value("${coin.api.url}")
    private String apiUrl;
    public List<CoinDTO> fetchCoinsFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode coinsNode = root.path("data").path("coins");
        List<CoinDTO> coins = objectMapper.convertValue(coinsNode, new TypeReference<List<CoinDTO>>() {});
        return coins;
    }
    public List<Coin> allCoins(){
        return coinRepository.findAll();
    }

    public Optional<Coin> singleCoin(ObjectId id) {
        return coinRepository.findById(id);
    }

    public Optional<Coin> findCoinByCoinId(String coinId) {
        return coinRepository.findMovieByCoinId(coinId);
    }
}
