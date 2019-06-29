package common.server.service;

import common.server.domain.Account;
import common.server.exception.NotFoundException;

import java.util.List;

public interface IAccountService {

	Account addAccount(Account accountd);

	Account findAccount(long id) throws NotFoundException;

	List<Account> findAllAccounts();

	void deleteAccount(long id) throws NotFoundException;

	List<Account> searchAccounts(Account account) throws NotFoundException;
	
	Account findAccountByUsername(String username) throws NotFoundException;
}
