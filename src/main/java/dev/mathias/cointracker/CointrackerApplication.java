package dev.mathias.cointracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@RestController
public class CointrackerApplication {

	@Autowired
	CoinRepository coinRepository;

	Map<String, Object> allCoins;

	public static void main(String[] args) {
		SpringApplication.run(CointrackerApplication.class, args);

		System.out.println("To the moon");

	}

//	@PostConstruct
//	void init() {
//		writeDataToFile();
////		convertJSONToMap();
//		processCoins("top50coins.json");
//	}
//
//
//	public ResponseEntity<Object> getTop50Coins() {
//		String url = "https://api.coinranking.com/v2/coins?tiers[]=1";
//		String apiKey = "coinranking96ac1d6ad6e7da2f76f2fef1fa08795f31a7b92448e66176";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("X-Api-Key", apiKey);
//
//		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//		RestTemplate restTemplate = new RestTemplate();
//
//		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
//
//		return response;
//	}
//
//	public void writeDataToFile() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		ResponseEntity<Object> response = getTop50Coins();
//
//		try {
//			String jsonData = objectMapper.writeValueAsString(response.getBody());
//			Files.write(Paths.get("top50coins.json"), jsonData.getBytes());
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public void processCoins(String filePath) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			JsonNode rootNode = objectMapper.readTree(new File(filePath));
//			JsonNode coinsNode = rootNode.path("data").path("coins");
//
//			System.out.println(coinsNode);
//
//			for (JsonNode coinNode : coinsNode) {
//				String coinId = coinNode.path("uuid").asText();
//				String symbol = coinNode.path("symbol").asText();
//				String color = coinNode.path("color").asText();
//				String iconUrl = coinNode.path("iconUrl").asText();
//				String rank = coinNode.path("rank").asText();
//				String price = coinNode.path("price").asText();
//
//				Optional<Coin> existingCoin = coinRepository.findCoinByCoinId(coinNode.path("uuid").asText());
//
//				Coin coin = new Coin(coinId, symbol, color, iconUrl, rank, price);
//				coinRepository.save(coin);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}




