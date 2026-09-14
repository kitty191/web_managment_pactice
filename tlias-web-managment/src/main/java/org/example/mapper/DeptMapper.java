package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门数据
     *
     * @return
     */
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    List<Dept> findAll();


    /**
     * 根据ID删除数据
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 增添部门数据
     **/
    @Insert("insert into dept (name, create_time, update_time) " +
            "VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查询部门
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
