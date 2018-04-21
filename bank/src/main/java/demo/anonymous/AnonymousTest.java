package demo.anonymous;


public class AnonymousTest {
    public static void main(String[] args) {

        // 4.1.通过实体类创建匿名内部类对象
        Person person = new Person() {
            @Override
            public void eat() {
                System.out.println("吃八宝粥");
            }
        };
        person.eat();
        // 相当于创建该类的一个子类对象
        Student stu = new Student();
        stu.eat();

        // 4.2.通过抽象类创建匿名内部类对象
        Animal animal = new Animal() {
            @Override
            public void bark() {
                System.out.println("汪汪..");
            }
        };
        animal.bark();
        // 相当于定义了该抽象类的一个子类对象,并重写了抽象类中所有的抽象方法
        Dog dog = new Dog();
        dog.bark();

        //4.3.通过接口创建匿名内部类对象
        Subject subject = new Subject() {
            @Override
            public void teach() {
                System.out.println("几何原理");
            }
        };
        subject.teach();
        //相当于定义了该接口的一个实现类对象,并重写了接口中所有的抽象方法
        Math math = new Math();
        math.teach();
    }
}
