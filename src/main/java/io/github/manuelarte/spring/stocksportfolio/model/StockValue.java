package io.github.manuelarte.spring.stocksportfolio.model;

import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class StockValue {

  @NotNull
  private final String value;
  @Positive
  private final AtomicInteger quantity;

  public int addQuantity(final int quantity) {
    return this.quantity.addAndGet(quantity);
  }

}
