package utilty.means.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by mahesh on 24/3/16.
 */
public class Utils
{
    public static final String IMAGE_BASE_URL = "http://appsculture.com/vocab/images/";

    public static final String MESSAGE_ERROR_CONNECTING_SERVER = "Error connecting server,please try again later..";

    public static final String MESSAGE_DATA_ERROR = "Data error";


    public static final String WORDS_URL = "http://appsculture.com/vocab/words.json";

    public static final String IMAGE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "means" + File.separator;


    public static final String IMAGE_EXTENTION = ".png";

    public static boolean isValidString(String str)
    {
        return (str != null && str.length() > 0);
    }

    public static boolean isListValid(List list)
    {
        return (list != null && list.size() > 0);
    }



    public static void saveWordBitMap(Bitmap bitmap, int id)
    {
        String fileName = IMAGE_FOLDER + id;

        File folder = new File(IMAGE_FOLDER);
        if (!folder.exists())
        {
            folder.mkdirs();
        }

        FileOutputStream outStream;
        try
        {
            outStream = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Log.i(Utils.class.getName(),"Image saved");

    }

    public static final Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return ContextCompat.getDrawable(context, id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }


    public static void configureCardView(CardView cardView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP ) {
            params.setMargins(5, 5, 5, 5);
        } else {
            cardView.setMaxCardElevation(5);
        }
        cardView.setCardElevation(5);
        cardView.setLayoutParams(params);
    }

}
