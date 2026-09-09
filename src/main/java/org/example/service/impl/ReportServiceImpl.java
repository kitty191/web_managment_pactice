package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.JobOption;
import org.example.pojo.StudentCountOption;
import org.example.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final EmpMapper empMapper;
    private final StudentMapper studentMapper;

    public ReportServiceImpl(EmpMapper empMapper, StudentMapper studentMapper) {
        this.empMapper = empMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobDataList();

        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderDataList();
    }

    /**
     * 统计学员的学历信息
     *
     * @return
     */
    @Override
    public List<Map<String, Integer>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeDataList();
    }

    /**
     * 统计班级人数信息
     *
     * @return
     */
    @Override
    public StudentCountOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countStudentInClassData();

        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new StudentCountOption(clazzList,dataList);


    }
}
