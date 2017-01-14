package iammert.com.instagramtags.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mertsimsek on 14/01/17.
 */

public class ImageDataBinding {

    @BindingAdapter({"bind:imageUrl", "bind:placeHolder"})
    public static void loadImage(ImageView view, String url, Drawable placeHolder) {
        if (url != null && !url.equals(""))
            Picasso.with(view.getContext()).load(url).placeholder(placeHolder).resize(500, 500).centerCrop().into(view);
    }

    @BindingAdapter({"bind:circleLibImageUrl", "bind:circleLibPlaceHolder"})
    public static void loadCircleImage(CircleImageView view, String url, Drawable placeHolder) {
        if (url != null && !url.equals(""))
            Picasso.with(view.getContext()).load(url).placeholder(placeHolder).resize(500, 500).centerCrop().into(view);
    }
}
