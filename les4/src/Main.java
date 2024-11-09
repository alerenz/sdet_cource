import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static ArrayList<Student> students;
    public static void main(String[] args) {
        students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        readFile();
        boolean f = true;
        do {
            System.out.println("""
                    Выберите действия:
                    1 - Вывести студентов
                    2 - Удалить из списка студентов со средним баллом меньше 3
                    3 - Перевести на следующий курс со средним баллом выше 3
                    4 - Выход
                    """);
            int choose = sc.nextInt();
            switch (choose){
                case 1:
                    System.out.print("Введите курс:(если хотите вывести выпускников - 0): ");
                    int course = sc.nextInt();
                    printStudents(course);
                    break;
                case 2:
                    deleteStudents();
                    break;
                case 3:
                    transferStudent();
                    break;
                case 4:
                    f = false;
                    break;
                default:
                    System.out.println("Вы ввели не ту цифру");
            }
        }while (f);
    }

    private static void readFile(){
        int course = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;
            Student student = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("Курс ")) {
                    String[] parts = line.split(" ");
                    course = Integer.parseInt(parts[1].replace(":", ""));
                } else if (!line.isEmpty() && !line.startsWith("    ")) {
                    String[] parts = line.split(",");
                    String[] names = parts[0].split(" ");
                    String surname = names[1];
                    String name = names[2];
                    String group = parts[1].split(" ")[2].trim();
                    student = new Student(course, surname, name, group);
                    students.add(student);
                } else if(line.startsWith("    ")){
                    line = line.trim();
                    String[] parts = line.split("-");
                    String subject = parts[0];
                    int grade = Integer.parseInt(parts[1].split("")[1]);
                    assert student != null;
                    student.setEstimation(subject, grade);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printStudents(int course){
        students.stream()
                .filter(st -> st.getCourse() == course)
                .map(st -> String.format(
                        "Группа: %s, %s %s\nПредметы и оценки:\n%s",
                        st.getGroup(),
                        st.getSurname(), st.getName(),
                        st.getEstimation().entrySet().stream()
                                .map(entry -> "\t" + entry.getKey() + " - " + entry.getValue())
                                .collect(Collectors.joining("\n"))))
                .forEach(System.out::println);
    }
    private static double averageMark(Student st){
        double mark = 0;
        for(Map.Entry<String, Integer> entry : st.getEstimation().entrySet()){
            mark += entry.getValue();
        }
        return mark / st.getEstimation().size();
    }

    private static void deleteStudents(){
        int count = 0;
        for(int i = 0; i < students.size(); i++){
            if(averageMark(students.get(i)) < 3){
                students.remove(i);
                i--;
                count++;
            }
        }
        System.out.println("Удаленно студентов: " + count);
    }

    private static void transferStudent(){
        int count = 0;
        int countGraduates = 0;
        for(Student st : students){
            if(averageMark(st) >= 3){
                if(st.getCourse() == 4){
                    st.setCourse(0);
                    st.setGroup("Выпускник");
                    countGraduates++;
                }else {
                    st.setCourse(st.getCourse() + 1);
                    char [] chars = st.getGroup().toCharArray();
                    int n = chars[6] + 1;
                    chars[6] = (char) n;
                    StringBuilder sb = new StringBuilder();
                    for (char c : chars){
                        sb.append(c);
                    }
                    st.setGroup(sb.toString());
                    count++;
                }
            }
        }
        System.out.println("Выпукников: " + countGraduates);
        System.out.println("Успешно переведенно: " + count);
    }
}