package dev.mathias.cointracker.bitcoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/bitcoins")
public class BitcoinController {
    @Autowired
    private BitcoinService bitcoinService;

    @GetMapping("/allbitcoins")
    public ResponseEntity<List<Bitcoin>> getAllCoins() {
        List<Bitcoin> coins = bitcoinService.allCoinsFilteredBy5Minutes();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }
}
