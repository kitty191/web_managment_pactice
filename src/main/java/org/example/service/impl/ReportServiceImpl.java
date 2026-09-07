package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final EmpMapper empMapper;

    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
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
}
