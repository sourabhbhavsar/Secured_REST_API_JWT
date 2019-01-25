package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.utils.GravatarURLgenerator;
import io.swagger.annotations.ApiModel;

@ApiModel(description="Represents a User.")
@Entity
public class UserInfo {

	
	private String name;
	
	@Id
	private String email;
	private String avatar_url;
	
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatarUrl) {
		this.avatar_url = avatarUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@PrePersist
	public void constructAvatar() {
		avatar_url = GravatarURLgenerator.getGravatarUrl(email);
	}
}