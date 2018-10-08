package com.lifeistech.android.mentorterminalforcamp;

import android.app.Application;

import io.realm.Realm;

public class MentorTerminalForCampApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(getApplicationContext());
    }

}
