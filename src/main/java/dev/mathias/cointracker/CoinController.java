package dev.mathias.cointracker;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coins")
public class CoinController {
    @Autowired
    private CoinService coinService;
    @GetMapping
    public ResponseEntity<List<Coin>> getAllMovies(){
        return new ResponseEntity<List<Coin>>(coinService.allCoins(),HttpStatus.OK);
    }

    @GetMapping("/object/{id}")
    public ResponseEntity<Optional<Coin>> getSingleMovieByObjectId(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Coin>>(coinService.singleCoin(id),HttpStatus.OK);
    }

    @GetMapping("/ownid/{coinId}")
    public ResponseEntity<Optional<Coin>> getSingleMovieByOwnId(@PathVariable String coinId){
        return new ResponseEntity<Optional<Coin>>(coinService.findCoinByCoinId(coinId),HttpStatus.OK);
    }
}
