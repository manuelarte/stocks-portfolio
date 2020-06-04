package io.github.manuelarte.spring.stocksportfolio.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class MyPortfolio {

  @lombok.Singular
  private final Map<String, StockValue> values = new HashMap<>();

  public StockValue addValue(final String value, final int quantity) {
    if (!values.containsKey(value)) {
      values.computeIfAbsent(value, k -> new StockValue(value, new AtomicInteger(quantity)));
    } else {
      values.get(value).addQuantity(quantity);
    }
    return values.get(value);
  }

}
