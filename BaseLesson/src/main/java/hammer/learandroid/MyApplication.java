package hammer.learandroid;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;

/**
 * Created by hammer on 2016/6/14.
 */
public class MyApplication extends Application {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SQLiteDatabase sqlDB;
    private static MyApplication instance;
    public static MyApplication getIns(){
        return  instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public  DaoMaster getDaoMaster(Context context){
        if (daoMaster == null){
            daoMaster = new DaoMaster(getSQLDB(context));
        }
        return daoMaster;
    }

    public SQLiteDatabase getSQLDB(Context context){
        if (sqlDB == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "zlot-db", null);
            sqlDB = helper.getWritableDatabase();
        }
        return  sqlDB;
    }

    public DaoSession getDaoSession(Context context){
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
