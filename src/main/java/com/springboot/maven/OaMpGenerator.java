package com.springboot.maven;

public class OaMpGenerator {


    public static void main(String[] args) {}/*{
        AutoGenerator mpg = new AutoGenerator();

        // 设置数据源
        mpg.setDataSource(new DataSourceConfig()
                .setDriverName("org.postgresql.Driver")
                // 设置数据库类型
                .setDbType(DbType.POSTGRE_SQL)
                .setUsername("postgres")
                .setPassword("123456")
                .setUrl("jdbc:postgresql://localhost:5432/mydata")
        );

        // 全局配置
        mpg.setGlobalConfig(new GlobalConfig()
                // 输出目录
                .setOutputDir("C:/tools/fans-springboot-maven/src/main/java/com/springboot/maven/oa")
                // 是否覆盖
                .setFileOverride(true)
                // 开启AR模式
                .setActiveRecord(true)
                // XML二级缓存
                .setEnableCache(false)
                // 生成ResultMap
                .setBaseResultMap(true)
                // 生成 sql片段
                .setBaseColumnList(true)
                // 自动打开生成后的文件夹
                .setOpen(true)
                // 所有文件的生成者
                .setAuthor("dancer")
                // 自定义文件命名,%s会自动填充表实体类名字
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

        // 策略配置
        mpg.setStrategy(new StrategyConfig()
                // 需要生成的表
               *//* .setInclude("t_action_score_record")*//*
                // 实体类使用Lombok
                .setEntityLombokModel(true)
                // 表名生成策略,下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
        );

        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                // 基本包路径
                .setParent("name.dancer")
                // 设置Controller包名
                .setController("controller")
                // 设置entity包名
                .setEntity("entity")
                // 设置Mapper包名
                .setMapper("mapper")
                // 设置Service包名
                .setService("service")
                // 设置Service实现类包名
                .setServiceImpl("service.impl")
                // 设置Mapper.xml包名
                .setXml("mapper")
        );

        // 注入自定义配置
        mpg.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(1);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.singletonList(
                new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义Mapper.xml输出路径
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return "C:/tools/fans-springboot-maven/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                })));

        mpg.execute();
    }*/

}
