package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * 学员信息分页查询
     *
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> list(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage()
                , studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.list(studentQueryParam);

        PageInfo<Student> info = new PageInfo<>(studentList);

        return new PageResult<>(info.getTotal(), info.getList());
    }

    /**
     * 删除学员
     *
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 添加学员
     *
     * @param student
     */
    @Override
    public void insert(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getbyId(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);
    }
}
