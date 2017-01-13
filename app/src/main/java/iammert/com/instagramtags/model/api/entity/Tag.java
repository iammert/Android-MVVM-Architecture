package iammert.com.instagramtags.model.api.entity;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by mertsimsek on 13/01/17.
 */
@Parcel
public class Tag {
    public String name;
    @SerializedName("media_count")
    public int mediaCount;
}
