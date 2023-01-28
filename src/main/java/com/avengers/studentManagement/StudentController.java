package com.avengers.studentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    //get information using request parameter
    @GetMapping("/get_student")
    public ResponseEntity getStudent(@RequestParam("admnNo") int admnNo) {
        Student student = studentService.getStudent(admnNo);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    //get information using path variable parameter
//    @GetMapping("/get_student/{admnNo}")
//    public Student getStudent(@PathVariable int admnNo) {
//        return db.get(admnNo);
//    }

    //adding the information
    @PostMapping("/add_student")
    public ResponseEntity addStudent(@RequestBody Student student) {
//        int admnNo = student.getAdmnNo();
//        db.put(admnNo, student);
//        return "Student added Successfully";
        String response = studentService.addStudent(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Delete Student
    @DeleteMapping("/delete_student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int id) {
//        if(!db.containsKey(id)) {
//            return "Invalid Id";
//        }
//        db.remove(id);
//        return "Student removed Successfully";
        String response = studentService.deleteStudent(id);
        if(response.equals("Invalid Id")) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    //Update Student
    @PutMapping("/update_student")
    public ResponseEntity updateStudent(@RequestParam("id") int id, @RequestParam("age") int age) {
//        if(!db.containsKey(id)) {
//            return "Invalid Id";
//        }
//        db.get(id).setAge((age));
////        Student student = db.get(id);
////        student.setAge((age));
////        db.put(id, student); // It is not necessary
//
//        return "Age updated successfully";
        String response = studentService.updateStudent(id, age);
        if(response == null) {
            return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Updated", HttpStatus.ACCEPTED);
    }
}
