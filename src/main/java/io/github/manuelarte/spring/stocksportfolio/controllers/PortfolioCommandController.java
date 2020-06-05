package io.github.manuelarte.spring.stocksportfolio.controllers;

import io.github.manuelarte.spring.stocksportfolio.commands.api.ClosePositionCommand;
import io.github.manuelarte.spring.stocksportfolio.commands.api.OpenPositionCommand;
import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.ClosePositionDto;
import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.OpenPositionDto;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/portfolios/{userId}/positions/{symbol}")
@lombok.AllArgsConstructor
public class PortfolioCommandController {

  private final CommandGateway commandGateway;

  @PostMapping
  public CompletableFuture<ResponseEntity<Object>> openPosition(@PathVariable final String userId,
      @PathVariable final String symbol, @RequestBody final OpenPositionDto body) {
    final var id = UUID.randomUUID();
    final CompletableFuture<Object> send = commandGateway
        .send(new OpenPositionCommand(id, userId, symbol, body.getTimestamp(), body.getQuantity(),
            body.getAmount(),
            body.getCurrency()));
    return send.thenApply(ResponseEntity::ok);
  }

  @DeleteMapping
  public CompletableFuture<ResponseEntity<Object>> closePosition(@PathVariable final String userId,
      @PathVariable final String symbol, @RequestBody final ClosePositionDto body) {
    final var id = UUID.randomUUID();
    final CompletableFuture<Object> send = commandGateway
        .send(new ClosePositionCommand(id, userId, symbol, body.getTimestamp(), body.getQuantity(), body.getAmount(),
            body.getCurrency()));
    return send.thenApply(ResponseEntity::ok);
  }

}
