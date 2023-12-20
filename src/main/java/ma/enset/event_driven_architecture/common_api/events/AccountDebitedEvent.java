package ma.enset.event_driven_architecture.common_api.events;

import lombok.Getter;

public class AccountDebitedEvent extends BaseEvent<String>{

@Getter
  private double amount;
 @Getter private String Currency;

    public AccountDebitedEvent(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
       this.Currency = currency;
    }
}
