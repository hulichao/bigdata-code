package com.hoult.flume;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Charsets;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.apache.parquet.Strings;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            if (Strings.isNullOrEmpty(jsonStr)) {
                return null;
            }
            // 将 string 转成 json 对象
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            String timestampStr = jsonObject.getString("time");
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
}
