package com.publicissapient.tour.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.publicissapient.tour.entity.Customer;
import com.publicissapient.tour.entity.Role;

public class CustomUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;

	public CustomUserDetails(Customer customer) {
		super();
		this.customer = customer;
	} 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles=customer.getRoles();
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
 	}

	@Override
	public String getPassword() {
		return customer.getPassword();
	}

	@Override
 	public String getUsername() {	
		return customer.getEmail() ;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
