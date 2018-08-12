package aaa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regx {
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("([\\【].+[\\】])(.+)");
        Matcher matcher = pattern.matcher("【动态 | ZG.TOP上线Loopring   】ZG.TOP将上线Loopring (LRC)其重提时间为");
        if (matcher.find()){
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            System.out.println(g1);
            System.out.println(g2);
        }
    }
}
