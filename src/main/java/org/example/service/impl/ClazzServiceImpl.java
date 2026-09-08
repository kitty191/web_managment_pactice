package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.javassist.ClassMap;
import org.example.mapper.ClazzMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;
    private final EmpMapper empMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper, EmpMapper empMapper) {
        this.clazzMapper = clazzMapper;
        this.empMapper = empMapper;
    }

    /**
     * 计算班级开课状态计算器
     *
     * @param CLASS
     * @param classBeginDate
     * @param now
     * @param classendDate
     */
    private static void clazzStatusComputer(Clazz CLASS, LocalDate classBeginDate
            , LocalDate now, LocalDate classendDate) {
        if (classBeginDate.isAfter(now)) {
            CLASS.setStatus("未开班");
        } else if (classBeginDate.isBefore(now) || classBeginDate.isEqual(now)
                && classendDate.isEqual(now) || classendDate.isAfter(now)) {
            CLASS.setStatus("在读中");
        } else {
            CLASS.setStatus("已结课");
        }
    }

    /**
     * 班级条件分页查询
     *
     * @param clazzQueryParam
     * @return
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        LocalDate now = LocalDate.now();

        /**
         * 判断班级在读状况
         */
        clazzList.forEach(CLASS -> {
            LocalDate classBeginDate = CLASS.getBeginDate();
            LocalDate classendDate = CLASS.getEndDate();

            clazzStatusComputer(CLASS, classBeginDate, now, classendDate);

        });

        PageInfo<Clazz> info = new PageInfo<>(clazzList);

        return new PageResult<>(
                info.getTotal(), info.getList()
        );
    }


    /**
     * 根据id删除班级
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    /**
     * 添加班级
     *
     * @param clazz
     */
    @Override
    public void insert(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查找班级
     *
     * @param id
     * @return
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 修改班级信息
     *
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.updateById(clazz);
    }
}
