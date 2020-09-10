package org.sid.securityservice.security;

import lombok.AllArgsConstructor;
import org.sid.securityservice.entities.AppUser;
import org.sid.securityservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

// l annotation service veut dire que la classe doit etre instancier au demarrage
@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {


    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //look for User in the database
        AppUser appUser = accountService.loadUserByUsername(username);

        if(appUser ==null) throw new UsernameNotFoundException("User does not exists");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(r ->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });

        return new User(appUser.getUsername(), appUser.getPassword(),authorities);
    }
}
