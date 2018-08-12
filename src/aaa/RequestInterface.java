package aaa;

import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RequestInterface {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:80/sysReadArticle?id=30");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        System.out.println(conn.getClass());
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.connect();
        InputStreamReader bin = new InputStreamReader(conn.getInputStream());
        BufferedReader reader = new BufferedReader(bin);
        byte[] b = new byte[1024];
        int len = 0;
        String s = null;
        StringBuilder sb = new StringBuilder();
        while((s = reader.readLine())!=null){
            sb.append(s);
        }
        System.out.println(sb.toString());
        JSONObject json = JSONObject.fromObject(sb.toString());
        String content = (String) json.get("acontent");
        System.out.println(content);
    }
}
