package cn.hll520.linling.core.code.generation;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 自动代码生成起
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-31-22:12
 * @since 2021-01-31-22:12
 */
@SuppressWarnings("all")
public class MybatisPlusCodeGeneration {
    // 模块
    private static final String model = "linling-core-code-generation";
    // 数据库
    private static final String dataUrl = "jdbc:mysql://127.0.0.1:3306/linling_core";
    private static final String driveClass = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";
    // 包设置
    private static final String packageName = "cn.hll520.linling.core.code.generation";  //pack
    private static final String packageModel = "";  // model 没有为""
    private static final String objectName = "po";  // po
    private static final String serviceName = "service";  // ser
    private static final String serviceImplName = "service.impl";  // ser Impl
    private static final String controlName = "control";  // ser
    private static final String daoClassName = "mapper";  // dao class
    private static final String daoResName = "mapper";  // dao res
    // 表设置
    private static final String[] tableName = {"linling_core_user"};   // 需要执行的表明
    private static final String[] tablePrefix = {"linling_core_"};  // 前缀 会自动去除
    // 字段设置
    private static final String createTime = "createTime";  // 创建时间字段
    private static final String updateTime = "updateTime";  // 更新时间字段
    private static final String logicDelteName = null;  // 逻辑删除
    private static final String versionName = null;  // 乐观锁


    /**
     * 更改上面配置后 执行
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        autoConfig(autoGenerator);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    /**
     * 自动 默认配置
     *
     * @param autoGenerator 自动代码生成
     */
    private static void autoConfig(AutoGenerator autoGenerator) {
        // 根模块目录
        String rootDir = System.getProperty("user.dir");
        String generationDir = rootDir + "\\" + model;
        System.err.println(generationDir);

        /*
         * -----------------------------------
         *           全局配置
         * -----------------------------------
         **/
        GlobalConfig globalConfig = new GlobalConfig();
        autoGenerator.setGlobalConfig(globalConfig);

        globalConfig.setOutputDir(generationDir + "/src/main/java"); // 代码输出目录
        globalConfig.setAuthor("lpc lpc@hll520.cn");
        globalConfig.setOpen(false); // 完成后不打开目录


        /*
         * -----------------------------------
         *           数据源配置
         * -----------------------------------
         **/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        autoGenerator.setDataSource(dataSourceConfig);

        // 拼接数据库字符串
        String url = dataUrl + "?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        dataSourceConfig.setUrl(url); // url
        dataSourceConfig.setDriverName(driveClass); // drive
        dataSourceConfig.setUsername(username); // 用户名
        dataSourceConfig.setPassword(password); // 密码
        //  dataSourceConfig.setSchemaName("public"); //设置schema


        /*
         * -----------------------------------
         *           数据源配置
         * -----------------------------------
         **/
        PackageConfig packageConfig = new PackageConfig();
        autoGenerator.setPackageInfo(packageConfig);

        packageConfig.setParent(packageName); // 包名
        packageConfig.setModuleName(packageModel); // 模块名
        packageConfig.setEntity(objectName); // po
        packageConfig.setService(serviceName);  //ser
        packageConfig.setServiceImpl(serviceImplName); //impl
        packageConfig.setController(controlName); // con
        packageConfig.setMapper(daoClassName);


        /*
         * -----------------------------------
         *           自定义配置
         * -----------------------------------
                 <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.30</version>
        </dependency>
         **/
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // TODO
            }
        };
        autoGenerator.setCfg(injectionConfig);

        List<FileOutConfig> focList = new ArrayList<>(); // 自定义输出配置
        injectionConfig.setFileOutConfigList(focList); // 设置输出

        // 模板引擎
        String templatePath = "/templates/mapper.xml.ftl";
        focList.add(new FileOutConfig(templatePath) {  // 自定义配置会被优先输出
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generationDir + "/src/main/resources/" + daoResName + "/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
                cfg.setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        // 判断自定义文件夹是否需要创建
                        checkDir("调用默认方法创建的目录，自定义目录用");
                        if (fileType == FileType.MAPPER) {
                            // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                            return !new File(filePath).exists();
                        }
                        // 允许生成模板文件
                        return true;
                    }
                });
                */




        /*
         * -----------------------------------
         *           模板配置
         * -----------------------------------
         **/
        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setTemplate(templateConfig);

        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        /* templateConfig.setEntity("templates/entity2.java");
         templateConfig.setService();
         templateConfig.setController();*/

        templateConfig.setXml(null);


        /*
         * -----------------------------------
         *          策略配置
         * -----------------------------------
         **/
        StrategyConfig strategy = new StrategyConfig();
        autoGenerator.setStrategy(strategy);


        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill(createTime == null ? "code_generator$not_creatime" : createTime,
                FieldFill.INSERT));
        tableFills.add(new TableFill(updateTime == null ? "code_generator$not_updatime" : updateTime,
                FieldFill.INSERT_UPDATE));

        strategy.setTablePrefix(tablePrefix);  // 表前缀
        strategy.setTableFillList(tableFills);  //字段填充
        strategy.setInclude(tableName);  // 表
        strategy.setLogicDeleteFieldName(logicDelteName); // 逻辑删除
        strategy.setVersionFieldName(versionName); // 乐观锁
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
    }
}
