package com.qa.service.repository;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountServiceMapRepoTest {
	
	private AccountServiceMapRepo accountServiceMapRepo;
	private static final String ACCOUNT_AS_JSON = "{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1234\"}";
	private static final String UPDATED_ACCOUNT_AS_JSON = "{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1235\"}";
	private static final String ACCOUNT_LIST_AS_JSON = "[{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1234\"}]";
	private JSONUtil jsonUtil;
	
	@Before
	public void initialise() {
		this.accountServiceMapRepo = new AccountServiceMapRepo();
		this.jsonUtil = new JSONUtil();
		this.accountServiceMapRepo.setJSONUtil(jsonUtil);
	}

	@Test
	public void testCreateAnAccount() {
		String expectedValue = "{\"message\": \"Account has been sucessfully created\"}";
		String actualValue = accountServiceMapRepo.createAnAccount(ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
		
		expectedValue = ACCOUNT_AS_JSON;
		actualValue = this.jsonUtil.getJSONForObject(this.accountServiceMapRepo.getAccounts().get(1L));
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAnAccount() {
		this.accountServiceMapRepo.createAnAccount(ACCOUNT_AS_JSON);
		String expectedValue = ACCOUNT_AS_JSON;
		String actualValue = this.jsonUtil.getJSONForObject(this.accountServiceMapRepo.getAccounts().get(1L));
		Assert.assertEquals(expectedValue, actualValue);
		
		expectedValue = "{\"message\": \"Account has been sucessfully updated\"}";
		actualValue = accountServiceMapRepo.updateAnAccount(1L, UPDATED_ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
		
		expectedValue = UPDATED_ACCOUNT_AS_JSON;
		actualValue = this.jsonUtil.getJSONForObject(this.accountServiceMapRepo.getAccounts().get(1L));
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testDeleteAccount() {
		this.accountServiceMapRepo.createAnAccount(ACCOUNT_AS_JSON);
		Assert.assertEquals(this.accountServiceMapRepo.getAccounts().size(), 1);
		
		this.accountServiceMapRepo.deleteAccount(1L);
		Assert.assertEquals(this.accountServiceMapRepo.getAccounts().size(), 0);
	}

	@Test
	public void testGetAllAccounts() {
		this.accountServiceMapRepo.createAnAccount(ACCOUNT_AS_JSON);
		String expectedValue = ACCOUNT_LIST_AS_JSON;
		String actualValue = this.accountServiceMapRepo.getAllAccounts();
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testGetAnAccount() {
		this.accountServiceMapRepo.createAnAccount(ACCOUNT_AS_JSON);
		String expectedValue = ACCOUNT_AS_JSON;
		String actualValue = this.accountServiceMapRepo.getAnAccount("1234");
		Assert.assertEquals(expectedValue, actualValue);
	}

}
