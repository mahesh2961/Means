package utilty.means.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import utilty.means.model.WordBean;

/**
 * Created by mahesh on 24/3/16.
 */
public class ListDBManager implements DBManager
{
    SQLiteDatabase dbHelper;
    Context mContext;
    private static ListDBManager mInstance;

    private ListDBManager(Context mContext)
    {
        this.mContext = mContext;
        dbHelper = DBHelper.getInstance(mContext).getWritableDatabase();
    }

    public static ListDBManager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new ListDBManager(context);
        }
        return mInstance;
    }

    @Override
    public void insertBulk(List<WordBean> beans)
    {
        try
        {
            dbHelper.beginTransaction();

            for (int i = 0; i < beans.size(); i++)
            {

                WordBean item = beans.get(i);
                if(item.getRatio()>-1)
                {
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.KEY_ID, beans.get(i).getId());
                    values.put(DBHelper.KEY_WORD, beans.get(i).getWord());
                    values.put(DBHelper.KEY_MEANING, beans.get(i).getMeaning());
                    values.put(DBHelper.KEY_RATIO, beans.get(i).getRatio());
                    values.put(DBHelper.KEY_VARIANT, beans.get(i).getVariant());
                    dbHelper.insert(DBHelper.TAB_WORDS, null, values);
                }
            }

            dbHelper.setTransactionSuccessful();

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            dbHelper.endTransaction();
        }

    }

    @Override
    public List<WordBean> readAll()
    {

        List<WordBean> beans = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DBHelper.TAB_WORDS;
        Cursor cursor = null;

        try
        {
            cursor = dbHelper.rawQuery(selectQuery, null);
            if (cursor.moveToFirst())
            {
                do
                {
                    // get the data into array, or class variable
                    WordBean wordBean = new WordBean();
                    wordBean.setWord(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_WORD)));
                    wordBean.setMeaning(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_MEANING)));
                    wordBean.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)));
                    wordBean.setRatio(cursor.getDouble(cursor.getColumnIndex(DBHelper.KEY_RATIO)));
                    wordBean.setVariant(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_VARIANT)));
                    beans.add(wordBean);

                } while (cursor.moveToNext());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            cursor.close();
        }

        return beans;
    }


}
