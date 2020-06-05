package io.github.manuelarte.spring.stocksportfolio.queries;

import io.github.manuelarte.spring.stocksportfolio.commands.api.PositionClosedEvent;
import io.github.manuelarte.spring.stocksportfolio.commands.api.PositionOpenedEvent;
import io.github.manuelarte.spring.stocksportfolio.repositories.StockPositionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
@Profile("query")
public class StockPositionProjector {

  private final StockPositionRepository stockPositionRepository;

  @EventHandler
  public void on(final PositionOpenedEvent evt) {
    log.debug("projecting {}", evt);
    final var stock = stockPositionRepository
        .findByUserIdAndSymbol(evt.getUserId(), evt.getSymbol()).orElse(new StockPosition(evt.getUserId(),
            evt.getSymbol(), evt.getQuantity()));
    stock.setQuantity(stock.getQuantity() + evt.getQuantity());
    this.stockPositionRepository.save(stock);
  }

  @EventHandler
  public void on(final PositionClosedEvent evt) {
    log.debug("projecting {}", evt);
    final var stock = stockPositionRepository
        .findByUserIdAndSymbol(evt.getUserId(), evt.getSymbol()).orElseThrow(() -> new IllegalArgumentException("Cant"
            + " remove stocks from a non existing position"));
    stock.setQuantity(stock.getQuantity() - evt.getQuantity());
    this.stockPositionRepository.save(stock);
  }

  @QueryHandler
  public Page<StockPosition> getAllByQuery(final FindStockPositions query) {
    return this.stockPositionRepository.findByUserId(query.getPageable(), query.getUserId());
  }

}
