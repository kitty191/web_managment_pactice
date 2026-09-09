package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> list(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void insert(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
