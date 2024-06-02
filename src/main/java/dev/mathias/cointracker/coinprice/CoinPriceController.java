package dev.mathias.cointracker.coinprice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/coinprices")
public class CoinPriceController {

    @Autowired
    private CoinPriceService coinPriceService;

    @GetMapping("/allbitcoinsbyminute")
    public ResponseEntity<List<CoinPrice>> getAllCoins() {
        List<CoinPrice> coins = coinPriceService.allCoinsFilteredBy1Minute(1L);
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }
}



