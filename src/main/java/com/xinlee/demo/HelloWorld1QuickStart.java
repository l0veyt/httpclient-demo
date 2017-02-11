package com.xinlee.demo;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by xin.lee on 2017/2/11.
 * HttpClient4.5.2 HelloWorld1 快速上手
 */
public class HelloWorld1QuickStart {

    public static void main(String[] args) {
        CloseableHttpClient client = HttpClients.createDefault();           // 创建HttpClient实例
        HttpGet request = new HttpGet("http://www.open1111.com");           // 创建HttpGet实例
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);                             // 执行请求
            if(200 == response.getStatusLine().getStatusCode()) {           // 判断响应码
                HttpEntity entity = response.getEntity();                   // 获取响应实体
                String content = EntityUtils.toString(entity, "UTF-8");     // 解析响应实体
                System.out.println(content);                                // 打印响应实体内容
            }
        } catch (ClientProtocolException e) {                               // Http协议异常
            e.printStackTrace();
        } catch (ParseException e) {                                        // 解析异常
            e.printStackTrace();
        } catch (IOException e) {                                           // IO异常
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();                                       // 释放响应资源
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(client != null) {
                try {
                    client.close();                                         // 释放客户端资源
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
