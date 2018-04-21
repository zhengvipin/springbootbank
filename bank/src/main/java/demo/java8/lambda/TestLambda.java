package demo.java8.lambda;

import org.junit.Test;

import java.util.*;

public class TestLambda {

    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() { // Comparator接口
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2); // 比较大小
            }
        };
        System.out.println(com.compare(4, 3)); // 1
        System.out.println(com.compare(3, 4)); // -1

        Set<Integer> set = new TreeSet<>(com);
        set.add(com.compare(4, 6));
        for (Integer i : set) System.out.println(i);
    }

    // Lambda表达式
    @Test
    public void test2() {
        //->左侧：Lambda表达式所需参数   ->右侧：Lambda表达式所执行的功能
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Set<Integer> set = new TreeSet<>(com);
        set.add(com.compare(4, 6));
        for (Integer i : set) System.out.println(i);
    }

    List<Employee> employees = Arrays.asList( // 数组转集合
            new Employee("张三", 18, 4444.44),
            new Employee("李四", 48, 8888.88),
            new Employee("王五", 26, 6666.11),
            new Employee("赵六", 32, 7777.22),
            new Employee("田七", 40, 9999.99)
    );

    // 需求：获取当前公司中员工年龄大于35的员工信息
    private List<Employee> filterEmployee(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getAge() >= 35) emps.add(emp);
        }
        return emps;
    }

    @Test
    public void test3() {
        List<Employee> list = filterEmployee(employees);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    // 需求： 获取当前公司中工资大于5000的员工信息
    private List<Employee> filterEmployee2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) emps.add(emp);
        }
        return emps;
    }

    // 优化方式一：策略设计模式
    private List<Employee> filterEmployee3(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (mp.test(emp)) emps.add(emp);
        }

        return emps;
    }

    @Test
    public void test4() {
        List<Employee> list = filterEmployee3(employees, new FilterEmployeeByAge());
        for (Employee emp : list) {
            System.out.println(emp);
        }
        System.out.println("---------------------------------------------------------");
        List<Employee> list2 = filterEmployee3(employees, new FilterEmployeeBySalary());
        for (Employee emp : list2) {
            System.out.println(emp);
        }
    }

    // 优化方式二：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployee3(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    // 优化方案三：Lambda表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployee3(employees, (e) -> e.getSalary() >= 5000);
        list.forEach(System.out::println);
    }

    @Test
    public void getNames() {
        // 获得当前公司中所有员工的姓名
        employees.stream().map(Employee::getName).forEach(System.out::println);
        // 获得当前公司中所有员工的年龄
        employees.stream().map(Employee::getAge).forEach(System.out::println);
        // 获得当前公司中所有员工的工资
        employees.stream().map(Employee::getSalary).forEach(System.out::println);
    }
}

