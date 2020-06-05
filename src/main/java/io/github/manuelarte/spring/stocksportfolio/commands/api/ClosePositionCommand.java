package io.github.manuelarte.spring.stocksportfolio.commands.api;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class ClosePositionCommand {

  @NotNull
  private final UUID id;

  @TargetAggregateIdentifier
  private final String userId;

  @NotNull
  private final String symbol;

  @NotNull
  @PastOrPresent
  private final ZonedDateTime timestamp;

  @Positive
  private final int quantity;

  @Positive
  private final BigDecimal amount;

  @NotNull
  private final String currency;

}
