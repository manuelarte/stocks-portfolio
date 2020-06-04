package io.github.manuelarte.spring.stocksportfolio.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.manuelarte.spring.stocksportfolio.model.MyPortfolio;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.concurrent.Immutable;

@Immutable
@JsonDeserialize(builder = MyPortfolioDto.MyPortfolioDtoBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.AllArgsConstructor
@lombok.Builder(toBuilder = true)
public class MyPortfolioDto {

  @lombok.Singular
  private final Map<String, StockValueDto> values;

  @JsonAnyGetter
  public Map<String, StockValueDto> getValues() {
    return values;
  }

  public static MyPortfolioDto from(final MyPortfolio myPortfolio) {
    final Map<String, StockValueDto> collect = myPortfolio.getValues().entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey,
            e -> StockValueDto.of(e.getValue())));
    return new MyPortfolioDto(collect);
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class MyPortfolioDtoBuilder {

  }

}
