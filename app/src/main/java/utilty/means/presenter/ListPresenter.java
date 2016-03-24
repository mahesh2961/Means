package utilty.means.presenter;

import java.util.List;

import utilty.means.model.WordBean;
import utilty.means.network.NetworkJsonListner;
import utilty.means.utils.CustomException;

/**
 * Created by mahesh on 24/3/16.
 */
public interface ListPresenter
{
    public void onStop();
    public void onPause();
    public void onResume();
    public void onCreate();
    public void onStart();
    public List<WordBean> getList();
    public String getImageUrl(int id);
    public void onFetchSuccess(List<WordBean> list);
    public void onFetchError(CustomException error);
    public void handleRuntimeExecption(CustomException e);
    public void onClickRetry();



}
