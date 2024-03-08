package dev.mathias.cointracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {
    @Autowired
    private CoinRepository coinRepository;
    @Value("${coin.api.url}")
    private String apiUrl;
    public List<CoinDTO> fetchCoinsFromApi() throws JsonProcessingException {
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
