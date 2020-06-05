package io.github.manuelarte.spring.stocksportfolio.controllers;

import io.github.manuelarte.spring.stocksportfolio.commands.api.AccountCreatedEvent;
import io.github.manuelarte.spring.stocksportfolio.commands.api.CreateAccountCommand;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@lombok.AllArgsConstructor
public class UserController {

  private final CommandGateway commandGateway;

  @PostMapping("/{userId}")
  public CompletableFuture<Object> createUser(@PathVariable final String userId) {
    return commandGateway.send(new CreateAccountCommand(userId));
  }

  // do a delete account and need to check that there are no open positions

}
