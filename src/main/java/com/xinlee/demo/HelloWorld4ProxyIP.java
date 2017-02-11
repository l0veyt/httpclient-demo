package com.xinlee.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by xin.lee on 2017/2/11.
 * HttpClient4.5.2 HelloWorld4 代理IP设置
 */
public class HelloWorld4ProxyIP {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://www.tuicool.com");
        HttpHost proxy = new HttpHost("101.26.166.179", 9999);                          // 配置代理地址、端口
        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();   // 创建自定义请求配置
        request.setConfig(requestConfig);
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        CloseableHttpResponse response = client.execute(request);
        if(200 == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "UTF-8");
            System.out.println(content);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        response.close();
        client.close();
    }
}
