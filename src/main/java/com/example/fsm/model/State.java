package com.example.fsm.model;

/**
 * Interface da qual todos os estados de negócio devem implementar
 * @param <T>
 * @param <E>
 */
public interface State<T, E extends Enum<E>> {

    E getEnum();

    default FsmTransition<T> onEntry(FsmContext<T> context) {
        return context.stop();
    }
}
