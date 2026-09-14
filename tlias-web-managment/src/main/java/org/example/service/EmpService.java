package org.example.service;

import org.example.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    EmpQuery getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}