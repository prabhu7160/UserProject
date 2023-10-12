package com.vois.user.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
	
	private UsersProject user;
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGranterAuth = new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGranterAuth);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
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
//	@Id
//	@GeneratedValue
//	private int id;
//	@Column(unique= true)
//	@NotBlank(message="mandatory field: username")
//	private String userName;
//	private String password;
//	private Boolean active;
//	private String roles;

	public UsersProject getUser() {
		return user;
	}

	public void setUser(UsersProject user) {
		this.user = user;
	}

	public CustomUserDetails(UsersProject user) {
		super();
		this.user=user;
	}	
}
