package demo.enumration;

import java.util.Arrays;

/**
 * @classDesc: 枚举类型
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 15:37:58
 * @version: v1.0
 */
enum EnumDemo1 {
    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;

    public static void main(String[] args) {

        System.out.println(Arrays.toString(EnumDemo1.values()));

        //创建枚举数组
        EnumDemo1[] days = new EnumDemo1[]{EnumDemo1.MONDAY, EnumDemo1.TUESDAY, EnumDemo1.WEDNESDAY,
                EnumDemo1.THURSDAY, EnumDemo1.FRIDAY, EnumDemo1.SATURDAY, EnumDemo1.SUNDAY};

        for (int i = 0; i < days.length; i++) {
            System.out.println("day[" + i + "].ordinal():" + days[i].ordinal());
        }

        System.out.println("-------------------------------------");
        //通过compareTo方法比较,实际上其内部是通过ordinal()值比较的
        System.out.println("days[0].compareTo(days[1]):" + days[0].compareTo(days[1]));
        System.out.println("days[0].compareTo(days[1]):" + days[0].compareTo(days[2]));

        //获取该枚举对象的Class对象引用,当然也可以通过getClass方法
        Class<?> clazz = days[0].getDeclaringClass();
        System.out.println("clazz:" + clazz);

        System.out.println("-------------------------------------");

        //name()
        System.out.println("days[0].name():" + days[0].name());
        System.out.println("days[1].name():" + days[1].name());
        System.out.println("days[2].name():" + days[2].name());
        System.out.println("days[3].name():" + days[3].name());

        System.out.println("-------------------------------------");

        System.out.println("days[0].toString():" + days[0].toString());
        System.out.println("days[1].toString():" + days[1].toString());
        System.out.println("days[2].toString():" + days[2].toString());
        System.out.println("days[3].toString():" + days[3].toString());

        System.out.println("-------------------------------------");

        // 根据枚举类和枚举名获得枚举常量
        EnumDemo1 d = Enum.valueOf(EnumDemo1.class, days[0].name());
        EnumDemo1 d2 = EnumDemo1.valueOf(EnumDemo1.class, days[0].name());
        System.out.println("d:" + d);
        System.out.println("d2:" + d2);
    }
}


