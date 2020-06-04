package io.github.manuelarte.spring.stocksportfolio.controllers;

import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.MyPortfolioDto;
import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.PositionOpenedDto;
import io.github.manuelarte.spring.stocksportfolio.events.PositionOpenedEvent;
import io.github.manuelarte.spring.stocksportfolio.queries.FindMyPortfolio;
import io.github.manuelarte.spring.stocksportfolio.queries.FindOpenPositions;
import io.github.manuelarte.spring.stocksportfolio.model.MyPortfolio;
import java.util.concurrent.CompletableFuture;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/portfolios/me")
@lombok.AllArgsConstructor
public class PortfolioQueryController {

  private final QueryGateway queryGateway;

  @GetMapping
  public CompletableFuture<ResponseEntity<MyPortfolioDto>> getMyPortfolio() {
    final var myPortfolio = queryGateway.query(new FindMyPortfolio(), MyPortfolio.class);
    return myPortfolio.thenApply(mP -> ResponseEntity.ok(MyPortfolioDto.from(mP)));
  }

  @GetMapping("/positions")
  public CompletableFuture<ResponseEntity<Page<PositionOpenedDto>>> getMyPositions(@PageableDefault(4) final Pageable pageable) {
    final var response = queryGateway.query(new FindOpenPositions(pageable), Page.class);
    return response.thenApply(e -> ResponseEntity.ok(e.map(it -> PositionOpenedDto.of((PositionOpenedEvent) it))));
  }

}
