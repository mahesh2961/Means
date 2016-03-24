package utilty.means.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import utilty.means.model.HttpsWordResponse;
import utilty.means.model.ListModel;
import utilty.means.model.ListModelImpl;
import utilty.means.model.WordBean;
import utilty.means.utils.ApplicationService;
import utilty.means.utils.CustomException;
import utilty.means.utils.GsonUtils;
import utilty.means.utils.IApplicationService;
import utilty.means.utils.Result;
import utilty.means.utils.Utils;
import utilty.means.view.ListView;

/**
 * Created by mahesh on 24/3/16.
 */
public class ListPresenterImpl implements ListPresenter
{

    Context mContext;
    ListView mListview;
    List<WordBean> mItemList;
    ListModel mlistModel;

    public ListPresenterImpl(Context mContext, ListView listView)
    {
        this.mContext = mContext;
        mListview=listView;
        mlistModel=new ListModelImpl();
    }


    @Override
    public void onStop()
    {

    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onResume()
    {

    }

    @Override
    public void onCreate()
    {
        mListview.initializeView();
        initiateProcess();


       /* mItemList=fetchListResponse();
        mListview.setListAdapter(mItemList);*/
    }

    private void initiateProcess()
    {
        mListview.displayRetry(View.INVISIBLE);
        mListview.displayProgress(View.VISIBLE);
        try
        {
            mItemList=mlistModel.readAllWords(mContext);
            if(!Utils.isListValid(mItemList))
            {
                mlistModel.fetchListResponse(this);
            }
            else
            {
                mListview.displayProgress(View.INVISIBLE);
                mListview.setListAdapter(mItemList);

            }

        } catch (CustomException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart()
    {

    }

    @Override
    public List<WordBean> getList()
    {

        return null;


    }

    @Override
    public String getImageUrl(int id)
    {
        return mlistModel.getUrl(id);
    }

    @Override
    public void onFetchSuccess(List<WordBean> list)
    {
        if(Utils.isListValid(list))
        {
            mlistModel.persistList(mContext,list);
            mListview.displayProgress(View.GONE);
            mListview.setListAdapter(mlistModel.readAllWords(mContext));
        }
        else
        {
            mListview.displayProgress(View.GONE);
            Toast.makeText(mContext,"No words available",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFetchError(CustomException error)
    {
        mListview.displayProgress(View.GONE);
        mListview.displayRetry(View.VISIBLE);

        Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleRuntimeExecption(CustomException e)
    {
        if(e.getMessage().equals(Utils.MESSAGE_DATA_ERROR))
        {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            mListview.displayProgress(View.GONE);
            mListview.displayRetry(View.VISIBLE);
        }

    }

    @Override
    public void onClickRetry()
    {
        initiateProcess();
    }

}
