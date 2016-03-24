package utilty.means.view;

import java.util.List;

import utilty.means.model.WordBean;

/**
 * Created by mahesh on 24/3/16.
 */
public interface ListView
{

    public void initializeView();

    public void displayProgress(int visibility);

    public void displayRetry(int visibility);


    public void setListAdapter(List<WordBean> listItems);

    public void refereshAdapter(List<WordBean> listItems);

}
