package iammert.com.instagramtags.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by mertsimsek on 15/01/17.
 */

public class DialogBuilder {

    public static MaterialDialog.Builder progressDialog(Context context, int title, int content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .canceledOnTouchOutside(false)
                .progress(true, 0);
    }

}
