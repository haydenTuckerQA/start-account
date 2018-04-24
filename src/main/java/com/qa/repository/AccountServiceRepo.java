package com.qa.repository;

import com.qa.util.JSONUtil;

public interface AccountServiceRepo {
	
	String createAnAccount(String accountAsJson);

	String updateAnAccount(Long id, String newAccountJSON);

	String deleteAccount(Long accountId);

	String getAllAccounts();

	String getAnAccount(String accountNumber);

	void setJSONUtil(JSONUtil jsonUtil);
}