package aaa;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConnectionTest {
    public Connection getConn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String name = "root";
            String password = "Qingyun!@#";
            String url = "jdbc:mysql://47.93.254.45:3306/7cj?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            conn = DriverManager.getConnection(url,name,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("不能注册jdbc实现");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("建立连接时出现异常");
        }
        return conn;
    }
    public static void main(String[] args){
        Connection conn = new ConnectionTest().getConn();
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar ca=Calendar.getInstance();
            ca.setTime(sdf.parse("2018-08-2 6:30:00"));
            for (int i = 0; i <= 99; i++){
                ca.add(Calendar.MINUTE, 10);
                String time = sdf.format(ca.getTime());
                String sql = "insert into article(atitle,uid,brief,addtime,readnumber,state,isbanner,category)" +
                        " (select atitle,?,acontent,?,500,1,0,7 from article_lives limit ?,10)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1,"49bf3348daa74b1aa25c15db0327b173");
                st.setString(2,time);
                st.setInt(3,i*10);
                st.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
