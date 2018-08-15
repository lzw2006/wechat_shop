package com.lzw.myshop.portal.ui.rest;

import com.lzw.myshop.portal.domain.account.AccountEntity;
import com.lzw.myshop.portal.domain.account.AccountEntityRepository;
import com.lzw.myshop.portal.domain.account.command.AccountCreateCommand;
import com.lzw.myshop.portal.domain.account.command.AccountDepositCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Ray Leung
 * @date 2018/4/22
 * @desctption
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private AccountEntityRepository accountEntityRepository;

	@PostMapping("")
	public CompletableFuture<Object> createAccount(@RequestParam String name) {
		UUID uuid = UUID.randomUUID();
		AccountCreateCommand accountCreateCommand = new AccountCreateCommand(uuid.toString(), name, 0d);
		return commandGateway.send(accountCreateCommand);
	}

	@GetMapping("/{accountId}")
	public AccountEntity getAccountById(@PathVariable String accountId) {
		return accountEntityRepository.findByAccountId(accountId);
	}

	@PutMapping("/{accountId}/deposit/{amount}")
	public CompletableFuture<Object> depositMoney(@PathVariable String accountId, @PathVariable Double amount) {
		return commandGateway.send(new AccountDepositCommand(accountId, amount));
	}

}
