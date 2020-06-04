package io.github.manuelarte.spring.stocksportfolio.controllers.dtos;

import io.github.manuelarte.spring.stocksportfolio.events.PositionOpenedEvent;
import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import org.springframework.data.annotation.Immutable;

@Immutable
@lombok.AllArgsConstructor
@lombok.Value
public class PositionOpenedDto {

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

  public static PositionOpenedDto of(final PositionOpenedEvent event) {
    return new PositionOpenedDto(event.getId(), event.getValue(), event.getTimestamp(), event.getQuantity(),
        event.getAmount(), event.getCurrency());
  }

}
