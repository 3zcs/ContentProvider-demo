package studentslist.azcs.me.studentdatabase;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void insert() {
        long newRowId = -1;
        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry.COLUMN_NAME, "Ali");
        values.put(StudentContract.StudentEntry.COLUMN_COURSE, "Android Programming");

        Uri uri = getContentResolver().insert(StudentContract.StudentEntry.CONTENT_URI, values);
        newRowId = ContentUris.parseId(uri);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Student", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Student saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    void updateOne() {
        int count = 0;

        // New value
        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry.COLUMN_NAME, "Ahmed");

        // Which row to update, based on the name
        String selection = StudentContract.StudentEntry._ID + " = ?";
        String[] selectionArgs = {"1"};

        count = getContentResolver().update(Uri.withAppendedPath(StudentContract.StudentEntry.CONTENT_URI, "1"), values, selection, selectionArgs);
        Toast.makeText(this, "Number of student updated is : " + count, Toast.LENGTH_SHORT).show();
    }

    void updateAll() {
        int count = 0;

        // New value
        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry.COLUMN_NAME, "Omar");

        count = getContentResolver().update(StudentContract.StudentEntry.CONTENT_URI, values, null, null);
        Toast.makeText(this, "Number of student updated is : " + count, Toast.LENGTH_SHORT).show();
    }

    void deleteOne() {
        // Define 'where' part of query.
        String selection = StudentContract.StudentEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {"1"};
        String id = "1";
        getContentResolver().delete(Uri.withAppendedPath(StudentContract.StudentEntry.CONTENT_URI, id), selection, selectionArgs);

    }

    void deleteAll() {
        getContentResolver().delete(StudentContract.StudentEntry.CONTENT_URI, null, null);
    }

    void readOne() {
        Cursor cursor;
        // columns you want
        String[] projection = {
                StudentContract.StudentEntry._ID,
                StudentContract.StudentEntry.COLUMN_NAME,
                StudentContract.StudentEntry.COLUMN_COURSE
        };

        // SELECT FROM student WHERE "name" = 'Ahmed'
        String selection = StudentContract.StudentEntry._ID + " = ?";
        String[] selectionArgs = {"1"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                StudentContract.StudentEntry._ID + " DESC";

        String id = "1";
        cursor = getContentResolver().
                query(Uri.withAppendedPath(StudentContract.StudentEntry.CONTENT_URI, id), projection, selection, selectionArgs, sortOrder);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Toast.makeText(this,
                        "ID = " +
                                cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry._ID))
                                + " Name is " +
                                cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_NAME)),
                        Toast.LENGTH_SHORT)
                        .show();
            } while (cursor.moveToNext());
            cursor.close();
        }


    }

    void readAll() {
        Cursor cursor;
        cursor = getContentResolver().
                query(StudentContract.StudentEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Toast.makeText(this,
                        "ID = " +
                                cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry._ID))
                                + " Name is " +
                                cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_NAME)),
                        Toast.LENGTH_SHORT)
                        .show();
            } while (cursor.moveToNext());
            cursor.close();
        }


    }

}
