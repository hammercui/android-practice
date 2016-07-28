package hammer.learandroid.dagger2;

import android.database.sqlite.SQLiteDatabase;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;

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
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(application, "zlot-db", null);
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
