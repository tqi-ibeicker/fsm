package com.example.fsm.business.state;

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
public class InitialState implements ContractState {

    @Override
    public ContractStateEnum getEnum() {
        return ContractStateEnum.INITIAL;
    }

    @Override
    public FsmTransition<Contract> receiveIndebted(IndebtedEvent indebtedEvent, FsmContext<Contract> context) {
        log.info("Received indebted event for {}", context.getPayload());
        return context.transitionTo(IndebtedState.class);
    }
}
