package com.beiluoshimen.securityguard.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beiluoshimen.securityguard.account.repository.Account;
import com.beiluoshimen.securityguard.account.repository.AccountRepository;


@Service("userDetailsService")
public class LoginService implements UserDetailsService{

	//get the database implementations.
	@Autowired
	private AccountRepository accounts;
	
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Account account = accounts.findByUsername(username).iterator().next();
		//specify the authority(ies) of this user
		List<GrantedAuthority> authorities = buildUserAuthority(account);
		return buildUserForAuthentication(account, authorities);
	}
	
	
	private List<GrantedAuthority> buildUserAuthority(Account account) {
		//asuming all user authority is 'user' 
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		result.add(new SimpleGrantedAuthority("user"));
		return result;
	}
	
	private User buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
			return new User(account.getUsername(), account.getPassword(), 
				true, true, true, true, authorities);
		}
	 
 

}
