package demo.enumration;

/**
 * @classDesc: 向enum中添加方法和自定义构造函数
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 16:51:15
 * @version: v1.0
 */
public enum EnumDemo2 {
    MONDAY("星期一"),
    TUESDAY("星期一"),
    WEDNESDAY("星期一"),
    THURSDAY("星期一"),
    FRIDAY("星期一"),
    SATURDAY("星期一"),
    SUNDAY("星期一");//记住要用分号结束

    private String desc;//中文描述

    /**
     * 私有构造，防止被外部调用
     * @param desc 中文描述
     */
    private EnumDemo2(String desc) {
        this.desc = desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return 中文描述
     */
    public String getDesc() {
        return desc;
    }

    public static void main(String[] args){
        for (EnumDemo2 day: EnumDemo2.values()) {
            System.out.println("name:"+day.name()+
                    ",desc:"+day.getDesc());
        }
    }
}
