package com.example.fsm.model;

import com.example.fsm.business.model.Contract;
import com.example.fsm.states.State;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FsmContext {

    @Getter
    private final Contract contract;

    public FsmTransition transitionTo(Class<? extends State> nextState) {
        return FsmTransition.to(nextState, contract);
    }

    public FsmTransition stop() {
        return FsmTransition.stop();
    }
}
