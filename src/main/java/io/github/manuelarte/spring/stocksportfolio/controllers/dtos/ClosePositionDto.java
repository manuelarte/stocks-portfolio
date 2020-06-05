package io.github.manuelarte.spring.stocksportfolio.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.annotation.concurrent.Immutable;

@Immutable
@JsonDeserialize(builder = ClosePositionDto.ClosePositionDtoBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class ClosePositionDto {

  private final int quantity;
  private final ZonedDateTime timestamp;
  private final BigDecimal amount;
  private final String currency;

  @JsonPOJOBuilder(withPrefix = "")
  public static final class ClosePositionDtoBuilder {

  }

}
