package org.example.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.pojo.Emp;
import org.example.pojo.EmpQuery;
import org.example.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    public List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    EmpQuery getById(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobDataList();

    List<Map<String, Object>> countEmpGenderDataList();

    Emp selectByUsernameAndPassword(Emp emp);
}
