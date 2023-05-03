package com.example.fsm.model;

import lombok.Data;

@Data
public class NoTransition<T, E extends Enum<E>> {

    private E currentState;
    private String methodName;
    private T payload;

    public NoTransition(E currentState, String methodName, T payload) {
        this.currentState = currentState;
        this.methodName = methodName;
        this.payload = payload;
    }
}
