package com.example.fsm.business.model;

import com.example.fsm.service.Fsm;
import com.example.fsm.states.ContractState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FsmConfig {

    @Autowired
    private List<ContractState> contractStateList;

    @Autowired
    private NoTransitionHandler noTransitionHandler;

    @Bean
    public Fsm buildFsm() {
        return new Fsm(contractStateList, e -> noTransitionHandler.handleNoTransition(e));
    }
}
