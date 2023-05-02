package com.example.fsm.states;

public class StateUtils {

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
}
