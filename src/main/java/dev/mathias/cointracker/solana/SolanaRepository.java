package dev.mathias.cointracker.solana;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolanaRepository extends JpaRepository<Solana, ObjectId> {
    Optional<Solana> findFirstByOrderByCreateDateDesc();
}
