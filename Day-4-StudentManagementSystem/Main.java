import java.util.*;

class Student{
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId(){
        return id;
    }

    public int getMarks(){
        return marks;
    }

    public String getrole(){
        return "Undergrad";
    }

    @Override
    public String toString(){
        return id + " - " + name + " (" + marks + ") " + getrole();
    }
}

class GraduateStudent extends Student{
    private String area;

    public GraduateStudent(String id, String name, int marks, String area){
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getrole(){
        return "Graduate (" + area + ")";
    }
}

class HonourStudent extends Student{
    private int bonusMarks;

    public HonourStudent(String id, String name, int marks, int bonusMarks){
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public String getrole(){
        return "Honour (bonus marks - " + bonusMarks + " marks)";
    }

    public String getTopper(List<Student> students){
        int maxMarks = 0;
        String topperId = "";
        for(Student s : students){
            if(s.getMarks() > maxMarks){
                topperId = s.getId();
                maxMarks = s.getMarks();
            }
        }
        return topperId;
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj){
        data.put(id, obj);
    }

    public T find(String id){
        return data.get(id);
    }

    public void delete(String id){
        data.remove(id);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Nina", 85));
        list.add(new Student("S2", "Charlotte", 78));
        list.add(new Student("S3", "Kevin", 92));
        
        list.add(new GraduateStudent("G1", "BoB", 90, "Computer Science"));

        Repository<Student> repo = new Repository<>();
        for(Student s : list){
            repo.save(s.getId(), s);
        }

        System.out.println("All Students:");
        list.forEach(System.out :: println);

        System.out.println("\nLOOKUP S2:");
        Student s = repo.find("S2");
        System.out.println(s != null ? s : "Student not found");

        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            if(student.getMarks() < 80){
                iterator.remove();
                repo.delete(student.getId());
            }
        }

        System.out.println("\nAfter removing students with marks < 80:");
        list.forEach(System.out :: println);

        HonourStudent honourStudent = new HonourStudent("H1", "Deepanshu", 95, 5);
        list.add(honourStudent);
        repo.save(honourStudent.getId(), honourStudent);
        System.out.println("\nAfter adding Honour Student:");
        list.forEach(System.out :: println);

        String topperId = honourStudent.getTopper(list);
        System.out.println("\nTopper ID: " + topperId);
    }
}