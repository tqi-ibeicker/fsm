package com.example.fsm.business.state;

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
public class InitialState implements ContractState {

    @Override
    public ContractStateEnum getEnum() {
        return ContractStateEnum.INITIAL;
    }

    @Override
    public FsmTransition receiveIndebted(IndebtedEvent indebtedEvent, FsmContext context) {
        log.info("Received indebted event for {}", context.getContract());
        return context.transitionTo(IndebtedState.class);
    }
}
