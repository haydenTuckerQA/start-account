package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountServiceDBImpl {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	JSONUtil jsonUtil;
	
	@Transactional(REQUIRED)
	public String createAnAccount(String accountAsJson) {
		Account newAccount = jsonUtil.getObjectForJSON(accountAsJson, Account.class);
		em.persist(newAccount);
		return "Account has been sucessfully created";
	}
	
	@Transactional(REQUIRED)
	public String updateAnAccount(Long id, String newAccountJSON) {
		Account oldAccount = em.find(Account.class, id);
		Account newAccount = jsonUtil.getObjectForJSON(newAccountJSON, Account.class);
		if (oldAccount != null) {
			em.merge(newAccount);
		}
		return "Account has been sucessfully updated";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long accountId) {
		Account accountToRemove = em.find(Account.class, accountId);
		if (accountToRemove != null) {
			em.remove(accountToRemove);
		}
		return "Account has been sucessfully deleted";
	}
	
	public String getAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a", Account.class);
		List<Account> accountsList = query.getResultList();
		return jsonUtil.getJSONForObject(accountsList);
	}
	
	public String getAnAccount(String accountNumber) {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a WHERE a.accountNumber = :accountNumber", Account.class);
		return jsonUtil.getJSONForObject(query.getResultList().get(0));
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
