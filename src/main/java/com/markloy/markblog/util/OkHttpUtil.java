package com.markloy.markblog.util;

import okhttp3.*;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @throws IOException
     */
    public static String get(String url, @Nullable String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = null;
        if (StringUtils.isEmpty(token)) {
            request = new Request.Builder().url(url).build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", token)
                    .build();
        }
        Response response = client.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }

    /**
     * 发送post请求
     *
     * @param url  请求地址
     * @param json 请求参数
     * @return
     * @throws IOException
     */
    public static String post(String url, String json) throws IOException {
        assert json != null : "请求体不能为空";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }

}
