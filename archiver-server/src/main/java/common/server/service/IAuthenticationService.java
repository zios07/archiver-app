package common.server.service;

import common.server.domain.Account;
import common.server.exception.BadCredentialsException;
import common.server.exception.NotFoundException;

public interface IAuthenticationService {

	Account authenticate(Account account) throws NotFoundException, BadCredentialsException;
}
