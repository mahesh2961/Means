package utilty.means.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import utilty.means.network.Network;
import utilty.means.network.NetworkJsonListner;
import utilty.means.persistance.ListDBManager;
import utilty.means.presenter.ListPresenter;
import utilty.means.utils.CustomException;
import utilty.means.utils.GsonUtils;
import utilty.means.utils.Utils;

/**
 * Created by mahesh on 24/3/16.
 */
public class ListModelImpl implements ListModel
{


    @Override
    public String getUrl(int id)
    {
        return Utils.IMAGE_BASE_URL + id + Utils.IMAGE_EXTENTION;
    }

    @Override
    public void fetchListResponse(@NonNull final ListPresenter presenter) throws CustomException
    {
        Network.getJson(Utils.WORDS_URL, new NetworkJsonListner()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.getMessage();

                presenter.onFetchError(new CustomException(Utils.MESSAGE_ERROR_CONNECTING_SERVER, 0));
            }

            @Override
            public void onResponse(JSONObject response)
            {
                List<WordBean> finalList = new ArrayList<WordBean>();
                if (response != null)
                {
                    try
                    {
                        HttpsWordResponse wordRespone = GsonUtils.fromJson(response.toString(), HttpsWordResponse.class);
                        if (Utils.isListValid(wordRespone.getWords()))
                        {
                            finalList = wordRespone.getWords();
                        }
                    }
                    catch (Exception e)
                    {
                        presenter.handleRuntimeExecption(new CustomException(Utils.MESSAGE_DATA_ERROR,0));
                        return;
                    }
                }
                presenter.onFetchSuccess(finalList);
            }
        });
    }

    @Override
    public void persistList(Context context, List<WordBean> list)
    {
        ListDBManager.getInstance(context).insertBulk(list);
    }

    @Override
    public List<WordBean> readAllWords(Context context)
    {
        return ListDBManager.getInstance(context).readAll();
    }




}
