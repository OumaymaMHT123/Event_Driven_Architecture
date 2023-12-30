1. Notre objectif est de créer une application permettant de gérer des comptes bancaires conformément aux motifs CQRS (Command Query Responsibility Segregation) et Event Sourcing en utilisant les frameworks AXON et Spring Boot , tout en permettant de :

- Ajouter un compte
- Activer un compte
- Créditer un compte
- Débiter un compte
- Consulter un compte
- Consulter les comptes
- Consulter les opérations d'un compte
- Suivre en temps réel l'état d'un compte
3. les dépendances utilisees :
- Spring Web
- Spring Data JPA
- lombok
- MySQL Driver

2. Properties

   
![image](https://github.com/OumaymaMHT123/Event_Driven_Architecture/assets/95369549/c6f98a2a-3c20-4136-93b0-359502965d83)

4. Base de donnees


   ![image](https://github.com/OumaymaMHT123/Event_Driven_Architecture/assets/95369549/3e277d3f-1c4d-46fe-8eb7-8a9ab130a2e6)

Aggregate

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
   

  
