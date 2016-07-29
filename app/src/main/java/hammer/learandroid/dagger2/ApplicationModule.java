package hammer.learandroid.dagger2;

import android.database.sqlite.SQLiteDatabase;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;
import com.hammer.example.HMROpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hammer.learandroid.MyApplication;

/**
 * 提供application生命周期的对象
 * @Provides后面提供具体对象
 * Created by hammer on 2016/7/28.
 */

@Module
public class ApplicationModule {

    private MyApplication myApplication;
    public ApplicationModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    @Singleton
    @Provides
    public MyApplication provideApplication(){
        return  myApplication;
    }

    @Singleton
    @Provides
    public SQLiteDatabase provideSQLiteDatabase(MyApplication application){
//        PackageInfo packageInfo = application.getAppInfo();
//        String assetsName = "lesson.db";
//        String dbName = "xue.db";
//        String DATABASE_PATH = packageInfo.applicationInfo.dataDir+"/database";
//        SqliteManager manager = new SqliteManager();
//        manager.copyAssetsDbToApkDb(application,assetsName,DATABASE_PATH,dbName,false);
//
        HMROpenHelper helper = new HMROpenHelper(application, "xue_old.db", null);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
       // SQLiteDatabase sqlDB = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH+"/"+dbName,null);
        return  sqlDB;
    }
    @Singleton
    @Provides
    public DaoMaster provideDaoMaster(SQLiteDatabase sqLiteDatabase){
        return  new DaoMaster(sqLiteDatabase);
    }

    @Singleton
    @Provides
    public DaoSession provideDaoSession(DaoMaster daoMaster){
        return  daoMaster.newSession();
    }
}
