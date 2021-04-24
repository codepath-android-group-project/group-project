package com.example.animebook;

import com.parse.Parse;
import android.app.Application;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FP87jyHtDgf900rbL8qwVLu0rtuu9KXiitFeu9Fy")
                .clientKey("wDWmjpVjWWLvioem6zvQWTjkvUkgjZBFFwBbR8yG")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
