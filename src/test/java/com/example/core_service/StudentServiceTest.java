package com.example.core_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setAge(20);
        student.setGroupName("Math");

        when(studentRepository.save(student)).thenReturn(student);

        studentService.createStudent(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(new Student(1L, "John Doe", 20, "Math"));
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        verify(studentRepository, times(1)).findAll();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testUpdateStudent() {
        Student existingStudent = new Student(1L, "John Doe", 20, "Math");
        Student updatedStudent = new Student(1L, "Jane Doe", 21, "Science");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student result = studentService.updateStudent(1L, updatedStudent);

        assertEquals("Jane Doe", result.getName());
        assertEquals(21, result.getAge());
        assertEquals("Science", result.getGroupName());

        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(any(Student.class));
    }



    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }
}
