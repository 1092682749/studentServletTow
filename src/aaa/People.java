package aaa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class People {
    public void movePole() throws SQLException, ParseException {
        Connection conn = new ConnectionTest().getConn();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca=Calendar.getInstance();
        ca.setTime(simpleDateFormat.parse("2018-08-7 6:30:00"));
        for (int i = 0; i < 100; i++){
            ca.add(Calendar.MINUTE,10);
            String time = simpleDateFormat.format(ca.getTime());
            String sql = "insert into article (atitle, acontent, uid, coverimg, brief, addtime, readnumber, state, isbanner, category, source, keyword, author) " +
                    "(select atitle,acontent,?,coverimg,atitle,?,0,2,0,6,source_from,?,author from article_people limit 10)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"49bf3348daa74b1aa25c15db0327b173");
            ps.setString(2,time);
            ps.setString(3,"人物");
            ps.execute();
        }
    }
}

