package aaa;

import java.util.ArrayList;
import java.util.List;
class Fruit{}
class Apple extends Fruit{}
class Orange extends Fruit{}
public class FList {
    @SuppressWarnings("nuchecked")
    public static void main(String[] args){
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        List<? extends Fruit> flist = apples;
        Fruit apple = flist.get(0);
        flist = new ArrayList<Orange>();
//        flist.add(new Orange());
//        flist.add(new Orange());
        List<? super Fruit> fflist = new ArrayList<Fruit>();
        fflist.add(new Apple());
        Fruit fruit = (Fruit) fflist.get(0);
    }
}
