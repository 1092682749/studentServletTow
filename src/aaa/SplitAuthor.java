package aaa;

import java.sql.*;

public class SplitAuthor {
    public void splitAndSave() throws SQLException {
        Connection conn = new ConnectionTest().getConn();
        Statement st = conn.createStatement();
        ResultSet set = st.executeQuery("select * from article_people");
        PreparedStatement ps = conn.prepareStatement("update article_people set author = ? where atitle = ?");
        while (set.next()){
            String author = set.getString("author");
            String[] ss = author.split(" ");
            if (ss.length > 0){
                String title = set.getString("atitle");
                System.out.println(ss[ss.length-1]);
                ps.setString(1,ss[ss.length-1]);
                ps.setString(2,title);
                ps.execute();
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        new SplitAuthor().splitAndSave();
    }
}
