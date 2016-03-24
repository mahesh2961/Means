package utilty.means.model;

import android.content.Context;

import java.util.List;

import utilty.means.presenter.ListPresenter;
import utilty.means.utils.CustomException;

/**
 * Created by mahesh on 24/3/16.
 */
public interface ListModel
{
    public String getUrl(int id);
    public void fetchListResponse(ListPresenter presenter) throws CustomException;
    public void persistList(Context context,List<WordBean> list);
    public List<WordBean> readAllWords(Context context);

}
