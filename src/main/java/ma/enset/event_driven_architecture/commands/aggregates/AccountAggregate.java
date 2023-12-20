package ma.enset.event_driven_architecture.commands.aggregates;
import ma.enset.event_driven_architecture.common_api.commands.CreateAccountCommand;
import ma.enset.event_driven_architecture.common_api.commands.DebitAccountCommand;
import ma.enset.event_driven_architecture.common_api.enums.AccountStatus;
import ma.enset.event_driven_architecture.common_api.events.AccountActivatedEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountCreatedEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountCrediteEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountDebitedEvent;
import ma.enset.event_driven_architecture.common_api.exceptions.AmountNegativeException;
import ma.enset.event_driven_architecture.common_api.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
    }
    @CommandHandler
    // la  foction de decision
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
      if(createAccountCommand.getInitialBalance()<0) throw new RuntimeException("Impossible");

      //ok
        AggregateLifecycle.apply(new AccountCreatedEvent(
               createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.CREATED));
    }

    @EventSourcingHandler
    public  void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getInitialBalance();
        this.currency=event.getCurrency();
        this.status=AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));

    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event)
    {
        this.status=event.getStatus();
    }


    @CommandHandler
    // la  foction de decision
    public void handle(AccountCrediteEvent command) {
        if(command.getAmount()<0) throw new AmountNegativeException("Amount should not be Negative");
        AggregateLifecycle.apply(new AccountCrediteEvent(command.getId(), command.getAmount(), command.getCurrency()));

    }
    @EventSourcingHandler
    public void on(AccountCrediteEvent event){
        this.balance+=event.getAmount();

    }
 @CommandHandler
    // la  foction de decision
    public void handle(DebitAccountCommand command) {
        if(command.getAmount()<0) throw new AmountNegativeException("Amount should not be Negative");
       if(this.balance<command.getAmount())throw new BalanceNotSufficientException("Balance not suffcinet Exception =>"+balance);
        AggregateLifecycle.apply(new AccountCrediteEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()));

    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();

    }

}
