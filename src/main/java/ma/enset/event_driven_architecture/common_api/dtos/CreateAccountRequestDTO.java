package ma.enset.event_driven_architecture.common_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateAccountRequestDTO {
    private double intialBalance;
    private String currency;
}
