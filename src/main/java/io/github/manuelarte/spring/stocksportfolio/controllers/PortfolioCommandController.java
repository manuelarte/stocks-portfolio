package io.github.manuelarte.spring.stocksportfolio.controllers;

import io.github.manuelarte.spring.stocksportfolio.commands.OpenPositionCommand;
import io.github.manuelarte.spring.stocksportfolio.controllers.dtos.OpenPositionDto;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/portfolios/me/positions")
@lombok.AllArgsConstructor
public class PortfolioCommandController {

  private final CommandGateway commandGateway;

  @PostMapping
  public CompletableFuture<ResponseEntity<Object>> addOpenPosition(@RequestBody final OpenPositionDto body) {
    final var id = UUID.randomUUID().toString();
    final CompletableFuture<Object> send = commandGateway
        .send(new OpenPositionCommand(id, body.getValue(), Instant.now(), body.getQuantity(), body.getAmount(), body.getCurrency()));
    return send.thenApply(ResponseEntity::ok);
  }

}
