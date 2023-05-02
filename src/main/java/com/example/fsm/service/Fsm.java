package com.example.fsm.service;

import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.exeption.NoTransitionException;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import com.example.fsm.model.TransitionEnum;
import com.example.fsm.states.ContractState;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public class Fsm {

    private final List<ContractState> contractStateList;
    private final Consumer<NoTransitionException> onNoTransitionException;
    private Map<ContractStateEnum, ContractState> contractStateEnumMap;
    private Map<Class<? extends ContractState>, ContractState> contractStateClassMap;

    @PostConstruct
    public void setup() {
        contractStateEnumMap = new HashMap<>();
        contractStateClassMap = new HashMap<>();
        contractStateList.forEach(i -> {
            contractStateEnumMap.put(i.getEnum(), i);
            contractStateClassMap.put(i.getClass(), i);
        });
    }

    public ContractState getByEnum(ContractStateEnum contractStateEnum) {
        return contractStateEnumMap.get(contractStateEnum);
    }

    public ContractStateEnum execute(ContractStateEnum contractStateEnum, Function<ContractState, FsmTransition> function) {
        ContractState contractState = contractStateEnumMap.get(contractStateEnum);
        FsmTransition fsmTransition;
        try {
            fsmTransition = function.apply(contractState);
        } catch (NoTransitionException e) {
            onNoTransitionException.accept(e);
            return contractStateEnum;
        } catch (Exception e) {
            return contractStateEnum;
        }

        ContractState nextState = contractState;
        while (fsmTransition.getTransitionType() != TransitionEnum.STOP) {
            FsmContext newContext = new FsmContext(fsmTransition.getContract());
            nextState = contractStateClassMap.get(fsmTransition.getNextState());
            fsmTransition = nextState.onEntry(newContext);
        }
        return nextState.getEnum();
    }

}
