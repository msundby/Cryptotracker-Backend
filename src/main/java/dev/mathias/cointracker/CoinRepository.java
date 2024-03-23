package dev.mathias.cointracker;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends MongoRepository<Coin, ObjectId> {
    Optional<Coin> findCoinByCoinId(String coinId); //Fordi der ikke er en metode der direkte finder det her ID laver vi vores egen her
    Optional<Coin> findFirstByOrderByCreateDateDesc();

}
