//package dev.mathias.cointracker.ethereum;
//
//import dev.mathias.cointracker.bitcoin.Bitcoin;
//import dev.mathias.cointracker.bitcoin.BitcoinService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping("/api/v1/ethereum")
//public class EthereumController {
//    @Autowired
//    private EthereumService ethereumService;
//
//    @GetMapping("/allethereum")
//    public ResponseEntity<List<Ethereum>> getAllCoins() {
//        List<Ethereum> coins = ethereumService.allCoinsFilteredBy5Minutes();
//        return new ResponseEntity<>(coins, HttpStatus.OK);
//    }
//}
