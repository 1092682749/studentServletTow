package aaa;

import java.util.*;

public class CountNumber {
    public static void main(String[] args){
        Integer sum = 0;
        for (int i = 1; i <=4; i++){
            for (int j = 1; j <=4; j++){
                for (int k = 1; k <=4; k++){
                    if (i != j && i != k && j != k){
                       sum++;
                       System.out.println( i*100 + j*10 + k);
                    }
                }
            }
        }
        System.out.println("一共有："+sum+"个");
    }
}
