package io.github.manuelarte.spring.stocksportfolio.commands.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

@lombok.AllArgsConstructor
@lombok.Value
public class AccountCreatedEvent {

  @TargetAggregateIdentifier
  private final String userId;

}
