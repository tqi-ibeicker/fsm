package com.example.fsm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Contexto de uma chamada de execução a uma FSM. Tem por propósito manter um payload de negócio a ser chamado nas ações
 * dos estados e facilitar a criação de transições de estado.
 * @param <T>
 */
@RequiredArgsConstructor
public class FsmContext<T> {

    @Getter
    private final T payload;

    public FsmTransition<T> transitionTo(Class<? extends State<T, ?>> nextState) {
        FsmTransition<T> t = new FsmTransition<>();
        t.setTransitionType(TransitionEnum.TRANSITION);
        t.setNextState(nextState);
        t.setPayload(payload);
        return t;
    }

    public FsmTransition<T> stop() {
        FsmTransition<T> t = new FsmTransition<>();
        t.setTransitionType(TransitionEnum.STOP);
        return t;
    }
}
