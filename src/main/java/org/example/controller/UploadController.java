package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.example.utils.AliyunOSSOperator;

@Slf4j
@RestController
public class UploadController {

    /**
     * 本地磁盘存储方案
     */
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("提交的数据为：{},{},{}", name, age, file);

        file.transferTo(new File("/Users/zhengweisong/Desktop/a/"
                + UUID.randomUUID().toString()
                + file.getOriginalFilename()
                .substring(file.getOriginalFilename()
                        .lastIndexOf("."))));

        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }

}
