package common.server.service.impl;

import common.server.domain.Account;
import common.server.exception.BadCredentialsException;
import common.server.exception.NotFoundException;
import common.server.repository.AccountRepository;
import common.server.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired 
	private AccountRepository accountRepo;
	
	@Override
	public Account authenticate(Account credentials) throws NotFoundException, BadCredentialsException {
		Account account = accountRepo.findByUsername(credentials.getUsername());
		if(account == null || (account.getPassword() != null && !account.getPassword().equals(credentials.getPassword())))
			throw new BadCredentialsException("AUTHENTICATION.ERROR", "Bad credentials");
		return account;
	}

}
