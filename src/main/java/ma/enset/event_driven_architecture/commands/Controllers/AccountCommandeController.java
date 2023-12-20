package ma.enset.event_driven_architecture.commands.Controllers;

import lombok.AllArgsConstructor;
import ma.enset.event_driven_architecture.common_api.commands.CreateAccountCommand;
import ma.enset.event_driven_architecture.common_api.commands.DebitAccountCommand;
import ma.enset.event_driven_architecture.common_api.dtos.CreateAccountRequestDTO;
import ma.enset.event_driven_architecture.common_api.dtos.CreditAccountRequestDTO;
import ma.enset.event_driven_architecture.common_api.dtos.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/account")
@AllArgsConstructor
public class AccountCommandeController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request){
        CompletableFuture<String> commandResponse =commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getIntialBalance(),
                request.getCurrency()
        ));
        return commandResponse;
    }
    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> commandResponse =commandGateway.send(new CreateAccountCommand(request.getAccountId(), request.getAmount(), request.getCurrency()));
        return commandResponse;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
ResponseEntity<String> entity=new ResponseEntity<>(exception.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
return entity;
    }
    @GetMapping(path = "/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
           return eventStore.readEvents(accountId).asStream();

}
    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request){
        CompletableFuture<String> commandResponse =commandGateway.send(new DebitAccountCommand
                (      request.getAccountId(),
                        request.getAmount(),
                        request.getCurrency()));
        return commandResponse;
    }


}
