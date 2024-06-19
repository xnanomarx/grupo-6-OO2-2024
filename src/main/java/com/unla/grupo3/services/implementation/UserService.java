package com.unla.grupo3.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.unla.grupo3.repositories.IUserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo3.entities.UserRole;
import com.unla.grupo3.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	private IUserRepository userRepository;

	private IUserRoleRepository userRoleRepository;

	public UserService(IUserRepository userRepository, IUserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.grupo3.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}

	private User buildUser(com.unla.grupo3.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
				true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}

	public com.unla.grupo3.entities.User findByUsername(String username) {
		return userRepository.findByUsernameAndFetchUserRolesEagerly(username);
	}

	public void guardarUser(com.unla.grupo3.entities.User user) {
		userRepository.save(user);
	}

	public void guardarUserRoles(UserRole role) {
		userRoleRepository.save(role);
	}


}