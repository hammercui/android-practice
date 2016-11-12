package hammer.learandroid.dagger2;

import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;
import com.hammer.example.HMROpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hammer.learandroid.MyApplication;
import hammer.learandroid.dao.SqliteManager;

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
        PackageInfo packageInfo = application.getAppInfo();
        String assetsName = "xue_old.db";
        String dbName = "xue_old.db";
        String DATABASE_PATH = packageInfo.applicationInfo.dataDir+"/databases";
        //拷贝assets的db文件
        SqliteManager manager = new SqliteManager();
        manager.copyAssetsDbToApkDb(application,assetsName,DATABASE_PATH,dbName,true);

        HMROpenHelper helper = new HMROpenHelper(application, dbName, null);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
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
