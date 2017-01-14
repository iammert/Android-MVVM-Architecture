package iammert.com.instagramtags.model.api.entity;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Parcel
public class User {
    public String id;
    public String username;
    @SerializedName("profile_picture")
    public String profilePicture;
    @SerializedName("full_name")
    public String fullName;
}
