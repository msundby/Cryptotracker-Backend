package dev.mathias.cointracker.solana;

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
@RequestMapping("/api/v1/solana")
public class SolanaController {
    @Autowired
    private SolanaService solanaService;

    @GetMapping("/allsolana")
    public ResponseEntity<List<Solana>> getAllCoins() {
        List<Solana> coins = solanaService.allCoinsFilteredBy5Minutes();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }
}
