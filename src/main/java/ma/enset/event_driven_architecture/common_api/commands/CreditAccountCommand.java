package ma.enset.event_driven_architecture.common_api.commands;

import lombok.Getter;

public class CreditAccountCommand extends BaseCommand<String>{
   @Getter
   private double amount;
   @Getter
   private String currency;
    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
