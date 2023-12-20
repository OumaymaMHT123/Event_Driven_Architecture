package ma.enset.event_driven_architecture.query.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.event_driven_architecture.common_api.queries.GetAccountByIdQuery;
import ma.enset.event_driven_architecture.common_api.queries.GetAllAccountsQuery;
import ma.enset.event_driven_architecture.query.entities.Account;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
@Slf4j
public class AccountQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/all/Accounts")
    public List<Account>accountList(){
      List<Account>response=  queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return response;
    }
    @GetMapping("/{id}")
    public Account accountList(@PathVariable String id){
      Account response=  queryGateway.query(new GetAccountByIdQuery(), ResponseTypes.instanceOf(Account.class)).join();
        return response;
    }
}
