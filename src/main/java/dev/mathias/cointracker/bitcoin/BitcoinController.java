package dev.mathias.cointracker.bitcoin;

import dev.mathias.cointracker.Coin;
import dev.mathias.cointracker.CoinService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/bitcoins")
public class BitcoinController {
    @Autowired
    private BitcoinService bitcoinService;

    @GetMapping("/allbitcoins")
    public ResponseEntity<List<Bitcoin>> getAllCoins() {
        List<Bitcoin> coins = bitcoinService.allCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }
}
