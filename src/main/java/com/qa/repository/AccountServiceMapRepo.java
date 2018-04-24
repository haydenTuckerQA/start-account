package com.qa.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountServiceMapRepo implements AccountServiceRepo {
	
	@Inject
	private JSONUtil jsonUtil;
	
	private Long id;
	private Map<Long, Account> accounts;
	
	public AccountServiceMapRepo() {
		this.id = 1L;
		this.accounts = new HashMap<Long, Account>();
	}

	@Override
	public String createAnAccount(String accountAsJson) {
		Account accountToAdd = jsonUtil.getObjectForJSON(accountAsJson, Account.class);
		this.accounts.put(this.id, accountToAdd);
		this.id++;
		return "Account has been sucessfully created";
	}

	@Override
	public String updateAnAccount(Long id, String newAccountJSON) {
		Account updatedAccount = jsonUtil.getObjectForJSON(newAccountJSON, Account.class);
		Account oldAccount = this.accounts.get(id);
		
		if (oldAccount != null) {
			this.accounts.put(id, updatedAccount);
		}
		
		return "Account has been sucessfully updated";
	}

	@Override
	public String deleteAccount(Long accountId) {
		Account accountToDelete = this.accounts.get(accountId);
		
		if (accountToDelete != null) {
			this.accounts.remove(accountId);
		}
		
		return "Account has been sucessfully deleted";
	}

	@Override
	public String getAllAccounts() {
		return this.jsonUtil.getJSONForObject(this.accounts.values());
	}

	@Override
	public String getAnAccount(String accountNumber) {
		Account accountToGet = null;
		
		for (Account account : this.accounts.values()) {
			if (account.getAccountNumber().equals(accountNumber)) {
				accountToGet = account;
			}
		}
		
		return this.jsonUtil.getJSONForObject(accountToGet);
	}

	@Override
	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}

}
