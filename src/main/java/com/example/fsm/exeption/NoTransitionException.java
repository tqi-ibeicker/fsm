package com.example.fsm.exeption;

import lombok.Data;

@Data
public class NoTransitionException extends RuntimeException {

    private String currentState;
    private final String methodName;

    public NoTransitionException(String methodName) {
        this.methodName = methodName;
    }

    @Override public String toString() {
        return "NoTransitionException{" +
                "currentState=" + currentState +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
