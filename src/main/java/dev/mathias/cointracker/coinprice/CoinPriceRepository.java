package dev.mathias.cointracker.coinprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPrice, Long> {

    List<CoinPrice> findByCoinId(Long coinId);

    Long findByCoinSymbol(String symbol);

    Optional<CoinPrice> findFirstByCoinIdEqualsOrderByCreateDateDesc(Long coinId);
}
