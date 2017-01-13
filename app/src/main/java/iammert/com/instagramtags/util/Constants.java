package iammert.com.instagramtags.util;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class Constants {

    /**
     * Base url for api call
     */
    public static final String BASE_URL = "https://api.instagram.com/v1/";

    /**
     * Client info, scope info, response type
     */
    public static final String CLIENT_ID = "";
    public static final String REDIRECT_URI = "";
    public static final String RESPONSE_TYPE = "token";
    public static final String SCOPE = "public_content";

    /**
     * Static login url
     */
    public static final String LOGIN_URL = "https://www.instagram.com/oauth/authorize/?client_id=" +
            CLIENT_ID + "&redirect_uri=" + REDIRECT_URI +
            "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE;

    /**
     * Intent Keys
     */
    public static final String KEY_INTENT_TOKEN = "token";

    /**
     * Shared Preferences Keys
     */
    public static final String SHARED_KEY_TOKEN = "shared_key_token";

}
