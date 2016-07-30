package bg.unisofia.fmi.context.impl;

import javax.ejb.Stateful;

import bg.unisofia.fmi.models.User;

@Stateful
public class UserContext {

	private User currentUser;
	
	public User getUser(){
		return currentUser;
	}
	
	public void setUser(User user){
		currentUser = user;
	}
}
