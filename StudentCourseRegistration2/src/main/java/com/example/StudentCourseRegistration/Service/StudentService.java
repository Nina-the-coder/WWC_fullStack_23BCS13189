package com.example.StudentCourseRegistration.Service;
import com.example.StudentCourseRegistration.Model.Student;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public Student addStudent(Student student){
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents(){
        return students;
    }

    public Student getStudentById(int id){
        return students.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public boolean deleteStudent(int id){
        return students.removeIf(e -> e.getId() == id);
    }
}
