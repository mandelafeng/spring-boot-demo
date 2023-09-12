package com.cfhui.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.Map.Entry;


/**
 * @className: HttpClientUtil
 * @description: TODO 类描述
 * @author: chunfenghui
 * @date: 2021/6/14 下午 6:32
 **/
public class HttpClientUtil {

    protected final Log LOG = LogFactory.getLog(HttpClientUtil.class);
    private static HttpClientUtil instance;
    protected Charset charset;

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        return getInstance(Charset.defaultCharset());
    }

    public static HttpClientUtil getInstance(Charset charset) {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        instance.setCharset(charset);
        return instance;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /**
     * post请求
     *
     * @param url 请求URL
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPost(String url) throws Exception {
        return doPost(url, null, null);
    }

    /**
     * 带参数的post请求
     *
     * @param url    请求URL
     * @param params 请求参数
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPost(String url, Map<String, Object> params) throws Exception {
        return doPost(url, params, null);
    }

    /**
     * 带参数和请求体的post请求
     *
     * @param url    请求URL
     * @param params 请求参数
     * @param header 请求头参数
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPost(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
        String body;
        try {
            // Post请求
            LOG.debug(" protocol: POST");
            LOG.debug("      url: " + url);
            HttpPost httpPost = new HttpPost(url.trim());
            // 设置参数
            LOG.debug("   params: " + JSON.toJSONString(params));
            httpPost.setEntity(new UrlEncodedFormEntity(map2NameValuePairList(params), charset));
            // 设置Header
            if (header != null && !header.isEmpty()) {
                LOG.debug("   header: " + JSON.toJSONString(header));
                for (Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }
            // 发送请求,获取返回数据
            body = execute(httpPost);
        } catch (Exception e) {
            throw e;
        }
        LOG.debug("   result: " + body);
        return body;
    }

    /**
     * post-参数转json请求
     *
     * @param url    请求URL
     * @param params 请求参数
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPostJson(String url, Map<String, Object> params) throws Exception {
        return doPostJson(url, params, null);
    }

    /**
     * post-参数转json + 请求头
     *
     * @param url    请求URL
     * @param params 请求参数
     * @param header 请求头
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPostJson(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
        String json = null;
        if (params != null && !params.isEmpty()) {
            for (Iterator<Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext(); ) {
                Entry<String, Object> entry = it.next();
                Object object = entry.getValue();
                if (object == null) {
                    it.remove();
                }
            }
            json = JSON.toJSONString(params);
        }
        return postJson(url, json, header);
    }

    /**
     * post+json字符串请求
     *
     * @param url  请求URL
     * @param json json
     * @return String 响应字符串
     * @throws Exception
     */
    public String doPostJson(String url, String json) throws Exception {
        return doPostJson(url, json, null);
    }

    /**
     * post+json字符串+请求头
     *
     * @param url    请求URL
     * @param json   json
     * @param header 请求头
     * @return
     * @throws Exception
     */
    public String doPostJson(String url, String json, Map<String, String> header) throws Exception {
        return postJson(url, json, header);
    }
    /**
     * [基础方法] post+json字符串+请求头
     *
     * @param url    请求URL
     * @param json   json
     * @param header 请求头
     * @return
     * @throws Exception
     */
    private String postJson(String url, String json, Map<String, String> header) throws Exception {
        String body;
        try {
            // Post请求
            LOG.debug(" protocol: POST");
            LOG.debug("      url: " + url);
            HttpPost httpPost = new HttpPost(url.trim());
            // 设置参数
            LOG.debug("   params: " + json);
            httpPost.setEntity(new StringEntity(json, ContentType.DEFAULT_TEXT.withCharset(charset)));
            httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
            LOG.debug("     type: JSON");
            // 设置Header
            if (header != null && !header.isEmpty()) {
                LOG.debug("   header: " + JSON.toJSONString(header));
                for (Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }
            // 发送请求,获取返回数据
            body = execute(httpPost);
        } catch (Exception e) {
            throw e;
        }
        LOG.debug("  result: " + body);
        return body;
    }

    /**
     * get请求
     * @param url 请求URL
     * @return String 响应字符串
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * get+请求头
     * @param url 请求URL
     * @param header 请求头
     * @return String 响应字符串
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> header) throws Exception {
        return doGet(url, null, header);
    }

    /**
     * get请求+请求参数+请求头
     * @param url 请求URL
     * @param params 请求参数
     * @param header 请求头
     * @return String 响应字符串
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
        String body;
        try {
            // Get请求
            LOG.debug("protocol: GET");
            HttpGet httpGet = new HttpGet(url.trim());
            // 设置参数
            if (params != null && !params.isEmpty()) {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(map2NameValuePairList(params), charset));
                String uri = httpGet.getURI().toString();
                if (uri.contains("?")) {
                    httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
                } else {
                    httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
                }
            }
            LOG.debug("     url: " + httpGet.getURI());
            // 设置Header
            if (header != null && !header.isEmpty()) {
                LOG.debug("   header: " + header);
                for (Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }
            // 发送请求,获取返回数据
            body = execute(httpGet);
        } catch (Exception e) {
            throw e;
        }
        LOG.debug("  result: " + body);
        return body;
    }

    /**
     * 下载文件
     * @param url 请求URL
     * @param path 文件保存位置
     * @throws Exception
     */
    public void doDownload(String url, String path) throws Exception {
        download(url, null, path);
    }

    /**
     * 下载文件 + 参数
     * @param url 请求URL
     * @param params 请求参数
     * @param path 文件保存全路径
     * @throws Exception
     */
    public void doDownload(String url, Map<String, Object> params, String path) throws Exception {
        download(url, params, path);
    }
    /**
     * 文件上传
     * @param url 请求URL
     * @param name
     * @param path
     * @return
     * @throws Exception
     */
    public String doUpload(String url, String name, String path) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put(name, new File(path));
        return doUpload(url, params);
    }

    /**
     * 文件上传+参数
     * @param url 请求URL
     * @param params 请求参数
     * @return String 响应字符串
     * @throws Exception
     */
    public String doUpload(String url, Map<String, Object> params) throws Exception {
        String body;
        // Post请求
        HttpPost httpPost = new HttpPost(url.trim());
        // 设置参数
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.setCharset(charset);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    FileBody fileBody = new FileBody((File) value);
                    entityBuilder.addPart(key, fileBody);
                } else {
                    entityBuilder.addPart(key, new StringBody(String.valueOf(value), ContentType.DEFAULT_TEXT.withCharset(charset)));
                }
            }
        }
        HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        // 发送请求,获取返回数据
        body = execute(httpPost);
        return body;
    }

    /**
     * [基础方法] 下载
     * @param url 请求URL
     * @param params 请求参数
     * @param path 下载位置
     * @throws Exception
     */
    private void download(String url, Map<String, Object> params, String path) throws Exception {
        // Get请求
        HttpGet httpGet = new HttpGet(url.trim());
        if (params != null && !params.isEmpty()) {
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(map2NameValuePairList(params)));
            String uri = httpGet.getURI().toString();
            if (uri.contains("?")) {
                httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
            } else {
                httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
            }
        }
        // 发送请求,下载文件
        downloadFile(httpGet, path);
    }

    /**
     * [基础方法-下载文件]
     * @param requestBase 请求基础
     * @param path 文件保存地址
     * @throws Exception
     */
    private void downloadFile(HttpRequestBase requestBase, String path) throws Exception {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(requestBase)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    byte[] b = EntityUtils.toByteArray(entity);
                    OutputStream out = new BufferedOutputStream(Files.newOutputStream(new File(path).toPath()));
                    out.write(b);
                    out.flush();
                    out.close();
                }
                EntityUtils.consume(entity);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private String execute(HttpRequestBase requestBase) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String body = null;
        try {
            try (CloseableHttpResponse response = httpclient.execute(requestBase)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    body = EntityUtils.toString(entity, charset.toString());
                }
                EntityUtils.consume(entity);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
        }
        return body;
    }

    private List<NameValuePair> map2NameValuePairList(Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> list = new ArrayList<>();
            for (String key : params.keySet()) {
                if (params.get(key) != null) {
                    String value = String.valueOf(params.get(key));
                    list.add(new BasicNameValuePair(key, value));
                }
            }
            return list;
        }
        return null;
    }
}

