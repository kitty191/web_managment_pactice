package org.example.service;

import org.example.pojo.JobOption;
import org.example.pojo.StudentCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Integer>> getStudentDegreeData();

    StudentCountOption getStudentCountData();
}
