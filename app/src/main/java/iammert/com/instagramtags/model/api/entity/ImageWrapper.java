package iammert.com.instagramtags.model.api.entity;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by mertsimsek on 14/01/17.
 */
@Parcel
public class ImageWrapper {
    @SerializedName("low_resolution")
    public Image lowResolution;
    public Image thumbnail;
    @SerializedName("standard_resolution")
    public Image standartResolution;
}
