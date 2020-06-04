package io.github.manuelarte.spring.stocksportfolio.events;

import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@lombok.AllArgsConstructor
@lombok.Value
public class PositionOpenedEvent {

  @TargetAggregateIdentifier
  private final String id;

  @NotNull
  private final String value;

  @NotNull
  @PastOrPresent
  private final Instant timestamp;

  @Positive
  private final int quantity;

  @Positive
  private final BigDecimal amount;

  @NotNull
  private final String currency;

}
