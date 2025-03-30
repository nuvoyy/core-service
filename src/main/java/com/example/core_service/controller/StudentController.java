package com.example.core_service.controller;

import com.example.core_service.entity.Student;
import com.example.core_service.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students") // Исправили URL
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Student create(@RequestBody Student student) { // Исправили product → student
        return repository.save(student);
    }

    @GetMapping
    public List<Student> read() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) { // Исправили product → student
        Student existing = repository.findById(id).orElseThrow();
        existing.setName(student.getName());
        existing.setAge(student.getAge()); // Исправили price → age
        existing.setGroupName(student.getGroupName()); // Добавили groupName
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
