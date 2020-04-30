package com.dnp.mybaits.baomidou;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具， 需要配置的参数说明
 * 1、数据库参数：DB_USER_NAME、DB_PASSWORD、DB_URL
 * 2、文件输出路径：OUT_PUT_DIR
 * 配置的绝对路径，直接生成到你的项目里面。注意，到java那成目录就可以了，如：G:\oauth2-learning-project\src\main\java
 * 3、配置模块：MODULE
 * 如果项目要分多个module，就可以配置模块参数：MODULE
 * 4、配置要生成的表：INCLUED_TABLE
 * 不配置就生成全部表，配置了就生成配置的表
 * 5、配置包名：PACKAGE_NAME
 * 注意：配置报名的时候集合加上模块的名字
 * 6、vo对象的包名配置：VO_PACKAGE_NAME
 *
 * @author: 华仔
 * @date: 2020/4/30
 */
public class MysqlGenerator {
    /*作者*/
    private static String AUTHOR = "huazai";
    /*数据库用户名称*/
    private static String DB_USER_NAME = "root";
    /*数据库密码*/
    private static String DB_PASSWORD = "123456";
    /*数据库url*/
    private static String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8";
    /*数据库driver*/
    private static String DB_DRIVER = "com.mysql.jdbc.Driver";

    // 注意:绝对路径的写法，到java目录就可以了：如：G:\my-git-project\code-generate-system\demo-code\src\main\java
    private static String OUT_PUT_DIR = "G:\\my-git-project\\code-generate-system\\code-generate-baomidou\\src\\main\\java";
    /* 自己定义的基本的操作生成到那个模块，没有就填空*/
//    moe.cnkirito.security.oauth2.code.modular.model
    private static String MODULE = "module";
    /*自定义要生成的信息表,不传生成所有表*/
    private static String[] INCLUED_TABLE = {};

    /*包名,controller、entity、dao、service到的包*/
    private static String PACKAGE_NAME =  "com.dnp.mybaits.baomidou.module";

    /*PageVo分页实体放置的目录*/
    private static String VO_PACKAGE_NAME = "com.dnp.mybaits.baomidou.vo";


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUT_PUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setFileOverride(true);//是否覆盖
        gc.setBaseResultMap(true);
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASSWORD);
        autoGenerator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        模块名称根据自己的项目自己定义
        if (StringUtils.isNotEmpty(MODULE)) {
            pc.setModuleName(MODULE);
        }
//        pc.setParent("com.baomidou.mybatisplus.samples.generator");
        pc.setParent(PACKAGE_NAME);
        autoGenerator.setPackageInfo(pc);

        // 自定义配置 // 注入自定义配置，可以在 VM ft 中使用 cfg.abc 设置的值
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("packageName", PACKAGE_NAME);
                map.put("pageVo", VO_PACKAGE_NAME);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return OUT_PUT_DIR.replace("java", "resources/mapper/") + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));

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
//        strategy.setSuperEntityColumns("id");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        autoGenerator.execute();
    }

}
