package app.services;

import java.util.List;

import app.model.UserInfo;

public interface UserInfoService {
	
	List<UserInfo> listAll();

	UserInfo getById(String userEmail);

	UserInfo saveOrUpdate(UserInfo user);

	void delete(String userEmail);

}
