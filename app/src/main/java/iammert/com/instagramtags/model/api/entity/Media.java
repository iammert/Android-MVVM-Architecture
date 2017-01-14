package iammert.com.instagramtags.model.api.entity;

import org.parceler.Parcel;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Parcel
public class Media {
    public String id;
    public String type;
    public String filter;
    public Comment comments;
    public Like likes;
    public User user;
    public ImageWrapper images;
}
