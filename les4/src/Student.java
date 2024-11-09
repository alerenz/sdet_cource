import java.util.HashMap;
import java.util.Map;

public class Student {
    private int course;
    private final String surname;
    private final String name;
    private String group;
    private final Map<String,Integer> estimation;

    public Student(int course, String surname, String name, String group){
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.group = group;
        estimation = new HashMap<>();
    }
    public int getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Map<String, Integer> getEstimation() {
        return estimation;
    }
    public void setEstimation(String nameSubject, Integer est) {
        estimation.put(nameSubject, est);
    }


}
