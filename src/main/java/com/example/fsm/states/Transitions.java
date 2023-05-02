package com.example.fsm.states;

import com.example.fsm.business.model.BalanceUpdate;
import com.example.fsm.business.model.BrokenAgreement;
import com.example.fsm.business.model.Contract;
import com.example.fsm.business.model.IndebtedEvent;
import com.example.fsm.business.model.ContractStateEnum;
import com.example.fsm.model.FsmTransition;

public interface Transitions extends State {

    ContractStateEnum getEnum();

    default void onEntry(Contract contract) {}

    FsmTransition receiveIndebted(IndebtedEvent indebtedEvent, Contract contract);

    FsmTransition receiveIndebtedRemoval(IndebtedEvent indebtedEvent, Contract contract);

    FsmTransition receiveBrokenAgreement(BrokenAgreement brokenAgreement, Contract contract);

    FsmTransition receiveBalanceUpdate(BalanceUpdate balanceUpdate, Contract contract);

}
