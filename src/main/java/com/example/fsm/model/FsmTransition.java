package com.example.fsm.model;

import lombok.Data;

@Data
public class FsmTransition<T> {

    private TransitionEnum transitionType;

    private Class<? extends State<T, ?>> nextState;

    private T payload;

    public FsmTransition() {}

}
