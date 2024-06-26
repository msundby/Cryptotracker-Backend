package dev.mathias.cointracker.bitcoin;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BitcoinRepository extends MongoRepository<Bitcoin, ObjectId> {
    Optional<Bitcoin> findFirstByOrderByCreateDateDesc();
}
