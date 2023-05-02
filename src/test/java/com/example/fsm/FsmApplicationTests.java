package com.example.fsm;

import com.example.fsm.business.model.*;
import com.example.fsm.model.FsmContext;
import com.example.fsm.model.FsmTransition;
import com.example.fsm.service.Fsm;
import com.example.fsm.states.ContractState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

import static com.example.fsm.business.model.ContractStateEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FsmApplicationTests {

	@Autowired
	private Fsm contractFms;

	@Test
	void contextLoads() {
	}

	@Test
	public void test1() {
		Contract c1 = new Contract("001122");
		FsmContext fsmContext = new FsmContext(c1);
		ContractStateEnum next = contractFms.execute(INITIAL, cs -> cs.receiveIndebted(new IndebtedEvent(), fsmContext));
		assertEquals(INDEBTED, next);
		next = contractFms.execute(next, cs -> cs.receiveBalanceUpdate(new BalanceUpdate(), fsmContext));
		assertEquals(INDEBTED, next);
		next = contractFms.execute(next, cs -> cs.receiveIndebtedRemoval(new IndebtedEvent(), fsmContext));
		assertEquals(NOT_INDEBTED, next);
	}

	@Test
	public void testNoTransition() {
		Contract c1 = new Contract("001122");
		FsmContext fsmContext = new FsmContext(c1);
		ContractStateEnum next = contractFms.execute(INITIAL, cs -> cs.receiveBrokenAgreement(new BrokenAgreement(),
				fsmContext));
		assertEquals(ContractStateEnum.INITIAL, next);
	}
}
