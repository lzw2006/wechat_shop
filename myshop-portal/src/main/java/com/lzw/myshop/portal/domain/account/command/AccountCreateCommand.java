package com.lzw.myshop.portal.domain.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
public class AccountCreateCommand {

	@TargetAggregateIdentifier
	private String accountId;

	private String nickName;

	private Double deposit;

	public AccountCreateCommand(String accountId, String nickName, Double deposit) {
		this.accountId = accountId;
		this.nickName = nickName;
		this.deposit = deposit;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
}
