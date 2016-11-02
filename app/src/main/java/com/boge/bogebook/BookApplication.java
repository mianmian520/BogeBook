package com.boge.bogebook;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boge.bogebook.component.ApplicationComponent;
import com.boge.bogebook.component.DaggerApplicationComponent;
import com.boge.bogebook.module.ApplicationModule;
import com.boge.bogebook.util.SharedPreferencesUtil;
import com.boge.dao.DaoMaster;
import com.boge.dao.DaoSession;
import com.boge.dao.LocalAndRecomendBookDao;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public class BookApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    private static Context mContext;

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        mContext = this;
        initPrefs();
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static DaoSession getDaoSession() {
        if(daoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "book.db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static LocalAndRecomendBookDao getLocalAndRecomendBookDao(){
        return getDaoSession().getLocalAndRecomendBookDao();
    }
}
