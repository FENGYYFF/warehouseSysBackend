package com.wmsdemo.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

public class CodeGenerator {
    private static String scanner(String tip) {
        System.out.println("请输入" + tip + "：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (StringUtils.isNotBlank(input)) {
            return input;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir") + "/wms";

        mpg.setGlobalConfig(new GlobalConfig().setOutputDir(projectPath + "/src/main/java").setAuthor("wms").setOpen(false).setSwagger2(true).setBaseResultMap(true).setBaseColumnList(true).setServiceName("%sService"));

        mpg.setDataSource(new DataSourceConfig().setUrl("jdbc:mysql://localhost:3306/wms02?useUnicode=true&characterEncoding=UTF8&useSSL=false").setDriverName("com.mysql.jdbc.Driver").setUsername("root").setPassword("123"));

        mpg.setPackageInfo(new PackageConfig().setParent("com.wms").setEntity("entity").setMapper("mapper").setService("service").setServiceImpl("service.impl").setController("controller"));

        String templatePath = "templates/mapper.xml.ftl";
        mpg.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {
                // do nothing
            }
        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        })));

        mpg.setTemplate(new TemplateConfig().setXml(null));

        mpg.setStrategy(new StrategyConfig().setNaming(NamingStrategy.underline_to_camel).setColumnNaming(NamingStrategy.underline_to_camel).setEntityLombokModel(true).setRestControllerStyle(true).setInclude(scanner("表名，多个英文逗号分割").split(",")).setControllerMappingHyphenStyle(true));

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
