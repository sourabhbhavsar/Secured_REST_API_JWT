package app.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.model.UserInfo;
import app.security.SecurityConstants;
import app.services.UserInfoService;
import app.utils.JWTtokenUtil;
import app.utils.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="User")
@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@ApiOperation(value="User Signup")
	@RequestMapping(method = RequestMethod.POST, 
	path = SecurityConstants.SIGN_UP_URL, 
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)

	public String addUser(@RequestBody UserInfo user) {

		String password = user.getPassword();

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userInfoService.saveOrUpdate(user);

		JSONObject json = new JSONObject();
		json.put("message", "Thank you for signing up, you can use your email " + user.getEmail() + " for logging in.");
		return json.toString();
	}	


	@ApiOperation(value="Getting user's own information")
	@RequestMapping(method = RequestMethod.GET, 
	path = "/me",
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)

	public String getCurrentUser(@RequestHeader(name = "Authorization") String jwtTokenStr)
	{
		String userEmail = JWTtokenUtil.getUserFromToken(jwtTokenStr);

		if (userEmail != null)
		{
			JSONObject jsonObject = new JSONObject();
			UserInfo user = userInfoService.getById(userEmail);

			jsonObject.put("name", user.getName());
			jsonObject.put("email", user.getEmail());
			jsonObject.put("avatar", user.getAvatar_url());

			return jsonObject.toString();
		}

		return null;
	}


	/*
	 * This is a dummy endpoint just for the swagger to create documentation
	 * The actual endpoint which overrides this one is configured by the
	 * spring security filter.
	 */
	@ApiOperation(value = "Login", notes = "Login for the user.")
	@ApiResponses({@ApiResponse(code = 200, message = "", response = JwtToken.class)})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	void login( @RequestParam("email") String email,
     			@RequestParam("password") String password) 
	{
		throw new IllegalStateException("Add Spring Security to handle authentication");
	}
}
