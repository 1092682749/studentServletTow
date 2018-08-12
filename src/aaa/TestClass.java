package aaa;

public class TestClass {
    TestClass(){}
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class clazz = TestClass.class;
        TestClass t = (TestClass) clazz.newInstance();
    }
}
