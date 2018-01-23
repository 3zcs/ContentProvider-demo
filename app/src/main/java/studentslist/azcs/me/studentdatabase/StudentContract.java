package studentslist.azcs.me.studentdatabase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by abdulazizalawshan on 1/22/18.
 */

public class StudentContract {

    static final String CONTENT_AUTHORITY = "studentslist.azcs.me.studentdatabase";
    //Use CONTENT_AUTHORITY to create the base of which all URIs which app's will use to contact content provider
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private StudentContract() {
    }

    static class StudentEntry implements BaseColumns {
        static final String PATH_STUDENTS = "students";
        static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STUDENTS);

        static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;

        static final String CONTENT_ITEM_TYPE = ContentResolver.ANY_CURSOR_ITEM_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;

        static final String TABLE_NAME = "student";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_COURSE = "course";
    }
}
