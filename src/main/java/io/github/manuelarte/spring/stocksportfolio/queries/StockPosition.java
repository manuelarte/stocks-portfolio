package io.github.manuelarte.spring.stocksportfolio.queries;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StockPosition {

  @Id
  @GeneratedValue
  private UUID id;

  @NotNull
  private String userId;

  @NotNull
  private String symbol;

  @PositiveOrZero
  private int quantity;

  public StockPosition(final String userId, final String symbol, final int quantity) {
    this.userId = userId;
    this.symbol = symbol;
    this.quantity = quantity;
  }

}
