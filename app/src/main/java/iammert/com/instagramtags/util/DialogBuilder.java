package iammert.com.instagramtags.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import iammert.com.instagramtags.R;

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

    public static MaterialDialog.Builder infoDialog(Context context, int title, int content) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(R.string.dialog_action_ok)
                .positiveColorRes(R.color.colorPrimary);
    }

}
