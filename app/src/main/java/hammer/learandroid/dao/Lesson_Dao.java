package hammer.learandroid.dao;

import com.hammer.example.Lesson;

import java.util.List;

import hammer.learandroid.adapters.DaoResponse;

/**
 *
 * Created by hammer on 2016/8/1.
 */
public interface Lesson_Dao {

    /**
     * 查询所有
     * @param response
     */
    public void searchAll(DaoResponse<List<Lesson>> response);
}
