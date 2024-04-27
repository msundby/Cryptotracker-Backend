package dev.mathias.cointracker.solana;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolanaRepository extends MongoRepository<Solana, ObjectId> {
    Optional<Solana> findFirstByOrderByCreateDateDesc();
}
