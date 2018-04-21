package demo.anonymous;

public class Person {
    public void eat() {
        System.out.println("吃饭");
    }
}

class Student extends Person{
    @Override
    public void eat() {
        System.out.println("吃八宝粥");
    }
}
