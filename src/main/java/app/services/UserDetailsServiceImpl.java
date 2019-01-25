package app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.model.Note;
import app.model.UserInfo;
import app.repositories.UserInfoRepository;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserInfoService {
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserInfoRepository userRepository)
	{
		this.userInfoRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo applicationUser = null;
		
    	try
    	{
    		applicationUser = userInfoRepository.findById(email).get();
    	}
    	catch(NoSuchElementException ex) {
    		throw new UsernameNotFoundException(email);
    	}
		
        return new User(applicationUser.getEmail(), 
        		applicationUser.getPassword(), 
		        new ArrayList<GrantedAuthority>()        		        
		       );
	}

	@Override
	public List<UserInfo> listAll() {		

		List<UserInfo> users = new ArrayList<UserInfo>();
		userInfoRepository.findAll().forEach(users::add); 
		
		return users;
	}

	@Override
	public UserInfo getById(String userEmail) {
		
		return userInfoRepository.findById(userEmail).get();
	}

	@Override
	public UserInfo saveOrUpdate(UserInfo user) {
		UserInfo savedUser = userInfoRepository.save(user);
		return savedUser;
	}

	@Override
	public void delete(String userEmail) {
		userInfoRepository.deleteById(userEmail);
	}  
}
