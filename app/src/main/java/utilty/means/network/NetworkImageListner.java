package utilty.means.network;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by mahesh on 24/3/16.
 */
public interface NetworkImageListner
{
    public void onErrorResponse(VolleyError error);
    public void onResponse(ImageLoader.ImageContainer response, boolean arg1);
}
