package demo.enumration;

import java.util.Arrays;

public enum EnumDemo3 {

    DOG("动物", "狗"), PIG("动物", "猪"), CAT("动物", "猫"),
    APPLE("水果", "苹果"), ORANGE("水果", "橘子"), PEAR("水果", "雪梨");

    String type;
    String desc;

    EnumDemo3(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        // 列出枚举中的所有水果
        Arrays.stream(EnumDemo3.values()).filter((e) -> e.type.equals("水果")).forEach(System.out::println);
    }
}
