package io.github.manuelarte.spring.stocksportfolio.commands.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class CreateAccountCommand {

  @TargetAggregateIdentifier
  private final String userId;

}
