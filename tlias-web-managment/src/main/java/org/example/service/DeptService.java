package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询部门的所有数据
     *
     * @return
     */
    List<Dept> findAll();

    /**
     * 根据ID删除数据
     */
    void deleteById(Integer id);

    /**
     * 增添数据
     **/
    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
