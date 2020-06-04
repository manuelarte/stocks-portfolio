package io.github.manuelarte.spring.stocksportfolio.aggregators;

import io.github.manuelarte.spring.stocksportfolio.commands.OpenPositionCommand;
import io.github.manuelarte.spring.stocksportfolio.events.PositionOpenedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PositionAggregate {

  @AggregateIdentifier
  private String positionId;

  @CommandHandler
  public PositionAggregate(final OpenPositionCommand command) {
    AggregateLifecycle.apply(new PositionOpenedEvent(command.getId(), command.getValue(), command.getTimestamp(),
        command.getQuantity(), command.getAmount(), command.getCurrency()));
  }

  protected PositionAggregate() { }

  @EventSourcingHandler
  public void on(final PositionOpenedEvent event) {
    this.positionId = event.getId();
  }

}
