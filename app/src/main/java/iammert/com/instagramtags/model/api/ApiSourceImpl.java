package iammert.com.instagramtags.model.api;

import retrofit2.Retrofit;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class ApiSourceImpl implements ApiSource{

    RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }
}
