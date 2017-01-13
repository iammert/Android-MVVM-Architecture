package iammert.com.instagramtags.domain.searchtag;

import iammert.com.instagramtags.model.api.entity.TagSearchResponse;
import rx.Observable;

/**
 * Created by mertsimsek on 13/01/17.
 */

public interface SearchTagUsecase {

    Observable<TagSearchResponse> searchTag(String query);
}
