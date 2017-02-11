package com.xinlee.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by xin.lee on 2017/2/11.
 * HttpClient4.5.2 HelloWorld6 设置超时时间
 */
public class HelloWorld5TimeOut {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://central.maven.org/maven2/");
        /**
         * 请求访问目标主机所消耗的时间为：连接时间
         *  Exception in thread "main" org.apache.http.conn.ConnectTimeoutException: Connect to central.maven.org:80 [central.maven.org/151.101.24.209] failed: connect time out
         * 请求到达后从目标主机数据库/服务器端读取数据所消耗的时间为：读取时间
         *  Exception in thread "main" java.net.SocketTimeoutException: Read timed out
         */
        RequestConfig requestConfig = RequestConfig
                .custom()                           // 选择自定义的请求配置
                .setConnectTimeout(5000)           // 设置连接超时时间
                .setSocketTimeout(5000)            // 设置读取超时时间
                .build();                           // 构建请求配置
        request.setConfig(requestConfig);
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        CloseableHttpResponse response = client.execute(request);
        if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println(content);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        response.close();
        client.close();
    }
}
