package com.example.fsm.business.state;

import com.example.fsm.business.model.BalanceUpdate;
import com.example.fsm.business.model.Contract;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.business.model.IndebtedEvent;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
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
    public FsmTransition<Contract> receiveIndebtedRemoval(IndebtedEvent indebtedEvent, FsmContext<Contract> context) {
        log.info("Removing from indebted {}", context.getPayload());
        return context.transitionTo(NotIndebtedState.class);
    }

    @Override
    public FsmTransition<Contract> receiveBalanceUpdate(BalanceUpdate balanceUpdate, FsmContext<Contract> context) {
        log.info("Updading Indebted balance {}", context.getPayload());
        return context.stop();
    }
}
