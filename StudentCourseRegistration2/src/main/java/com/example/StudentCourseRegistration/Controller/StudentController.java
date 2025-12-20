package com.example.StudentCourseRegistration.Controller;
import com.example.StudentCourseRegistration.Model.Student;
import com.example.StudentCourseRegistration.Service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        if(student.getName() == null || student.getCourse() == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        if(studentService.getStudentById(student.getId()) != null){
            return new ResponseEntity<>( HttpStatus.CONFLICT);
        }
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Student>> getAllStudents(){
        Iterable<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/{id}") 
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Student student = studentService.getStudentById(id);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
