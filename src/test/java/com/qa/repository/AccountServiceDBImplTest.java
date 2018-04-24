package com.qa.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceDBImplTest {
	@InjectMocks
	private AccountServiceDBImpl accountServiceDBI;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Account> query;
	
	JSONUtil jsonUtil;
	
	private static final String ACCOUNT_AS_JSON = "{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1234\"}";
	private static final String ACCOUNT_LIST_AS_JSON = "[{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1234\"}]";
	
	@Before
	public void initialise() {
		accountServiceDBI.setEm(em);
		jsonUtil = new JSONUtil();
		accountServiceDBI.setJSONUtil(jsonUtil);
	}
	
	@Test
	public void testCreateAnAccount() {
		String expectedValue = "Account has been sucessfully created";
		String actualValue = accountServiceDBI.createAnAccount(ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAnAccount() {
		String expectedValue = "Account has been sucessfully updated";
		String actualValue = accountServiceDBI.updateAnAccount(123L, ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testDeleteAccount() {
		String expectedValue = "Account has been sucessfully deleted";
		String actualValue = accountServiceDBI.deleteAccount(123L);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.eq(Account.class))).thenReturn(query);
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("Hayden", "Tucker", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		String expectedValue = ACCOUNT_LIST_AS_JSON;
		String actualValue =  accountServiceDBI.getAllAccounts();
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testGetAnAccount() {
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.eq(Account.class))).thenReturn(query);
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("Hayden", "Tucker", "1234"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		String expectedValue = ACCOUNT_AS_JSON;
		String actualValue = accountServiceDBI.getAnAccount("1234");
		Assert.assertEquals(expectedValue, actualValue);
	}
}