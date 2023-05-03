package com.example.fsm.business.model;

import com.example.fsm.business.state.ContractState;
import com.example.fsm.model.AbstractFsm;
import com.example.fsm.model.NoTransition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContractFsm extends AbstractFsm<ContractState, Contract, ContractStateEnum> {

    public ContractFsm(List<ContractState> contractStateList) {
        super(contractStateList);
    }

    @Override
    protected void handleNoTransition(NoTransition<Contract, ContractStateEnum> noTransition) {
        log.warn("{}", noTransition);
    }

}
