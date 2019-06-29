package common.server.service;

import common.server.domain.Role;
import common.server.exception.ServiceException;

import java.util.List;

public interface IRoleService {

	Role findRoleByUsername(String username) throws ServiceException;

	Role findById(Long id);
	
	List<Role> findAll();
	
	/**
	 * Fetchs the role USER from database to set it to regular users
	 * @return
	 */
	Role getRoleUser();
	
	
	/**
	 * Fetchs the role ADMIN from database to set it to admin users
	 * @return Role
	 */
	Role getRoleAdmin();

}
