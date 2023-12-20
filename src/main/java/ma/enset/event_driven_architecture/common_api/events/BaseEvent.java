package ma.enset.event_driven_architecture.common_api.events;

import lombok.Getter;

public abstract class BaseEvent <T>{
    @Getter private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
