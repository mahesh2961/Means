package utilty.means.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahesh on 24/3/16.
 */
public class DBHelper extends SQLiteOpenHelper
{

    private static DBHelper sInstance;

    private static final String DATABASE_NAME = "database_name";
    private static final String DATABASE_TABLE = "table_name";
    private static final int DATABASE_VERSION = 1;

    public static final String TAB_WORDS = "WORDS";
    public static final String KEY_ID = "id";
    public static final String KEY_WORD = "word";
    public static final String KEY_VARIANT = "variant";
    public static final String KEY_MEANING = "meaning";
    public static final String KEY_RATIO = "ratio";



    public static synchronized DBHelper getInstance(Context context)
    {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null)
        {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    private DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
         String CREATE_TABLE_WORD = "CREATE TABLE " + TAB_WORDS
                + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                + KEY_MEANING + " TEXT,"+KEY_RATIO+" REAL,"+KEY_VARIANT+" INTEGER"+")";

        sqLiteDatabase.execSQL(CREATE_TABLE_WORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
