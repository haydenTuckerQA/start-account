package com.qa.service.business;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qa.domain.Account;
import com.qa.service.repository.AccountServiceRepo;
import com.qa.util.JSONUtil;

@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AccountServiceImpl implements AccountService {
	
	@Inject
	AccountServiceRepo accountServiceRepo;
	
	@Inject
	JSONUtil jsonUtil;

	@POST
	@Path("/create_account")
	@Override
	public Response createAccount(String accountAsJson) {
		Account account = jsonUtil.getObjectForJSON(accountAsJson, Account.class);
		String message = null;
		if (account.getAccountNumber().equals("9999")) {
			message = "{\"message\": \"This account is blocked\"}"; 
		} else {
			message = accountServiceRepo.createAnAccount(accountAsJson);
		}
		
		if (message != null) {
			return Response.ok(message).build();
		} else {
			return new BadRequestException().getResponse();
		}
	}

	@PUT
	@Path("/update_account/{id}")
	@Override
	public Response updateAccount(@PathParam("id") Long id, String newAccountJson) {
		String message = accountServiceRepo.updateAnAccount(id, newAccountJson);
		if (message != null) {
			return Response.ok(message).build();
		} else {
			return new BadRequestException().getResponse();
		}
	}

	@DELETE
	@Path("/delete_account/{id}")
	@Override
	public Response deleteAccount(@PathParam("id") Long id) {
		String message = accountServiceRepo.deleteAccount(id);
		if (message != null) {
			return Response.ok(message).build();
		} else {
			return new BadRequestException().getResponse();
		}
	}

	@GET
	@Path("/get_all_accounts")
	@Override
	public Response getAllAccounts() {
		String accountsJson = accountServiceRepo.getAllAccounts();
		if (accountsJson != null) {
			return Response.ok(accountsJson).build();
		} else {
			return new BadRequestException().getResponse();
		}
	}

	@GET
	@Path("/get_account/{accountNumber}")
	@Override
	public Response getAnAccount(@PathParam("accountNumber") String accountNumber) {
		String accountJson = accountServiceRepo.getAnAccount(accountNumber);
		if (accountJson != null) {
			return Response.ok(accountJson).build();
		} else {
			return new BadRequestException().getResponse();
		}
	}
}
