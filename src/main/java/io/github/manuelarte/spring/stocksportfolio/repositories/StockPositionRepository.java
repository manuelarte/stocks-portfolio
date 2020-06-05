package io.github.manuelarte.spring.stocksportfolio.repositories;

import io.github.manuelarte.spring.stocksportfolio.queries.StockPosition;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPositionRepository extends PagingAndSortingRepository<StockPosition, UUID> {

  Optional<StockPosition> findByUserIdAndSymbol(String userId, String symbol);

  Page<StockPosition> findByUserId(Pageable pageable, String userId);

}
