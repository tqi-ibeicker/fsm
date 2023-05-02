package com.example.fsm.exeption;

import com.example.fsm.model.FsmContext;

public class NoTransitionException extends RuntimeException {

    public NoTransitionException(FsmContext context) {
    }
}
