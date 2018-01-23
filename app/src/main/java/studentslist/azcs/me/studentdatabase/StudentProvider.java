package studentslist.azcs.me.studentdatabase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by abdulazizalawshan on 1/22/18.
 */

public class StudentProvider extends ContentProvider {

    private static final int STUDENT = 10;
    private static final int STUDENT_ID = 11;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(StudentContract.CONTENT_AUTHORITY, StudentContract.StudentEntry.PATH_STUDENTS, STUDENT);
        uriMatcher.addURI(StudentContract.CONTENT_AUTHORITY, StudentContract.StudentEntry.PATH_STUDENTS + "/#", STUDENT_ID);
    }

    StudentHelper helper;

    @Override
    public boolean onCreate() {
        helper = new StudentHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;

        int match = uriMatcher.match(uri);

        switch (match) {
            case STUDENT:
                cursor = database.query(StudentContract.StudentEntry.TABLE_NAME,
                        null, null, null, null, null, null);
                break;

            case STUDENT_ID:
                cursor = database.query(StudentContract.StudentEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENT:
                return StudentContract.StudentEntry.CONTENT_LIST_TYPE;

            case STUDENT_ID:
                return StudentContract.StudentEntry.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalStateException("Unknown Uri " + uri + " with match" + match);
        }
        //if we don't implement getType just throw this exception
        //throw new UnsupportedOperationException("getType is not implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) != STUDENT)
            throw new IllegalArgumentException("Unsupported Uri for insertion " + uri);

        SQLiteDatabase database = helper.getWritableDatabase();
        long rowId = database.insert(StudentContract.StudentEntry.TABLE_NAME, null, values);
        if (rowId != -1)
            //content://studentslist.azcs.me.studentdatabase/students/1
            return ContentUris.withAppendedId(StudentContract.StudentEntry.CONTENT_URI, rowId);

        //if there is a problem
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int deletedRows = 0;
        switch (match) {
            case STUDENT:
                //if you want to delete all rows send selection and selectionArgs as null
                deletedRows = database.delete(StudentContract.StudentEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case STUDENT_ID:
                deletedRows = database.delete(StudentContract.StudentEntry.TABLE_NAME, selection, selectionArgs);
                break;
        }
        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int effectedRows = 0;
        switch (match) {
            case STUDENT:
                //if you want to update all rows send selection and selectionArgs as null
                effectedRows = database.update(StudentContract.StudentEntry.TABLE_NAME, values, selection, selectionArgs);
                break;

            case STUDENT_ID:
                effectedRows = database.update(StudentContract.StudentEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
        return effectedRows;
    }
}
