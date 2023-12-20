package ma.enset.event_driven_architecture.query.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.event_driven_architecture.common_api.enums.OperationType;
import ma.enset.event_driven_architecture.common_api.events.AccountActivatedEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountCreatedEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountCrediteEvent;
import ma.enset.event_driven_architecture.common_api.events.AccountDebitedEvent;
import ma.enset.event_driven_architecture.common_api.queries.GetAccountByIdQuery;
import ma.enset.event_driven_architecture.common_api.queries.GetAllAccountsQuery;
import ma.enset.event_driven_architecture.query.entities.Account;
import ma.enset.event_driven_architecture.query.entities.Operation;
import ma.enset.event_driven_architecture.query.repositories.AccountRepository;
import ma.enset.event_driven_architecture.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AccountServiceHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event)
    {
        log.info("***********************************");
        log.info("Account created Event Received ");
        Account account= new Account();
        account.setId(event.getId());
        account.setCurrency(event.getCurrency());
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event)
    {
        log.info("***********************************");
        log.info("Account Activeted Event Received ");
        Account account=accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);

    }
    @EventHandler
    public void on(AccountDebitedEvent event)
    {
        log.info("***********************************");
        log.info("Account Activated Event Received ");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date());//à ne pas faire
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()-event.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCrediteEvent event)
    {
        log.info("***********************************");
        log.info("Account credite Event Received ");
        Account account=accountRepository.findById(event.getId()).get();
        Operation operation = new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date());//à ne pas faire
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setBalance(account.getBalance()+event.getAmount());
        accountRepository.save(account);

    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
     return  accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        return  accountRepository.findById(query.getId()).get();
    }
}
