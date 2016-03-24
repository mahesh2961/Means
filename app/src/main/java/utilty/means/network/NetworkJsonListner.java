package utilty.means.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by mahesh on 24/3/16.
 */
public interface NetworkJsonListner
{
    public void onErrorResponse(VolleyError error);
    public void onResponse(JSONObject response);
}
