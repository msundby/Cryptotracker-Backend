package dev.mathias.cointracker.coin;

import dev.mathias.cointracker.coin.Coin;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, ObjectId> {

    Optional<Coin> findFirstByOrderByCreateDateDesc();

    Optional<Coin> findById(Long id);

    Coin findBySymbol(String symbol);
}
