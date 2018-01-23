package studentslist.azcs.me.studentdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abdulazizalawshan on 1/22/18.
 */

public class StudentHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_students.db";
    public static final int DATABASE_VERSION = 1;

    public StudentHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + StudentContract.StudentEntry.TABLE_NAME + " (" +
                StudentContract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                StudentContract.StudentEntry.COLUMN_NAME + " TEXT," +
                StudentContract.StudentEntry.COLUMN_COURSE + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StudentContract.StudentEntry.TABLE_NAME);
        onCreate(db);
    }
}
