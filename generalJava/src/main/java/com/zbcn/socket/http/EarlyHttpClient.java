package com.zbcn.socket.http;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *  早期的http 客户端请求
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 13:40
 */
public class EarlyHttpClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.liaoxuefeng.com/wiki/1252599548343744/1319099982413858");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000); // 请求超时5秒
            // 设置HTTP头:
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 11; Windows NT 5.1)");
            // 连接并发送HTTP请求:
            connection.connect();
            // 判断HTTP响应是否200:
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("bad response");
            }
            // 获取所有响应Header:
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
            }
            //获取响应体
            InputStream inputStream = connection.getInputStream();
            List<String> list = IOUtils.readLines(inputStream, "utf-8");
            list.forEach(str->{
                System.out.println(str);
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
