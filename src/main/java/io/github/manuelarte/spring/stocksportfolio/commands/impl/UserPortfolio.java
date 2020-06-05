package io.github.manuelarte.spring.stocksportfolio.commands.impl;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import io.github.manuelarte.spring.stocksportfolio.commands.api.AccountCreatedEvent;
import io.github.manuelarte.spring.stocksportfolio.commands.api.ClosePositionCommand;
import io.github.manuelarte.spring.stocksportfolio.commands.api.CreateAccountCommand;
import io.github.manuelarte.spring.stocksportfolio.commands.api.OpenPositionCommand;
import io.github.manuelarte.spring.stocksportfolio.commands.api.PositionClosedEvent;
import io.github.manuelarte.spring.stocksportfolio.commands.api.PositionOpenedEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

@Aggregate
@lombok.NoArgsConstructor
@lombok.extern.slf4j.Slf4j
@Profile("command")
public class UserPortfolio {

  @AggregateIdentifier
  private String userId;
  private Map<String, StockValue> values = new ConcurrentHashMap<>();

  @CommandHandler
  public UserPortfolio(final CreateAccountCommand cmd) {
    log.debug("handling {}", cmd);
    apply(new AccountCreatedEvent(cmd.getUserId()));
  }

  @CommandHandler
  public void handle(final OpenPositionCommand cmd) {
    log.debug("handling {}", cmd);
    apply(new PositionOpenedEvent(cmd.getId(), cmd.getUserId(), cmd.getSymbol(), cmd.getTimestamp(),
        cmd.getQuantity(), cmd.getAmount(), cmd.getCurrency()));
  }

  @CommandHandler
  public void handle(final ClosePositionCommand cmd) {
    log.debug("handling {}", cmd);
    apply(new PositionClosedEvent(cmd.getId(), cmd.getUserId(), cmd.getSymbol(), cmd.getTimestamp(),
        cmd.getQuantity(), cmd.getAmount(), cmd.getCurrency()));
  }

  @EventSourcingHandler
  public void on(final AccountCreatedEvent evt) {
    log.debug("applying {}", evt);
    this.userId = evt.getUserId();
  }

  @EventSourcingHandler
  public void on(final PositionOpenedEvent evt) {
    log.debug("applying {}", evt);
    this.userId = evt.getUserId();
    getStockValue(evt.getSymbol()).addQuantity(evt.getQuantity());
  }

  @EventSourcingHandler
  public void on(final PositionClosedEvent evt) {
    log.debug("applying {}", evt);
    this.userId = evt.getUserId();
    if (!this.values.containsKey(evt.getSymbol()))
      throw new IllegalArgumentException("Symbol " + evt.getSymbol() + " not found in user's portfolio");
    if (getStockValue(evt.getSymbol()).getQuantity().get() < evt.getQuantity())
      throw new IllegalArgumentException("Symbol " + evt.getSymbol() + " does not contain enough stocks to sell");
    getStockValue(evt.getSymbol()).addQuantity(-evt.getQuantity());
  }

  private StockValue getStockValue(final String symbol) {
    if (!values.containsKey(symbol)) {
      values.putIfAbsent(symbol, new StockValue(symbol, 0));
    }
    return values.get(symbol);
  }

}
