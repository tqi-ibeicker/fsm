package com.example.fsm.business.state;

import com.example.fsm.business.model.Contract;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotIndebtedState implements ContractState {

    @Override
    public ContractStateEnum getEnum() {
        return ContractStateEnum.NOT_INDEBTED;
    }

    @Override
    public FsmTransition<Contract> onEntry(FsmContext<Contract> context) {
        log.info("Sending to history and deleting {}", context.getPayload());
        return context.stop();
    }
}
