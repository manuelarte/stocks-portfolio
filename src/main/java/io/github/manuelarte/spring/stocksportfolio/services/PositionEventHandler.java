package io.github.manuelarte.spring.stocksportfolio.services;

import io.github.manuelarte.spring.stocksportfolio.events.PositionOpenedEvent;
import io.github.manuelarte.spring.stocksportfolio.queries.FindMyPortfolio;
import io.github.manuelarte.spring.stocksportfolio.queries.FindOpenPositions;
import io.github.manuelarte.spring.stocksportfolio.model.MyPortfolio;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class PositionEventHandler {

  private final MyPortfolio myPortfolio = new MyPortfolio();
  private final List<PositionOpenedEvent> positionOpenedEventList = new CopyOnWriteArrayList<>();

  @EventHandler
  public void on(final PositionOpenedEvent event) {
    final var value = event.getValue();
    myPortfolio.addValue(value, event.getQuantity());
    positionOpenedEventList.add(event);
  }

  @QueryHandler
  public MyPortfolio handle(final FindMyPortfolio query) {
    return myPortfolio;
  }

  @QueryHandler
  // don't parametrized output, if not axos don't know how to find the handler
  public Page handle(final FindOpenPositions query) {
    final var pageable = query.getPageable();
    final var content = positionOpenedEventList.stream().collect(Collectors.toList());
    final int startIndex = pageable.getPageNumber()*pageable.getPageSize();
    final int endIndex = Math.min((1 + pageable.getPageNumber()) * pageable.getPageSize(), content.size());
    return new PageImpl<>(content.subList(startIndex, endIndex), pageable, content.size());
  }

}
