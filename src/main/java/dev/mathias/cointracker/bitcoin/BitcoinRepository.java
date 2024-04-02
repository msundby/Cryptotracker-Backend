package dev.mathias.cointracker.bitcoin;

import dev.mathias.cointracker.Coin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BitcoinRepository extends MongoRepository<Bitcoin, ObjectId> {
    Optional<Bitcoin> findFirstByOrderByCreateDateDesc();
}
