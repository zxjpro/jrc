package com.xiaojiezhu.jrc.kit;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 朱小杰
 * @since 2016年11月7日 下午2:15:14
 * 版本 1.0<br>
 *  http的工具类
 */
public class HttpRequest {


    /**
     * 请求一个地址，默认为utf-8编码，GET请求
     * @param url
     * @return
     * @throws IOException
     * @since 2016年11月7日 下午12:46:38
     */
    public static String request(String url) throws IOException{
        return request(url,"UTF-8","GET");
    }
    /**
     * 请求一直地址，UTF-8编码，GET请求，并以指定的类型返回
     * @param url
     * @param t
     * @return
     * @throws IOException
     * @since 2016年11月7日 下午12:47:04
     */
    public static <T> T request(String url,Class<T> t) throws IOException{
        String content = request(url,"UTF-8","GET");
        return JSON.parseObject(content, t);
    }
    /**
     *请求一个地址，自定义编码，自定义请求类型，自定义返回类型
     * @param url
     * @param encode
     * @param requestMethod
     * @param t
     * @return
     * @throws IOException
     * @since 2016年11月7日 下午12:47:29
     */
    public static <T> T request(String url,String encode,String requestMethod,Class<T> t) throws IOException{
        String content = request(url,encode,requestMethod);
        return JSON.parseObject(content, t);
    }

