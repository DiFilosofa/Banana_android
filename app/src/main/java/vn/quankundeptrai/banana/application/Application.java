package vn.quankundeptrai.banana.application;

import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDexApplication;

import vn.quankundeptrai.banana.interfaces.IForegroundListener;

/**
 * Created by TQN on 1/19/2018.
 */

public class Application extends MultiDexApplication implements IForegroundListener{
    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            ////////////////////
        }

//        Foreground.init(this);
//        Foreground.getInstance(this).addListener(this);
//        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void onTerminate() {
//        Foreground.getInstance(this).removeListener(this);
        super.onTerminate();
    }

    @Override
    public void onBecameForeground() {
//        SocketManager.getInstance().connect(this);
    }

    @Override
    public void onBecameBackground() {
//        SocketManager.getInstance().disconnect();
    }
}
