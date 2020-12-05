package com.hoult.flume;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Charsets;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.interceptor.Interceptor;
import org.apache.parquet.Strings;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInterceptor implements Interceptor {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 获得body的内容
        String eventBody = new String(event.getBody(), Charsets.UTF_8);
        // 获取header的内容
        Map<String, String> headerMap = event.getHeaders();
        final String[] bodyArr = eventBody.split("\\s+");
        try {
            String jsonStr = bodyArr[6];
            System.out.println("jsonStr:" + jsonStr);
            if (Strings.isNullOrEmpty(jsonStr)) {
                return null;
            }
            // 将 string 转成 json 对象
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            String timestampStr = jsonObject.getJSONObject("app_active").getString("time");
            //将timestamp 转为时间日期类型（格式 ：yyyyMMdd)
            long timeStamp = Long.valueOf(timestampStr);
            String date = formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()));
            headerMap.put("logtime", date);
            event.setHeaders(headerMap);
        } catch (Exception e) {
            headerMap.put("logtime", "unknown");
            event.setHeaders(headerMap);
        }
        return event;

    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> out = new ArrayList<>();
        for (Event event : events) {
            Event outEvent = intercept(event);
            if (outEvent != null) {
                out.add(outEvent);
            }
        }
        return out;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        @Override
        public Interceptor build() {
            return new CustomerInterceptor();
        }

        @Override
        public void configure(Context context) {
        }
    }

    @Test
    public void test() {
        String str = "2020-08-20 11:56:02.557 [main] INFO  com.lagou.ecommerce.AppStart - {\"app_active\":{\"name\":\"app_active\",\"json\":{\"entry\":\"2\",\"action\":\"0\",\"error_code\":\"0\"},\"time\":1595261095608},\"attr\":{\"area\":\"克拉玛依\",\"uid\":\"2F10092A99999\",\"app_v\":\"1.1.10\",\"event_type\":\"common\",\"device_id\":\"1FB872-9A10099999\",\"os_type\":\"0.6.2\",\"channel\":\"AA\",\"language\":\"chinese\",\"brand\":\"xiaomi-9\"}}";
        //new Event
        Event event = new SimpleEvent();
        event.setHeaders(new HashMap<>());
        event.setBody(str.getBytes(Charsets.UTF_8));
        Event outEvent = new CustomerInterceptor().intercept(event);
        System.out.println(outEvent);
    }
}
