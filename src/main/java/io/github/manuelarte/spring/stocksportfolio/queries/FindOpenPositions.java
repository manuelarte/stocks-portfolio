package io.github.manuelarte.spring.stocksportfolio.queries;

import org.springframework.data.domain.Pageable;

@lombok.AllArgsConstructor
@lombok.Value
public class FindOpenPositions {

  private final Pageable pageable;

}
