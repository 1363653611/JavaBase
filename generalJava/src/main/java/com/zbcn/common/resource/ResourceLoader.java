package com.zbcn.common.resource;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 *  资源文件加载类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/2 16:57
 */
public class ResourceLoader {

    public static void main(String[] args) throws IOException {
        // Array.class的完整路径
        String name = "java/sql/Array.class";
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = contextClassLoader.getResources(name);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url.toString());
        }

    }
}
