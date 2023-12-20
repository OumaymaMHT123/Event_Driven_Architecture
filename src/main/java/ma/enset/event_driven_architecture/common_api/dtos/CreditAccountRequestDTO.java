package ma.enset.event_driven_architecture.common_api.dtos;

import lombok.Data;
import lombok.Getter;

@Data
public class CreditAccountRequestDTO {

    @Getter
    private String accountId;
    @Getter
    private double amount;
    @Getter
    private String currency;
}
