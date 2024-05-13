package dev.mathias.cointracker.coin;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/coins")
public class CoinController {
    @Autowired
    private CoinService coinService;

    @GetMapping("/allcoins")
    public ResponseEntity<List<Coin>> getAllCoins() {
        List<Coin> coins = coinService.allCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/object/{id}")
    public ResponseEntity<Optional<Coin>> getSingleCoinByObjectId(@PathVariable Long id){
        return new ResponseEntity<Optional<Coin>>(coinService.singleCoin(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Coin> createCoin(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Coin>(coinService.createCoin(payload.get("color"), payload.get("symbol"), payload.get("iconUrl"), payload.get("rank"), payload.get("marketcap")), HttpStatus.CREATED);
    }


}
