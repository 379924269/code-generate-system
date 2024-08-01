package com.huazai.springboot3sagen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义代码生成，<a href="https://baomidou.com/reference/new-code-generator-configuration/">MyBatis-Plus 官网文档</a>
 *
 * @author 华仔
 * @date 2024/7/31
 */
public class AutoGenerateCode {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/jetlinks?serverTimezone=Asia/Shanghai", "root", "derper123")
                .globalConfig(builder -> {
                    builder.author("huazai") // 设置作者
                            .enableSpringdoc()
                            //.outputDir("src\\main\\java"); // 输出目录
                            .outputDir("G:\\myself_written_test_project\\sa-test\\hos-project-test\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.huazai.hosprojecttest") // 设置父包名
                            .entity("model") // 设置实体类包名
                            .mapper("dao") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml("mappers")
                            .pathInfo(xmlOutPutFile()); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("s_", "dev_") // 增加过滤表前缀
                            .addInclude("s_role", "s_user") // 设置需要生成的表名, 如果不设置就返回所有表
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .idType(IdType.ASSIGN_ID)
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .controllerBuilder().template("templates/controller1.java.vm") // 启用 REST 风格
                            .enableRestStyle();
                })
                .injectionConfig((stringStringFunction, builder) -> {
                    List<CustomFile> customFiles = new ArrayList<>();
                    //customFiles.add(new CustomFile.Builder().fileName("DTO.java").templatePath("/templates/dto.java.vm").packageName("dto").build());
                    customFiles.add(new CustomFile.Builder().fileName("VO.java").templatePath("/templates/vo.java.vm").packageName("vo").build());
                    builder.customFile(customFiles).customMap(voPath());
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }

    private static Map<OutputFile, String> xmlOutPutFile() {
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, "G:\\myself_written_test_project\\sa-test\\hos-project-test\\src/main/resources/mapper");
        return pathInfo;
    }

    private static Map<String, Object> voPath() {
        Map<String, Object> pathInfo = new HashMap<>();
        pathInfo.put("voPath", "com.huazai.hosprojecttest.vo");
        return pathInfo;
    }




}
