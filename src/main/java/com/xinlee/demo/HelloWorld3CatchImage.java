package com.xinlee.demo;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by xin.lee on 2017/2/11.
 * HttpClient4.5.2 HelloWorld3 抓取图片
 */
public class HelloWorld3CatchImage {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://i0.hdslb.com/bfs/archive/4b9c7031618e743f3ed3e13f74ebc0130c55d981.jpg");
        CloseableHttpResponse response = client.execute(request);
        if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            System.out.println("Content-Type:" + entity.getContentType().getValue());
            // 获取响应实体内容的输入流
            InputStream is = entity.getContent();
            String filename = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            FileUtils.copyToFile(is, new File("C:" + File.separator + "Users" + File.separator + "xin.lee" + File.separator + "Desktop" + File.separator + filename));
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        response.close();
        client.close();
    }
}
