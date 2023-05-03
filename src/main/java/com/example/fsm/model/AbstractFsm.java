package com.example.fsm.model;

import com.example.fsm.exeption.NoTransitionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFsm<S extends State<T, E>, T, E extends Enum<E>> {

    private final Map<E, S> contractStateEnumMap;
    private final Map<Class<?>, S> contractStateClassMap;

    public AbstractFsm(List<S> contractStateList) {
        contractStateEnumMap = new HashMap<>();
        contractStateClassMap = new HashMap<>();
        contractStateList.forEach(i -> {
            contractStateEnumMap.put(i.getEnum(), i);
            contractStateClassMap.put(i.getClass(), i);
        });
    }

    protected abstract void handleNoTransition(NoTransition<T, E> noTransition);

    /**
     * M
     * @param contract
     * @param contractStateEnum
     * @return
     */
    public WithContext of(T contract, E contractStateEnum) {
        return new WithContext(new FsmContext<>(contract), contractStateEnum);
    }

    /**
     * Classe para armazenar o contexto e estado atual da FSM
     */
    public class WithContext {

        private final FsmContext<T> fsmContext;

        private final E contractStateEnum;

        public WithContext(FsmContext<T> fsmContext, E contractStateEnum) {
            this.fsmContext = fsmContext;
            this.contractStateEnum = contractStateEnum;
        }

        public E execute(Execution<T, S> execution) {
            S contractState = contractStateEnumMap.get(contractStateEnum);
            FsmTransition<T> fsmTransition;
            try {
                fsmTransition = execution.execute(contractState, fsmContext);
            } catch (NoTransitionException e) {
                NoTransition<T, E> noTransition = new NoTransition<>(contractStateEnum, e.getMethodName(), fsmContext.getPayload());
                handleNoTransition(noTransition);
                return contractStateEnum;
            } catch (Exception e) {
                return contractStateEnum;
            }

            S nextState = contractState;
            while (fsmTransition.getTransitionType() != TransitionEnum.STOP) {
                FsmContext<T> newContext = new FsmContext<>(fsmTransition.getPayload());
                nextState = contractStateClassMap.get(fsmTransition.getNextState());
                fsmTransition = nextState.onEntry(newContext);
            }
            return nextState.getEnum();
        }

    }

    public interface Execution<T, S> {

        FsmTransition<T> execute(S cs, FsmContext<T> cx);
    }

}
