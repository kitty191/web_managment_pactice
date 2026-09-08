package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.javassist.ClassMap;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    private final ClazzMapper clazzMapper;

    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
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

            if (classBeginDate.isAfter(now)) {
                CLASS.setStatus("未开班");
            } else if (classBeginDate.isBefore(now) && classendDate.isAfter(now)) {
                CLASS.setStatus("在读中");
            } else {
                CLASS.setStatus("已结课");
            }

        });

        PageInfo<Clazz> info = new PageInfo<>(clazzList);

        return new PageResult<>(
                info.getTotal(), info.getList()
        );
    }
}
