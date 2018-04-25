package com.qa.service.business;

import javax.ws.rs.core.Response;

public interface AccountService {
	
	Response createAccount(String accountAsJson);

	Response updateAccount(Long id, String newAccountJson);

	Response deleteAccount(Long id);
	
	Response getAllAccounts();
	
	Response getAnAccount(String accountNumber);

}