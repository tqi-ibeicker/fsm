package com.example.fsm.model;

import com.example.fsm.business.model.Contract;
import com.example.fsm.states.State;
import lombok.Data;

@Data
public class FsmTransition {

    private TransitionEnum transitionType;

    private Class<? extends State> nextState;

    private Contract contract;

    public FsmTransition() {}

    public static FsmTransition to(Class<? extends State> nextState, Contract contract) {
        FsmTransition t = new FsmTransition();
        t.setTransitionType(TransitionEnum.TRANSITION);
        t.setNextState(nextState);
        t.setContract(contract);
        return t;
    }

    public static FsmTransition stop() {
        FsmTransition t = new FsmTransition();
        t.setTransitionType(TransitionEnum.STOP);
        return t;
    }
}
