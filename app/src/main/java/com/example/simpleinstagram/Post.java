package com.example.simpleinstagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.Date;

@Parcel(analyze={Post.class})
@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public Post() { }

    public String getKeyDescription() { return getString(KEY_DESCRIPTION); }

    public ParseFile getKeyImage() { return getParseFile(KEY_IMAGE); }

    public ParseUser getKeyUser() { return getParseUser(KEY_USER); }

    public Date getKeyCreatedAt() {
        return this.getCreatedAt();
    }

    public void setKeyDescription(String description){ put(KEY_DESCRIPTION, description); }

    public void setKeyImage(ParseFile image){ put(KEY_IMAGE, image); }

    public void setKeyUser(ParseUser user){ put(KEY_USER, user); }

}
