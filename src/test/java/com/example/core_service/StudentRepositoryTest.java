package com.example.core_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(20);
        student.setGroupName("Math");

        student = studentRepository.save(student);

        assertThat(student.getId()).isNotNull();

        Student foundStudent = studentRepository.findById(student.getId()).orElseThrow();

        assertThat(foundStudent.getName()).isEqualTo("John Doe");
        assertThat(foundStudent.getAge()).isEqualTo(20);
        assertThat(foundStudent.getGroupName()).isEqualTo("Math");
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setAge(20);
        student.setGroupName("Math");

        student = studentRepository.save(student);

        studentRepository.deleteById(student.getId());

        assertThat(studentRepository.findById(student.getId()).isPresent()).isFalse();
    }
}
