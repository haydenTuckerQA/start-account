package com.qa.service.business;

import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.service.repository.AccountServiceRepo;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService {
	
	@Inject
	AccountServiceRepo accountServiceRepo;
	
	@Inject
	JSONUtil jsonUtil;

	@Override
	public String createAccount(String accountAsJson) {
		Account account = jsonUtil.getObjectForJSON(accountAsJson, Account.class);
		
		if (account.getAccountNumber().equals("9999")) {
			return "{\"message\": \"This account is blocked\"}"; 
		} else {
			return accountServiceRepo.createAnAccount(accountAsJson);
		}
	}

	@Override
	public String updateAccount(Long id, String newAccountJson) {
		return accountServiceRepo.updateAnAccount(id, newAccountJson);
	}

	@Override
	public String deleteAccount(Long accountId) {
		return accountServiceRepo.deleteAccount(accountId);
	}

	@Override
	public String getAllAccounts() {
		return accountServiceRepo.getAllAccounts();
	}

	@Override
	public String getAnAccount(String accountNumber) {
		return accountServiceRepo.getAnAccount(accountNumber);
	}
}
