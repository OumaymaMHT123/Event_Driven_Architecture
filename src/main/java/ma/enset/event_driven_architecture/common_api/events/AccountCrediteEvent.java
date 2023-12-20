package ma.enset.event_driven_architecture.common_api.events;

import lombok.Getter;
import ma.enset.event_driven_architecture.common_api.enums.AccountStatus;

public class AccountCrediteEvent extends BaseEvent<String>{

@Getter
  private double amount;
 @Getter private String Currency;

    public AccountCrediteEvent(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
       this.Currency = currency;
    }
}
