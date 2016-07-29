package com.hammer.example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 继承自 DaoMaster.OpenHelper
 * Created by hammer on 2016/7/29.
 */
public class HMROpenHelper extends DaoMaster.OpenHelper {

    public HMROpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //操作数据库的更新
        MigrationHelper.migrate(db,NoteDao.class,LessonDao.class,ZhangDao.class);
    }

}
