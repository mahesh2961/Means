package utilty.means.network;

import android.app.ProgressDialog;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import utilty.means.Application.MeansApplications;
import utilty.means.utils.CustomException;

/**
 * Created by mahesh on 24/3/16.
 */
public class Network
{
    public static void getJson(@NonNull String url, @NonNull final NetworkJsonListner listner) throws CustomException
    {

        if(url==null || listner==null)
        {
            throw new CustomException("Parameters cannot be null",5);
        }
        String tag_json_obj = "json_obj_req:"+ System.currentTimeMillis();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        listner.onResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                listner.onErrorResponse(error);
            }
        });

        MeansApplications.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public static void getImage(@NonNull String url, @NonNull final NetworkImageListner listner)
    {
        ImageLoader imageLoader = MeansApplications.getInstance().getImageLoader();

        imageLoader.get(url, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                listner.onErrorResponse(error);
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    listner.onResponse(response,arg1);
                }
            }
        });

    }
}
