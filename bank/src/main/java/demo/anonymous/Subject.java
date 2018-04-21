package demo.anonymous;

public interface Subject {
    public abstract void teach();
}

class Math implements Subject{

    @Override
    public void teach() {
        System.out.println("几何原理");
    }
}
