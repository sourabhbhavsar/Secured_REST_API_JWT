package app.security;

public class SecurityConstants {

	public static final String SIGN_UP_URL = "/signup";
	public static final long EXPIRATION_TIME = 600_000;//10 mins
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String LOGIN_URL = "/login";

}
