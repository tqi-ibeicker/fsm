package com.example.fsm.exeption;

import lombok.Data;

@Data
public class NoTransitionException extends RuntimeException {

    private final String methodName;

    public NoTransitionException(String methodName) {
        this.methodName = methodName;
    }

}
