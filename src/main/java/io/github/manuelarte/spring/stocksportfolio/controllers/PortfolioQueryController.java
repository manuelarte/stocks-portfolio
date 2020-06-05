package io.github.manuelarte.spring.stocksportfolio.controllers;

import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.StockPositionDto;
import io.github.manuelarte.spring.stocksportfolio.queries.FindStockPositions;
import io.github.manuelarte.spring.stocksportfolio.queries.StockPosition;
import io.github.manuelarte.spring.stocksportfolio.util.PageResponseType;
import java.util.concurrent.CompletableFuture;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/portfolios/{userId}")
@lombok.AllArgsConstructor
public class PortfolioQueryController {

  private final QueryGateway queryGateway;

  @GetMapping("/positions")
  public CompletableFuture<ResponseEntity<Page<StockPositionDto>>> getUserPositions(@PathVariable final String userId,
      @PageableDefault final Pageable pageable) {
    CompletableFuture<Page<StockPosition>> response = queryGateway.query(FindStockPositions.of(pageable, userId),
        new PageResponseType(StockPosition.class));
    return response.thenApply(e -> ResponseEntity.ok(e.map(it -> StockPositionDto.of(it))));
  }

}
