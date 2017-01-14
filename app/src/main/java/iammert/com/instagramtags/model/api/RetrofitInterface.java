package iammert.com.instagramtags.model.api;

import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface RetrofitInterface {

    @GET("tags/search")
    Observable<TagSearchResponse> searchTag(@Query("q") String query,
                                            @Query("access_token") String token);

    @GET("tags/{tag_name}/media/recent")
    Observable<MediaListResponse> searchMediaWithTag(@Path("tag_name") String tag,
                                                     @Query("access_token") String token);
}
