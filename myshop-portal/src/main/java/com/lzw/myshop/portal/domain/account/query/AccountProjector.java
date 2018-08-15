package com.lzw.myshop.portal.domain.account.query;

import com.lzw.myshop.portal.domain.account.AccountEntity;
import com.lzw.myshop.portal.domain.account.AccountEntityRepository;
import com.lzw.myshop.portal.domain.account.event.AccountCreatedEvent;
import com.lzw.myshop.portal.domain.account.event.AccountDepositedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Service
public class AccountProjector {

	@Autowired
	private AccountEntityRepository accountEntityRepository;

	@EventHandler
	public void on(AccountCreatedEvent event) {
		AccountEntity accountEntity = new AccountEntity(event.getAccountId(), event.getNickName(), event.getDeposit());
		accountEntityRepository.save(accountEntity);
	}

	@EventHandler
	public void on(AccountDepositedEvent event) {
		AccountEntity accountEntity = accountEntityRepository.getOne(event.getAccountId());
		accountEntity.setDeposit(accountEntity.getDeposit() + event.getAmount());
		accountEntityRepository.save(accountEntity);
	}
}
