package com.dnp.mybaits.baomidou.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * mysql 代码生成器演示例子 这个用的是FreemarkerTemplateEngine（ftl），
 * 所以就不会加载我自定义的controller.java.vm， 生成代码生成器原生的。
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {
    /*作者*/
    private static String AUTHOR = "huazai";
    /*数据库用户名称*/
    private static String DB_USER_NAME = "root";
    /*数据库密码*/
    private static String DB_PASSWORD = "123456";
    /*数据库url*/
    private static String DB_URL = "jdbc:mysql://localhost:3306/ms_ptt?useUnicode=true&useSSL=false&characterEncoding=utf8";
    /*数据库driver*/
    private static String DB_DRIVER = "com.mysql.jdbc.Driver";

    /*注意写绝对路径：如：G:\my-git-project\code-generate-system\demo-code\src\main\java*/
    private static String OUT_PUT_DIR = "G:\\my-git-project\\code-generate-system\\demo-code\\src\\main\\java";
    /*自己定义的包名称*/
    private static String PK_NAME = "com.dnp.ptt";
    /* 自己定义的基本的操作生成到那个模块，没有就填空*/
    private static String MODULE = "modular";
    /*自定义要生成的信息表*/
    private static String[] INCLUED_TABLE = {"users", "manager", "users_role", "manager_role", "resource", "role_resource"};


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUT_PUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        模块名称根据自己的项目自己定义
        pc.setModuleName(MODULE);
//        pc.setParent("com.baomidou.mybatisplus.samples.generator");
        pc.setParent(PK_NAME);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return OUT_PUT_DIR.replace("java", "resources/mapper/") + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
//        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        if (INCLUED_TABLE.length != 0) {
            strategy.setInclude(INCLUED_TABLE); // 需要生成的表,注释就生成所有
        }
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
