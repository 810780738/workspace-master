package com.xiaozhu.rpm_server.utils;/**
 * @Auther: Administrator
 * @Date: 2018/10/25 15:03
 * @Description:
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 *@ClassName HttpRequestUtils
 *@Description TODO
 *@Author zhusm
 *@Date 2018/10/25 15:03    
 *@Version 1.0
 */
@Slf4j
public class HttpRequestUtils {

    /**全局连接池对象*/
    private static final PoolingHttpClientConnectionManager connerManager = new PoolingHttpClientConnectionManager();

    /**
     * @Author zhusm
     * @Description 静态代码块配置信息
     * @Date 15:12 2018/10/25
     * @Param
     * @return
     **/
    static {
        //最大连接数
        connerManager.setMaxTotal(200);
        //每个链接的路由数
        connerManager.setDefaultMaxPerRoute(20);
    }

    /**
     * @Author zhusm
     * @Description 获取HttpClient连接对象
     * @Date 15:16 2018/10/25
     * @Param [timeOut]连接超时时间 请求超时 响应超时
     * @return org.apache.http.impl.client.CloseableHttpClient
     **/
    public static CloseableHttpClient getHttpClient(Integer timeOut){
        //创建http请求配置参数
        RequestConfig requestConfig = RequestConfig.custom()
                //获取连接超时时间
                .setConnectionRequestTimeout(timeOut)
                //请求超时
                .setConnectTimeout(timeOut)
                //响应超时
                .setSocketTimeout(timeOut)
                .build();

        HttpRequestRetryHandler httpRequestRetryHandler = (exception, exceptionCount, httpContext) -> {
            //重试超过三次放弃
            if (exceptionCount > 3) {
                log.error("重试超过三次放弃");
                return false;
            }
            //服务器丢掉链接，重试
            if (exception instanceof NoHttpResponseException){
                log.error("服务器丢掉链接，重试");
                return true;
            }
            //SSL握手异常跳过
            if (exception instanceof SSLHandshakeException) {
                log.error("SSL握手异常跳过");
                return false;
            }
            //超时重试
            if (exception instanceof InterruptedIOException){
                log.error("超时重试");
                return true;
            }
            //目标服务器不可达
            if (exception instanceof UnknownHostException) {
                log.error("目标服务器不可达");
                return false;
            }
            //连接被拒绝 跳过
            if (exception instanceof ConnectTimeoutException) {
                log.error("连接被拒绝 跳过");
                return false;
            }
            //SSL异常
            if (exception instanceof SSLException) {
                log.error("SSL异常");
                return false;
            }

            HttpClientContext httpClientContext = HttpClientContext.adapt(httpContext);
            HttpRequest request = httpClientContext.getRequest();
            //幂等请求重试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                log.error("幂等请求重试");
                return true;
            }
            return false;
        };


        return HttpClients.custom()
                //设置配置信息
                .setDefaultRequestConfig(requestConfig)
                //重试设置
                .setRetryHandler(httpRequestRetryHandler)
                //设置连接池信息
                .setConnectionManager(connerManager)
                .build();
    }


    /**
     * @Author zhusm
     * @Description Get请求
     * @Date 15:39 2018/10/25
     * @Param [url, timeOut]
     * @return java.lang.String
     **/

    public static String httpGet(String url,Integer timeOut){
        String msg = "-1";
        CloseableHttpClient httpClient = getHttpClient(timeOut);
        //创建Get请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
             response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            msg = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null){
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    log.error("HTTPClient客户端释放错误");
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }


    /**
     * @Author zhusm
     * @Description POST请求
     * @Date 15:55 2018/10/25
     * @Param [url, requestStr, timeOut]
     * @return 服务端返回的数据
     **/
    public static String httpPost(String url,String requestStr,Integer timeOut){
        String msg = "-1";
        CloseableHttpClient httpClient = getHttpClient(timeOut);
        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("Content-Type","text/xml;charset=utf-8");
        httpPost.addHeader("Content-Type","application/json;charset=utf-8");
        httpPost.addHeader("X-Access-Token","06876bc0-2417-40cc-8e0d-650c37b882c5");
        httpPost.addHeader("X-Service-Id","hcn.queue");
        httpPost.addHeader("X-Service-Method","queryPatientQueues");
        CloseableHttpResponse response = null;
        try {
            StringEntity stringEntity = new StringEntity(requestStr, "UTF-8");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            msg = EntityUtils.toString(entity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }


    public static void main(String[] args) {
        String s = httpPost("http://219.131.197.18:8210/hcn-web/*.jsonRequest", "[\"e342865d-e93c-42a4-9e6a-782a9d91a936\"]", 60000);
        System.out.println(s);
    }
}
