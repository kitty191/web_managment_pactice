package org.example.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private final EmpExprMapper empExprmapper;

    public EmpServiceImpl(EmpMapper empMapper, EmpExprMapper empExprmapper) {
        this.empMapper = empMapper;
        this.empExprmapper = empExprmapper;
    }


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParam);

        PageInfo<Emp> info = new PageInfo<>(empList);

        return new PageResult<>(info.getTotal(), info.getList());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();

        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprmapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);

        empExprmapper.deleteByEmpIds(ids);
    }

    /**
     * 查询员工信息及工作经历信息
     */
    @Override
    public EmpQuery getInfo(Integer id) {
        /*查询员工信息*/
        EmpQuery empQuery = empMapper.getById(id);

        /*查询工作经历信息*/
        List<EmpExpr> exprList = empExprmapper.getByEmpId(id);

        empQuery.setExprList(exprList);

        return empQuery;
    }

    /**
     * 修改员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        empExprmapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprmapper.insertBatch(exprList);
        }
    }


}
