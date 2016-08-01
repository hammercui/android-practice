package hammer.learandroid.dao;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.Buffer;

import de.greenrobot.dao.DbUtils;
import hammer.learandroid.util.LogUtil;

/**
 * sqlite数据库管理类
 */
public class SqliteManager {

    /**
     * 拷贝assets中的db文件到apk目录
     */
    public void copyAssetsDbToApkDb(Context context, String assetDbName, String apkDbPath, String dbName, boolean refresh){
        boolean b = false;
        File f = new File(apkDbPath);
        //如果apk默认的db数据库文件夹不存在，新建这个文件夹
        if (!f.exists()){
            f.mkdirs();
        }
        //database完整路径
        String databaseFileName = apkDbPath + "/" +dbName;
       // File dbFile = new File(databaseFileName);
        b = isDbFileExists(new File(databaseFileName),refresh);
        //不存在，如要覆盖 ,后期考虑加密
        if (!b){
            try {
                InputStream is = context.getAssets().open(assetDbName);
                FileOutputStream fos = new FileOutputStream(databaseFileName);
                byte[] buffer = new byte[1024*2];
                int size = 0;
                while ((size = is.read(buffer,0,buffer.length))>0){
                    fos.write(buffer,0,size);
                }
                fos.flush();
                fos.close();
                LogUtil.Debug(databaseFileName+"数据库拷贝成功");
            }
            catch (Exception e){
                LogUtil.Debug("数据库拷贝："+e.toString());
            }
        }
    }

    /**
     * 判断dbfile是否存在，以及是否刷新
     * @return
     */
    public boolean isDbFileExists(File file,boolean refresh){
        if (!file.exists()){
            return false;
        }
        else{
            return  !refresh;
        }

    }


}
