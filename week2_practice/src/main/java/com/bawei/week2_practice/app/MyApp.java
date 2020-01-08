package com.bawei.week2_practice.app;

import android.app.Application;

import com.bawei.week2_practice.greendao.DaoMaster;
import com.bawei.week2_practice.greendao.DaoSession;

/**
 * @author 刘云蔚
 * @createTime 2020/1/4 9:26
 * @description
 */
public class MyApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "cache.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
