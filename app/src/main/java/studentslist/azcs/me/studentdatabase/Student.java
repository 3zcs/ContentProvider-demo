package studentslist.azcs.me.studentdatabase;

/**
 * Created by abdulazizalawshan on 1/22/18.
 */

public class Student {
    private String name;
    private String course;
    private String id;

    public Student(String name, String course, String id) {
        this.name = name;
        this.course = course;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
