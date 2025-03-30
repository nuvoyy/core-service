package com.example.core_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.core_service.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}
