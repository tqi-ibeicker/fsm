package com.example.fsm.service;

import com.example.fsm.business.model.Contract;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.exeption.NoTransitionException;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import com.example.fsm.model.NoTransition;
import com.example.fsm.model.TransitionEnum;
import com.example.fsm.business.state.ContractState;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class Fsm {

    private final List<ContractState> contractStateList;
    private final Consumer<NoTransition<Contract, ContractStateEnum>> onNoTransition;
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

    public WithContext of(Contract contract, ContractStateEnum contractStateEnum) {
        return new WithContext(new FsmContext<>(contract), contractStateEnum);
    }

    public class WithContext {

        private final FsmContext<Contract> fsmContext;

        private final ContractStateEnum contractStateEnum;

        public WithContext(FsmContext<Contract> fsmContext, ContractStateEnum contractStateEnum) {
            this.fsmContext = fsmContext;
            this.contractStateEnum = contractStateEnum;
        }

        public ContractStateEnum execute(Execution execution) {
            ContractState contractState = contractStateEnumMap.get(contractStateEnum);
            FsmTransition<Contract> fsmTransition;
            try {
                fsmTransition = execution.execute(contractState, fsmContext);
            } catch (NoTransitionException e) {
                NoTransition<Contract, ContractStateEnum> noTransition = new NoTransition<>(contractStateEnum, e.getMethodName(), fsmContext.getPayload());
                onNoTransition.accept(noTransition);
                return contractStateEnum;
            } catch (Exception e) {
                return contractStateEnum;
            }

            ContractState nextState = contractState;
            while (fsmTransition.getTransitionType() != TransitionEnum.STOP) {
                FsmContext<Contract> newContext = new FsmContext<>(fsmTransition.getPayload());
                nextState = contractStateClassMap.get(fsmTransition.getNextState());
                fsmTransition = nextState.onEntry(newContext);
            }
            return nextState.getEnum();
        }

    }

    public interface Execution {

        FsmTransition<Contract> execute(ContractState cs, FsmContext<Contract> cx);
    }

}
