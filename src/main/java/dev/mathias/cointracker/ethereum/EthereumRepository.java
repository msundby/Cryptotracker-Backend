package dev.mathias.cointracker.ethereum;

import dev.mathias.cointracker.bitcoin.Bitcoin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EthereumRepository extends MongoRepository<Ethereum, ObjectId> {
    Optional<Ethereum> findFirstByOrderByCreateDateDesc();
}
