package com.example.fsm.business.state;

import com.example.fsm.business.model.*;
import com.example.fsm.exeption.NoTransitionException;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import com.example.fsm.model.State;

import static com.example.fsm.states.StateUtils.getMethodName;

public interface ContractState extends State<Contract, ContractStateEnum> {

    default FsmTransition<Contract> receiveIndebted(IndebtedEvent indebtedEvent, FsmContext<Contract> context) {
        throw new NoTransitionException(getMethodName());
    }

    default FsmTransition<Contract> receiveIndebtedRemoval(IndebtedEvent indebtedEvent, FsmContext<Contract> context) {
        throw new NoTransitionException(getMethodName());
    }

    default FsmTransition<Contract> receiveBrokenAgreement(BrokenAgreement brokenAgreement, FsmContext<Contract> context) {
        throw new NoTransitionException(getMethodName());
    }

    default FsmTransition<Contract> receiveBalanceUpdate(BalanceUpdate balanceUpdate, FsmContext<Contract> context) {
        throw new NoTransitionException(getMethodName());
    }

}
