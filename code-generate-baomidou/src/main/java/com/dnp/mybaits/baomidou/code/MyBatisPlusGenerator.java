package com.dnp.mybaits.baomidou.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 自动生成代码， 我仿照的是最新mybaits的生成代码写的。 最新的用的是FreemarkerTemplateEngine（ftl）， 我自己
 * 的自定义了controller
 *
 * @Author: 华仔
 * @Date: 2019/7/2
 */
public class MyBatisPlusGenerator {
    /*数据库用户名称*/
    private static String DB_USER_NAME = "root";
    /*数据库密码*/
    private static String DB_PASSWORD = "123456";
    /*数据库url*/
    private static String DB_URL = "jdbc:mysql://localhost:3306/ms_ptt?useUnicode=true&useSSL=false&characterEncoding=utf8";
    /*数据库driver*/
    private static String DB_DRIVER = "com.mysql.jdbc.Driver";

    /*作者*/
    private static String AUTHOR = "huazai";
    /*自己定义的包名称*/
    private static String PK_NAME = "com.dnp.ptt";
    /* 自己定义的基本的操作生成到那个模块，没有就填空*/
    private static String MODULE = "modular";

    /*注意写绝对路径：如：G:\my-git-project\code-generate-system\demo-code\src\main\java*/
    private static String OUT_PUT_DIR = "G:\\my-git-project\\code-generate-system\\demo-code\\src\\main\\java";

    /*自定义要生成的信息表，不传值就不生成所有表*/
    private static String[] INCLUED_TABLE = {"users", "manager", "users_role", "manager_role", "resource", "role_resource"};


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUT_PUT_DIR);//这里写你自己的java目录
        gc.setAuthor(AUTHOR); // 作者
        gc.setFileOverride(false);//是否覆盖
        gc.setOpen(false);
        gc.setSwagger2(true); //调用swagger2
        gc.setBaseResultMap(true);// XML ResultMap
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
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

//         自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("pageVo", "com.dnp.ptt.vo");
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
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
