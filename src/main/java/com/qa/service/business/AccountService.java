package com.qa.service.business;

public interface AccountService {
	
	String createAccount(String accountAsJson);

	String updateAccount(Long id, String newAccountJson);

	String deleteAccount(Long accountId);
	
	String getAllAccounts();
	
	String getAnAccount(String accountNumber);

}