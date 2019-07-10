package common.server.util;

public class UtilContants {

    private UtilContants() {

    }

    /* Utils for role service */
    public static final String ROLE_CODE_USER = "USER";
    public static final String ROLE_CODE_ADMIN = "ADMIN";

    /* Utils for Jwt security system */
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 4 * 60 * 60 * 1000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/api/v1/authentication/authenticate";
    public static final String REGISTRATION_URL = "/api/users/register";
    public static final String DOWNLOAD_URL = "/api/documents/**";


}
