package com.zbcn.common.resource;

import com.zbcn.utils.file.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 *  资源文件加载类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/2 16:57
 */
public class ResourceLoader {

    public static void main(String[] args) throws IOException {
        // Array.class的完整路径
        //getPath();
        //getSystemPath();
        //getRelativePath();

        //getAbsolutePath();

        getPathByThread();

        //getPathByClass();

        //getPathByClassLoad();

        //getSeparate();

        getPathByClassLoadClass();
    }

    private static void getPathByClassLoadClass() {
        URL systemResource = ClassLoader.getSystemResource("");
        System.out.println("类加载路径:" + systemResource.getPath());
    }

    private static void getSeparate() {
        String property = System.getProperty("file.separator");
        System.out.println("文件分割符:" + property);
    }

    private static void getPathByClassLoad() {
        URL relativePath = ResourceLoader.class.getClassLoader().getResource("");
        System.out.println("类加载路径" + relativePath.getPath());
        URL absPath = ResourceLoader.class.getClassLoader().getResource("/");
        System.out.println("未获取到路径:" + absPath);
    }

    private static void getPathByClass() {
        //不以’/'开头时，默认是从此类所在的包下取资源
        URL relativePath = ResourceLoader.class.getResource("");
        System.out.println("相对路径:" + relativePath.getPath());
        //以’/'开头时，则是从ClassPath根下获取；
        URL absPath = ResourceLoader.class.getResource("/");
        System.out.println("绝对路径:" + absPath.getPath());
    }

    /**
     * 绝对路径:绝对路径以 "/" 开头
     */
    private static void getAbsolutePath() {
        //获取当前目录的路径
        URL resource = ResourceLoader.class.getResource("/");
        System.out.println("获取classpath："+ resource.getPath());
        URL absPath = ResourceLoader.class.getResource("/com/zbcn/common/resource/demon.properties");
        System.out.println(absPath.getPath());
    }

    private static void getPathByThread() {
        //获取当前目录的路径
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println("获取classpath："+ resource.getPath());
        URL absPath = Thread.currentThread().getContextClassLoader().getResource("com/zbcn/common/resource/demon.properties");
        System.out.println(absPath.getPath());
    }


    /**
     * 相对路径
     */
    private static void getRelativePath() {
        //获取当前目录的路径
        URL resource = ResourceLoader.class.getResource("");
        System.out.println("当前目录的路径："+ resource.getPath());
        //获取指定文件
        URL demon = ResourceLoader.class.getResource("demon.properties");
        String file = demon.getFile();
        System.out.println("获取指定文件:"+ file);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            String name = (String)properties.get("name");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 系统路径
     */
    private static void getSystemPath() {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }

    /**
     * 获取指定类的全路径
     * @throws IOException
     */
    private static void getPath() throws IOException {
        String name = "java/sql/Array.class";
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = contextClassLoader.getResources(name);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url.toString());
        }
    }
}
