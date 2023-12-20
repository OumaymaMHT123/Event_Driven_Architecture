package ma.enset.event_driven_architecture.common_api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetAccountByIdQuery {
    private String id;


}
