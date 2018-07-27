package com.agenda.converter;

import org.springframework.stereotype.Component;

import com.agenda.entity.User;
import com.agenda.model.UserModel;

@Component
public class UserConverter {

	public User modelToEntity(UserModel userModel) {
		User user = new User();
		user.setUsername(userModel.getUsername());
		user.setPassword(userModel.getPassword());
		user.setEnabled(userModel.isEnabled());
		return user;
	}
	
	public UserModel entityToModel(User user) {
		UserModel userModel = new UserModel();
		userModel.setUsername(user.getUsername());
		userModel.setPassword(user.getPassword());
		userModel.setEnabled(user.isEnabled());
		return userModel;
	}
	
}
