package com.zbcn.GOF.facade.concrete;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *  @title DataBase
 *  @Description 获取指定数据库的工具类
 *  @author zbcn8
 *  @Date 2020/6/11 14:25
 */
public class DataBase {

    private DataBase() {
    }

    /**
     * 获取数据库的配置信息
     * @param dbName
     * @return
     */
    public static Properties getProperties(String dbName){
        String fileName = dbName + ".txt";
        Properties properties = new Properties();
        fileName = DataBase.class.getClassLoader().getResource("").getPath() + "/GOF/facade" + File.separator + fileName;
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  properties;
    }
}
