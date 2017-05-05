package com.zzc.webservice;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;



public class Test {
	public static void main(String[] args) throws Exception {
		JSONObject json = new JSONObject();
		json.put("year", 2014);
		json.put("quarter_id",3);
		json.put("income", 7);
		json.put("ticket_income",7);
		json.put("tourists", 1);
		json.put("free_tourists", 1);
		json.put("note", "1");
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("key", "0.675221457437362");
		jsonBody.put("code", "4602004A0004");
		jsonBody.put("season", json);
		
		// 创建url资源
        URL url = new URL("http://test.cnta.gov.cn/apis/add_season");
        // 建立http连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置允许输出
        conn.setDoOutput(true);

        conn.setDoInput(true);

        // 设置不用缓存
        conn.setUseCaches(false);
        // 设置传递方式
        conn.setRequestMethod("POST");
        // 设置维持长连接
        conn.setRequestProperty("Connection", "Keep-Alive");
        // 设置文件字符集:
        conn.setRequestProperty("Charset", "UTF-8");
        //转换为字节数组
        byte[] data = (jsonBody.toString()).getBytes();
        // 设置文件长度
//        conn.setRequestProperty("Content-Length", String.valueOf(data.length));

        // 设置文件类型:
        conn.setRequestProperty("contentType", "x-www-form-urlencoded");


        // 开始连接请求
        conn.connect();
        OutputStream  out = conn.getOutputStream();     
        // 写入请求的字符串
        out.write((jsonBody.toString()).getBytes());
        out.flush();
        out.close();

        System.out.println(conn.getResponseCode());

        // 请求返回的状态
        if (conn.getResponseCode() == 200) {
            System.out.println("连接成功");
            // 请求返回的数据
            InputStream in = conn.getInputStream();
            String a = null;
            try {
                byte[] data1 = new byte[in.available()];
                in.read(data1);
                // 转成字符串
                a = new String(data1);
                System.out.println(a);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            System.out.println("no++");
        }
	}
}
