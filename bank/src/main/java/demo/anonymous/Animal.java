package demo.anonymous;

abstract class Animal {
    public abstract void bark();
}


class Dog extends  Animal{

    @Override
    public void bark() {
        System.out.println("汪汪...");
    }
}