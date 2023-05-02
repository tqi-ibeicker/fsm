package com.example.fsm.business.model;

import com.example.fsm.exeption.NoTransitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NoTransitionHandler {

    public void handleNoTransition(NoTransitionException e) {
        log.warn("{}", e.toString());
    }
}
