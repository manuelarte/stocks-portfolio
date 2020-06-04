package io.github.manuelarte.spring.stocksportfolio.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.manuelarte.spring.stocksportfolio.model.StockValue;
import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Immutable
@JsonDeserialize(builder = StockValueDto.StockValueDtoBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class StockValueDto {

  @NotNull
  private final String value;
  @Positive
  private final int quantity;

  public static StockValueDto of(final StockValue stockValue) {
    return new StockValueDto(stockValue.getValue(), stockValue.getQuantity().get());
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class StockValueDtoBuilder { }

}
