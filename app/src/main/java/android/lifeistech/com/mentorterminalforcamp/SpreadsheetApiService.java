package android.lifeistech.com.mentorterminalforcamp;

import retrofit2.http.GET;

public class SpreadsheetApiService {
    public interface ApiService {

        public static final String API_URL = "http://konifar.com";

        static final String PATH_USERS = "/users";
        static final String EXT_JSON = ".json";

        //@GET(PATH_USERS + EXT_JSON)
        //void getUsers(Callback<List<User>> cb);
    }
}
