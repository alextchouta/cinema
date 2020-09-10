package org.sid.securityservice.service;

import org.sid.securityservice.entities.AppRole;
import org.sid.securityservice.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String username, String password, String confirmedPassword);
    public AppRole saveAppRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String roleName);
}
