package io.github.manuelarte.spring.stocksportfolio.controllers.dtos;

import io.github.manuelarte.spring.stocksportfolio.commands.api.PositionOpenedEvent;
import io.github.manuelarte.spring.stocksportfolio.queries.StockPosition;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.Immutable;

@Immutable
@lombok.AllArgsConstructor
@lombok.Value
public class StockPositionDto {

  private final UUID id;

  @NotNull
  private final String symbol;

  @PositiveOrZero
  private final int quantity;

  public static StockPositionDto of(final StockPosition stockPosition) {
    return new StockPositionDto(stockPosition.getId(), stockPosition.getSymbol(), stockPosition.getQuantity());
  }

}
