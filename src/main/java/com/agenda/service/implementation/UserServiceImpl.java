package com.agenda.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agenda.converter.UserConverter;
import com.agenda.entity.UserRole;
import com.agenda.model.UserModel;
import com.agenda.repository.UserRepository;
import com.agenda.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.agenda.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(com.agenda.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	@Override
	public boolean createUser(UserModel userModel) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		com.agenda.entity.User user = new com.agenda.entity.User();
		user = userConverter.modelToEntity(userModel);
		user.setPassword(pe.encode(userModel.getPassword()));
		UserRole userRole = new UserRole(userConverter.modelToEntity(userModel),"ROLE_USER");
		user.getUserRole().add(userRole);
		user = userRepository.save(user);
		if	(null != user) {
			return true;
		}
		return false;
	}

}
