package com.lzw.myshop.portal.domain.account;

import com.lzw.myshop.portal.domain.account.command.AccountCreateCommand;
import com.lzw.myshop.portal.domain.account.command.AccountDepositCommand;
import com.lzw.myshop.portal.domain.account.event.AccountCreatedEvent;
import com.lzw.myshop.portal.domain.account.event.AccountDepositedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@Aggregate
public class Account {

	@AggregateIdentifier
	private String accountId;

	private Double deposit;

	public Account() {
	}

	@CommandHandler
	public Account(AccountCreateCommand command) {
		apply(new AccountCreatedEvent(command.getAccountId(), command.getNickName(), command.getDeposit()));
	}

	@CommandHandler
	public void handle(AccountDepositCommand command) {
		apply(new AccountDepositedEvent(command.getAccountId(), command.getAmount()));
	}

	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {
		this.accountId = event.getAccountId();
		this.deposit = event.getDeposit();
	}

	@EventSourcingHandler
	public void on(AccountDepositedEvent event) {
		this.accountId = event.getAccountId();
		this.deposit += event.getAmount();
	}


	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
}
