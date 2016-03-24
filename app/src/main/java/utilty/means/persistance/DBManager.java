package utilty.means.persistance;

import java.util.List;

import utilty.means.model.WordBean;

/**
 * Created by mahesh on 24/3/16.
 */
public interface DBManager
{
    public void insertBulk(List<WordBean> beans);
    public List<WordBean> readAll();

}
