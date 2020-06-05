package io.github.manuelarte.spring.stocksportfolio.commands.impl;

import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@lombok.Builder(toBuilder = true)
class StockValue {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @NotNull
  private String symbol;

  @Positive
  private AtomicInteger quantity;

  public StockValue(final String symbol, final AtomicInteger quantity) {
    this.symbol = symbol;
    this.quantity = quantity;
  }

  public StockValue(final String symbol, final int quantity) {
    this(symbol, new AtomicInteger(0));
  }

  // calculate average price

  public int addQuantity(final int quantity) {
    return this.quantity.addAndGet(quantity);
  }

}
