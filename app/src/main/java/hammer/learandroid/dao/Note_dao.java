package hammer.learandroid.dao;

import android.text.TextUtils;

import com.hammer.example.DaoSession;
import com.hammer.example.Note;
import com.hammer.example.NoteDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import hammer.learandroid.adapters.DaoResponse;
import hammer.learandroid.util.LogUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hammer on 2016/6/15.
 */
public interface Note_dao {
    /**
     * 新增一个note
     * @param note
     */
    public void  add(Note note,DaoResponse<List<Note>> daoResponse);

    /**
     * 根据条件查询note
     * @param title
     * @param daoResponse
     */
    public void  search(String title, DaoResponse<List<Note>> daoResponse);

    public List<Note> getAllNotes();

}
