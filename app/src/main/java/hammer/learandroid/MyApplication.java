package hammer.learandroid;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import com.hammer.example.DaoMaster;
import com.hammer.example.DaoSession;

import javax.inject.Inject;

import hammer.learandroid.dagger2.ApplicationComponent;
import hammer.learandroid.dagger2.ApplicationModule;
import hammer.learandroid.dagger2.DaggerApplicationComponent;

/**
 * Created by hammer on 2016/6/14.
 */
public class MyApplication extends Application {

    @Inject
    public DaoMaster daoMaster;
    @Inject
    public DaoSession daoSession;
    @Inject
    public SQLiteDatabase sqlDB;

    private static MyApplication instance;
    public static MyApplication getIns(){
        return  instance;
    }
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initInject();
    }

    /**
     * dagger2的依赖注入
     */
    private void initInject(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }


    /**
     * 获得app 包信息
     * @return
     */
    public PackageInfo getAppInfo(){
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = null;
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try
        {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return packInfo;
    }

}
