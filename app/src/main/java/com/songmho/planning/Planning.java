package com.songmho.planning;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by songmho on 2015-01-17.
 */
public class Planning extends Application {
    private static final String APPLICATION_ID="pnxzKz0cikozZkg1CeF8KQKDMxcogS2QwTsFoiqJ";
    private static final String CLIENT_KEY="1mFw93TLIw5FDGq7qQ7M7o6Ehc1ECu8Qq0mkd4cD";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this,APPLICATION_ID,CLIENT_KEY);
        ParseACL defaultACL=new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
