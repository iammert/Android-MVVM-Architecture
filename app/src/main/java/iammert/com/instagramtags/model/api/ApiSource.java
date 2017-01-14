package iammert.com.instagramtags.model.api;

import iammert.com.instagramtags.model.api.entity.MediaListResponse;
import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface ApiSource {

    Observable<TagSearchResponse> searchTag(String query, String token);

    Observable<MediaListResponse> searchMedia(String tag, String token);
}
