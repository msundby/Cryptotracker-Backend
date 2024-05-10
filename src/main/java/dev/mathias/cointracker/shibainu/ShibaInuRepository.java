package dev.mathias.cointracker.shibainu;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShibaInuRepository extends JpaRepository<ShibaInu, ObjectId> {
    Optional<ShibaInu> findFirstByOrderByCreateDateDesc();
}
