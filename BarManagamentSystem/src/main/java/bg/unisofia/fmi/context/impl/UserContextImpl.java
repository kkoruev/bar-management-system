package bg.unisofia.fmi.context.impl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import bg.unisofia.fmi.models.User;

@SessionScoped
public class UserContextImpl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private User currentUser;
	
	/* (non-Javadoc)
	 * @see bg.unisofia.fmi.context.impl.UserConext#getUser()
	 */
	public User getUser(){
		return currentUser;
	}
	
	/* (non-Javadoc)
	 * @see bg.unisofia.fmi.context.impl.UserConext#setUser(bg.unisofia.fmi.models.User)
	 */
	public void setUser(User user){
		currentUser = user;
	}
}
