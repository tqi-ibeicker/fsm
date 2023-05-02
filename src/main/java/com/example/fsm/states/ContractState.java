package com.example.fsm.states;

import com.example.fsm.business.model.BalanceUpdate;
import com.example.fsm.business.model.BrokenAgreement;
import com.example.fsm.business.model.IndebtedEvent;
import com.example.fsm.exeption.NoTransitionException;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;

public interface ContractState extends State {

    ContractStateEnum getEnum();

    default FsmTransition onEntry(FsmContext context) {
        return context.stop();
    }

    default FsmTransition receiveIndebted(IndebtedEvent indebtedEvent, FsmContext context) {
        throw new NoTransitionException(context);
    }

    default FsmTransition receiveIndebtedRemoval(IndebtedEvent indebtedEvent, FsmContext context) {
        throw new NoTransitionException(context);
    }

    default FsmTransition receiveBrokenAgreement(BrokenAgreement brokenAgreement, FsmContext context) {
        throw new NoTransitionException(context);
    }

    default FsmTransition receiveBalanceUpdate(BalanceUpdate balanceUpdate, FsmContext context) {
        throw new NoTransitionException(context);
    }

}
