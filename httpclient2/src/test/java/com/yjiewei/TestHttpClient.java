package com.yjiewei;

import com.alibaba.fastjson.JSON;
import com.yjiewei.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author yjiewei
 * @date 2021/7/3
 */
public class TestHttpClient {

    @Test // 测试有参数的httpGet请求
    void testHttpGetWithArgs() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // http://localhost:8080/httpGetWithArgs?name=yjiewei&age=10
        URI uri = null;

        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(8080)
                    .setPath("/yjiewei")
                    .setParameter("name", "yjiewei")
                    .setParameter("age", String.valueOf(10))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
        System.out.println(Arrays.toString(response.getAllHeaders()));

        response.close();
        httpClient.close();
    }

    @Test // 测试有对象参数和普通参数的post请求
    public void testHttpPostWithArgs() throws URISyntaxException, IOException {
        User user = new User();
        user.setUsername("杨杰炜");
        user.setAge(100);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        URI uri = null;
        uri = new URIBuilder().setScheme("http").setPort(8080)
                .setHost("localhost").setPath("/yjiewei")
                .setParameter("name", "杨杰炜")
                .setParameter("age", String.valueOf(10))
                .build();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity userEntity = new StringEntity(JSON.toJSONString(user), "utf-8"); // 需要设置编码
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(userEntity);
        // 设置请求头，格式
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String entityUtilsEntity = EntityUtils.toString(entity);
        System.out.println(entityUtilsEntity);
        System.out.println(Arrays.toString(response.getAllHeaders()));
        System.out.println(response.toString());
        System.out.println();
        response.close();
        httpClient.close();
    }

    @Test
    public void testHttpFileUpload() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/yjiewei/filesUpload");
        CloseableHttpResponse httpResponse = null;
        // 要定义一个文件组的key
        String filesKey = "files";
        File file = new File("头像.jpg");

        // httpmime对上传文件的辅助
        // 添加了两个文件二进制传输过去，并传过去了文件名，两个参数是以textbody传过去的
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody(filesKey, file, ContentType.DEFAULT_BINARY, URLEncoder.encode(file.getName(), "utf-8"));

        File file2 = new File("NETCA.png");
        multipartEntityBuilder.addBinaryBody(filesKey, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf-8"));

        ContentType contentType = ContentType.create("text/plain", Charset.forName("utf-8"));
        multipartEntityBuilder.addTextBody("name", "杨杰炜", contentType);
        multipartEntityBuilder.addTextBody("age", "15", contentType);

        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();
        System.out.println("HTTP 文件上传响应内容：" + EntityUtils.toString(entity, StandardCharsets.UTF_8)); // 防止响应乱码
        System.out.println("" + Arrays.toString(httpResponse.getAllHeaders()));

        httpResponse.close();
        httpClient.close();
    }
}
