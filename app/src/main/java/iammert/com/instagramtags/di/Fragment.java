package iammert.com.instagramtags.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mertsimsek on 13/01/17.
 */

@Scope
@Retention(RUNTIME)
public @interface Fragment {
}