    public static String request(String url,String encode,String requestMethod) throws IOException{
        URL u;
        BufferedReader reader = null;
        try {
            u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            InputStream in = conn.getInputStream();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                throw new IOException("请求 " + url + "时发生异常，状态码是" + responseCode);
            }
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("请求 " + url + "时发生异常");
        } finally{
            CloseUtil.close(reader);
        }
    }



    //===================================================================
    private HttpURLConnection conn = null;
    private String charset;

    public String request() throws IOException{
        InputStream inputStream = this.conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, this.charset));
        String line;
        StringBuffer sb = new StringBuffer();
        while((line = reader.readLine()) != null){
            sb.append(line);
        }
        if(reader != null){
            reader.close();
        }
        return sb.toString();
    }
    public <T> T request(Class<T> t) throws IOException{
        return JSON.parseObject(request(), t);
    }

    /**
     * 获取响应的包装，不需要时，需要调用close()方法关流
     * @return
     * @throws IOException
     */
    public CloseableResponse requestConnection()throws IOException{
        InputStream inputStream = this.conn.getInputStream();
        CloseableResponse response = new CloseableResponse(this.conn,inputStream);
        return response;
    }



    private HttpRequest(){}
    private void setUrlConnection(HttpURLConnection conn){
        this.conn = conn;
    }
    private void setCharset(String charset){
        this.charset = charset;
    }
    public static class Builder{
        /**
         * JSON格式的数据
         */
        public final static String CONTENT_TYPE_JSON = "application/json";
        /**
         *  二进制流数据（如常见的文件下载）
         */
        public final static String CONTENT_TYPE_STREAM = "application/octet-stream";
        private HttpURLConnection conn;
        private String charset = "UTF-8";
        private String responseCharset = "UTF-8";
        private String url;
        private String contentType = "text/plain";
        private String method = "POST";
        private int connectionMilliseconds = 5000;
        private int timeOutMilliseconds = 5000;
        private byte[] streams;
        private Map<String,String> headers = new HashMap<>();
        private Builder(){

        }
        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder url(String url) throws IOException{
            this.url = url;
            return this;
        }
        public String getUrl(){
            return this.url.toString();
        }
        public Builder get(){
            this.method = "GET";
            return this;
        }
        public Builder post(){
            this.method = "POST";
            return this;
        }
        public Builder json(){
            this.contentType = CONTENT_TYPE_JSON;
            return this;
        }
        /**
         * 连接时间
         * @param connectionMilliseconds 单位毫秒
         * @return
         */
        public Builder connectionTmeout(int connectionMilliseconds){
            this.connectionMilliseconds = connectionMilliseconds;
            return this;
        }
        public Builder timeout(int timeOutMilliseconds){
            this.timeOutMilliseconds = timeOutMilliseconds;
            return this;
        }
        public Builder requestCharset(String charset){
            this.charset = charset;
            //this.conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + this.charset);
            return this;
        }
        public Builder contentType(String contentType){
            this.contentType = contentType;
            //this.conn.setRequestProperty("Content-Type", contentType);
            return this;
        }
        public Builder responseCharset(String charset){
            this.responseCharset = charset;
            return this;
        }
        public Builder header(String name,String value){
            headers.put(name,value);
            return this;
        }


        public Builder cookie(String name,String value){
//			CookieManager manager = new CookieManager();
//			manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//			CookieHandler.setDefault(manager);
//			Cookie cookie = new Cookie(name,value);
//			this.conn.setRequestProperty("Cookie", "zzzz=bbbbb,bb=cc,a=b,c=d");
            return this;
        }
        public Builder stream(byte[] buf) throws IOException{
            this.streams = buf;

            return this;
        }
        public HttpRequest build(){
            try{
                this.conn = (HttpURLConnection) new URL(url).openConnection();
                this.conn.setDoInput(true);
                this.conn.setDoOutput(true);
                if(this.connectionMilliseconds != -1){
                    this.conn.setConnectTimeout(connectionMilliseconds);
                }
                this.conn.setRequestMethod(method);
                if(this.timeOutMilliseconds != -1){
                    this.conn.setReadTimeout(timeOutMilliseconds);
                }

                this.conn.setRequestProperty("Content-Type", ""+contentType+"; charset=" + this.charset);
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, String> entry = iterator.next();
                    this.conn.setRequestProperty(entry.getKey(),entry.getValue());
                }
                if(this.streams != null){
                    this.conn.setRequestProperty("Content-Length", String.valueOf(this.streams.length));
                    OutputStream out = this.conn.getOutputStream();
                    out.write(this.streams);
                    out.flush();
                    out.close();
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage(),e);
            }


            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setUrlConnection(this.conn);
            httpRequest.setCharset(this.responseCharset);
            return httpRequest;
        }

    }

    public static class CloseableResponse implements Closeable{
        private HttpURLConnection conn;
        private InputStream in;
        private CloseableResponse(HttpURLConnection conn, InputStream inputStream) {
            this.conn = conn;
            this.in = inputStream;
        }
        private ByteBuffer byteBuf = null;

        /**
         * 返回请求后得到的内容，但是得确定文件返回不会太大，避免内存溢出<br>
         * 这个方法是缓存的，并且是线程安全的
         * @return
         * @throws IOException
         */
        public byte[] bytes() throws IOException{
            if(this.byteBuf != null){
                return this.byteBuf.array();
            }else{
                synchronized (HttpRequest.class) {
                    if(this.byteBuf != null){
                        return this.byteBuf.array();
                    }else{
                        int contentLen = length();
                        byte[] by = new byte[1024];
                        int len = 0;
                        ByteBuffer byteBuffer = ByteBuffer.allocate(contentLen);
                        while((len = in.read(by)) != -1){
                            byteBuffer.put(by,0,len);
                        }
                        this.byteBuf = byteBuffer;
                        return byteBuffer.array();
                    }
                }
            }


        }

        public int length() throws IOException{
            int contentLength = conn.getContentLength();
            return contentLength;
        }
        /**
         * 获取响应的长度
         * @return
         */
        public int getContentLength(){
            int contentLength = conn.getContentLength();
            return contentLength;
        }

        public int responseCode() throws IOException{
            int code = conn.getResponseCode();
            return code;
        }
        public InputStream getInputStream(){
            return this.in;
        }

        /**
         * 获取响应的头信息
         * @param name 名称
         * @return
         */
        public String getHeader(String name){
            String field = this.conn.getHeaderField(name);
            return field;
        }
        /**
         * 获取响应的所有headers
         * @return
         */
        public Map<String, List<String>> getHeaders(){
            Map<String, List<String>> fields = this.conn.getHeaderFields();
            return fields;
        }

        @Override
        public void close() throws IOException {
            this.in.close();
        }

    }

}
