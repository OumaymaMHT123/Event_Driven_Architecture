1. Notre objectif est de créer une application permettant de gérer des comptes bancaires conformément aux motifs CQRS (Command Query Responsibility Segregation) et Event Sourcing en utilisant les frameworks AXON et Spring Boot .
<img width="494" alt="image" src="https://github.com/OumaymaMHT123/Event_Driven_Architecture/assets/95369549/c4f7000d-f333-4611-b01b-992e7592ea72">

  
2. les dépendances utilisees :
- Spring Web
- Spring Data JPA
- lombok
- MySQL Driver

3. Properties

   
![image](https://github.com/OumaymaMHT123/Event_Driven_Architecture/assets/95369549/c6f98a2a-3c20-4136-93b0-359502965d83)

4. Base de donnees


   ![image](https://github.com/OumaymaMHT123/Event_Driven_Architecture/assets/95369549/3e277d3f-1c4d-46fe-8eb7-8a9ab130a2e6)

5. Aggregate


   @Aggregate
public class AccountAggregate {

    @AggregateIdentifier // id is  presented targetAggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;

    private AccountStatus status;
}



La fonction de décision

    @CommandHandler // subscribe sur le bus de commande
    public AccountAggregate(CreateAccountCommand command) {
        if(command.getInitialBalance()<0){
            throw new RuntimeException("Impossible ....");
        }
        // ON
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                command.getCurrency()
        ));
        // Axon va charger de l'ajouter dans la base de donnees.
    }
   

6.  Query

Operation

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
   
    public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

Account


@Entity
@Data @AllArgsConstructor @NoArgsConstructor

    public class Account{
    @Id
    private String id;
    private String currency;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;
}


