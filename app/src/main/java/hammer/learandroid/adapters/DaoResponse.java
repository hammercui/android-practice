package hammer.learandroid.adapters;

/** dao的response接口
 * Created by hammer on 2016/7/28.
 */
public interface DaoResponse<T> {
    public void onSuccess(T t);
    public void onFial(String msg);
}
