package com.travanleo.springsecurity.oauthserver.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travanleo.springsecurity.oauthserver.domain.Authority;
import com.travanleo.springsecurity.oauthserver.domain.Role;
import com.travanleo.springsecurity.oauthserver.domain.User;
import com.travanleo.springsecurity.oauthserver.repo.UserRepo;

/**
 * User service
 * 
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepo userRepo;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (null != user) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
    }
    return null;
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Authority> collection = new ArrayList<>();
    for (Role role : roles) {
      collection.addAll(role.getAuthorities());
    }
    for (Authority item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
