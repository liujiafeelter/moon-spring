package com.moon.store.utils;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HttpUtil
{
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final String URL = "";


    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<>();
        params.put("name", "刘佳");
        doPost(params);
    }

    public static String doPost(Map<String,Object> paramsMap){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(URL);
            //参数设置
            List<NameValuePair> list = new ArrayList<>();

            Iterator<Map.Entry<String, Object>> it = paramsMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, String.valueOf(value));
                list.add(basicNameValuePair);
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);//设置编码
            post.setEntity(formEntity);
            //获取结果
            CloseableHttpResponse res = closeableHttpClient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                logger.info("POST请求返回的数据是：" + result);
                return result;
            }
        }catch (Exception e){
            logger.info("doPost error", e);
        }finally {
            //关闭流并释放资源
            try {
                closeableHttpClient.close();
            } catch (Exception e) {
                logger.info("doPost error", e);
            }
        }
        return null;
    }


}
