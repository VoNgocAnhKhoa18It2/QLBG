package com.qlgiay.DAO;

import com.qlgiay.model.User;

public interface UserDAO {
	public boolean userRegister(User us);
	public User login(String email,String password);
}
