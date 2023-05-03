package com.example.fsm.business.model;

import com.example.fsm.model.NoTransition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NoTransitionHandler {

    public void handleNoTransition(NoTransition noTransition) {
        log.warn("{}", noTransition);
    }
}
