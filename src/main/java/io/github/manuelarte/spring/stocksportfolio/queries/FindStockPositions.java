package io.github.manuelarte.spring.stocksportfolio.queries;

import org.springframework.data.domain.Pageable;

@lombok.AllArgsConstructor(staticName = "of")
@lombok.Value
public class FindStockPositions {

  private final Pageable pageable;
  private final String userId;

}
