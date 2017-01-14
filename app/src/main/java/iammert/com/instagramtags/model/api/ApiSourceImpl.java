package iammert.com.instagramtags.model.api;

import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public class ApiSourceImpl implements ApiSource{

    RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public Observable<TagSearchResponse> searchTag(String query, String token) {
        return retrofitInterface.searchTag(query, token);
    }

    @Override
    public Observable<MediaListResponse> searchMedia(String tag, String token) {
        return retrofitInterface.searchMediaWithTag(tag, token);
    }
}
