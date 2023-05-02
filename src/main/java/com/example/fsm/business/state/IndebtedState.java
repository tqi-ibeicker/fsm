package com.example.fsm.business.state;

import com.example.fsm.business.model.BalanceUpdate;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.business.model.IndebtedEvent;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import com.example.fsm.states.ContractState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndebtedState implements ContractState {

    @Override
    public ContractStateEnum getEnum() {
        return ContractStateEnum.INDEBTED;
    }

    @Override
    public FsmTransition receiveIndebtedRemoval(IndebtedEvent indebtedEvent, FsmContext context) {
        log.info("Removing from indebted {}", context.getContract());
        return context.transitionTo(NotIndebtedState.class);
    }

    @Override
    public FsmTransition receiveBalanceUpdate(BalanceUpdate balanceUpdate, FsmContext context) {
        log.info("Updading Indebted balance {}", context.getContract());
        return context.stop();
    }
}
