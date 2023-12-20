package ma.enset.event_driven_architecture.common_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DebitAccountRequestDTO {
    private String accountId;
    private String currency;
    private double amount;
}
