package com.qa.repository;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

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
	public Account createAnAccount(Account account) {
		em.persist(account);
		return account;
	}
	
	@Transactional(REQUIRED)
	public Account updateAnAccount(Long id, String newAccountJSON) {
		Account oldAccount = em.find(Account.class, id);
		Account newAccount = jsonUtil.getObjectForJSON(newAccountJSON, Account.class);
		if (oldAccount != null) {
			em.merge(newAccount);
		}
		return newAccount;
	}
	
	@Transactional(REQUIRED)
	public Account deleteAccount(Account account) {
		em.remove(account);
		return account;
	}
	
	public List<Account> getAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a ORDER BY a.id", Account.class);
		return query.getResultList();
	}
	
	public Account getAnAccount(String accountNumber) {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a WHERE a.accountNumber = :accountNumber", Account.class);
		return query.getResultList().get(0);
	}
}
