package ma.enset.event_driven_architecture.query.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.event_driven_architecture.common_api.enums.AccountStatus;

import java.util.Collection;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    private Collection<Operation>operations;
}
