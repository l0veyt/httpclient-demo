package com.xinlee.demo;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xin.lee on 2017/2/11.
 * HttpClient4.5.2 HelloWorld2 添加User-Agent请求头模拟浏览器行为访问tuicool
 */
public class HelloWorld2AddHeader {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://www.tuicool.com");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        CloseableHttpResponse response = client.execute(request);
        StatusLine statusLine = response.getStatusLine();           // 获取状态栏
        System.out.println(statusLine);                             // 打印状态栏 HTTP/1.1 200 OK
        System.out.println(statusLine.getProtocolVersion());        // 打印协议版本 HTTP/1.1
        System.out.println(statusLine.getStatusCode());             // 打印状态码 200
        System.out.println(statusLine.getReasonPhrase());           // 打印原因短语 OK
        System.out.println("----------------------------------------------------------");
        if(200 == statusLine.getStatusCode()) {
            /**
             * 手动方式获取响应实体的内容
             * 推荐：EntityUtils.toString(HttpEntity entity, "UTF-8")
             */
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();                   // 获取输入流
            byte[] byteArray = new byte[1024];
            int length;
            while((length = is.read(byteArray)) != -1) {
                System.out.println(new String(byteArray, 0, length, "UTF-8"));
            }
            System.out.println("----------------------------------------------------------");
            System.out.println(entity.getContentEncoding());        // 打印实体内容编码 null
            System.out.println(entity.getContentLength());          // 打印实体内容长度 -1
            System.out.println(entity.getContentType().getValue()); // 打印实体内容类型 Content-Type: text/html; charset=utf-8
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        response.close();
        client.close();
    }
}
