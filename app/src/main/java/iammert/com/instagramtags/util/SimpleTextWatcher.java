package iammert.com.instagramtags.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by mertsimsek on 13/01/17.
 */

public abstract class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
