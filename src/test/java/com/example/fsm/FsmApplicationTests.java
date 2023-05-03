package com.example.fsm;

import com.example.fsm.business.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.fsm.business.model.ContractStateEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FsmApplicationTests {

	@Autowired
	private ContractFsm contractFsm;

	@Test
	void contextLoads() {
	}

	@Test
	public void test1() {
		Contract c1 = new Contract("001122");
		ContractStateEnum next = contractFsm
				.of(c1, INITIAL)
				.execute((cs, cx) -> cs.receiveIndebted(new IndebtedEvent(), cx));
		assertEquals(INDEBTED, next);
		next = contractFsm
				.of(c1, next)
				.execute((cs, cx) -> cs.receiveBalanceUpdate(new BalanceUpdate(), cx));
		assertEquals(INDEBTED, next);
		next = contractFsm
				.of(c1, next)
				.execute((cs, cx) -> cs.receiveIndebtedRemoval(new IndebtedEvent(), cx));
		assertEquals(NOT_INDEBTED, next);
	}

	@Test
	public void testNoTransition() {
		Contract c1 = new Contract("001122");
		ContractStateEnum next = contractFsm
				.of(c1, INITIAL)
				.execute((cs, cx) -> cs.receiveBrokenAgreement(new BrokenAgreement(), cx));
		assertEquals(ContractStateEnum.INITIAL, next);
	}
}
