package aaa;

import net.sf.json.JSON;
        import net.sf.json.JSONArray;
        import net.sf.json.JSONObject;
        import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.sql.*;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class JinseRequest {
    public void requestNews() throws IOException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.net.URLEncoder.encode("xxxx","utf-8");
        URL url = new URL("http://api.coindog.com/live/list?id=0&flag=down&limit=5");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
//        PrintWriter pw = new PrintWriter(connection.getOutputStream());
//        pw.write("");
//        pw.flush();
//        pw.close();
        if (connection.getResponseCode() == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String liner;
            StringBuffer buffer=new StringBuffer();
            while ((liner=br.readLine())!=null) {
                buffer.append(liner);
            }
            ConnectionTest ct = new ConnectionTest();
            Connection conn = ct.getConn();

            JSONObject object = JSONObject.fromObject(buffer.toString());
            JSONArray array = object.getJSONArray("list");
            for (int i = 0; i < array.size(); i++){
                JSONObject olive = array.getJSONObject(i);
                System.out.println(olive.get("date"));
                System.out.println(olive.get("lives"));
                JSONArray livesArr = JSONArray.fromObject(olive.get("lives"));
                List<Live> ls = (List<aaa.Live>) JSONArray.toCollection(livesArr,aaa.Live.class);
                for (Live e : ls){
//                    Pattern pattern = Pattern.compile("([\\【].+[\\】])(.+)");
//                    System.out.println(e);

                }
                for (int j = 0 ; j < livesArr.size(); j++){
                    String title = "";
                    String content = "";
                    String imgs = "";
                    Pattern pattern = Pattern.compile("([\\【].+[\\】])(.+)");
                    Matcher matcher = pattern.matcher(ls.get(j).getContent());
                    if (matcher.find()){
                        System.out.println("title====="+matcher.group(1));
                        System.out.println("content======="+matcher.group(2));
                        title = matcher.group(1);
                        content = matcher.group(2);
                        PreparedStatement st = conn.prepareStatement("select  * from article where atitle = ?");
                        st.setString(1,title);
                        ResultSet set = st.executeQuery();
                        if (set.next()){
                            continue;
                        }
                    }
                    JSONObject li = (JSONObject) livesArr.get(j);
//                    System.out.println(li.get("images"));
                    JSONArray images = (JSONArray) li.get("images");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int k = 0; k < images.size(); k++){
                        JSONObject img = images.getJSONObject(k);
                        System.out.println("img======"+img.get("url"));
                        stringBuilder.append(img.get("url").toString());
                    }
                    imgs = stringBuilder.toString();
                    PreparedStatement ps = conn.prepareStatement("insert into article(atitle,uid,acontent,addtime,readnumber,state,isbanner,category,author,source)" +
                            "values (?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1,title);
                    ps.setString(2,"49bf3348daa74b1aa25c15db0327b173");
                    ps.setString(3,content+imgs);
                    String addtime = sdf.format(new Date());
                    ps.setString(4,addtime);
                    ps.setInt(5,0);
                    ps.setInt(6,0);
                    ps.setInt(7,0);
                    ps.setInt(8,7);
                    ps.setString(9,"7财经");
                    ps.setString(10,"金色财经");
                    ps.execute();
                }

            }
            System.out.println(object);
//            JSONArray jsonArray = JSONArray.fromObject(buffer.toString());
//            System.out.println(jsonArray.toString());
//            for (Object jsonObject : jsonArray){
//                System.out.println(jsonObject);
//            }
        }
    }
//    public static void main(String[] args) throws IOException, SQLException {
//        new JinseRequest().requestNews();
//    }
}
