package com.luolei.mybatisplus.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/29
 */
public class MybatisGen {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.setProperty("generated.source.dir", "G:\\test\\mybatis");
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(properties, warnings);
        InputStream inputStream = MybatisGen.class.getResourceAsStream("/generatorConfig.xml");
        Configuration configuration = cp.parseConfiguration(inputStream);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warnings);
        ProgressCallback progressCallback = new VerboseProgressCallback();
        generator.generate(progressCallback);
        for (String w: warnings) {
            System.out.println(w);
        }
    }
}
