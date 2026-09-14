package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void insert(Student student);

    Student getbyId(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    List<Map<String, Integer>> countStudentDegreeDataList();

    List<Map<String, Object>> countStudentInClassData();
}
