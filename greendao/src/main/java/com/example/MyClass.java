package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) {
        //生成数据库的实体类，对应的是数据库的表
        Schema schema = new Schema(1, "com.my.greendao_search.bean");
        addCase(schema);
        schema.setDefaultJavaPackageDao("com.my.greendao_search.dao");
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据库的表
     * @param schema
     */
    private static void addCase(Schema schema) {
        Entity entity = schema.addEntity("Case"); //创建数据库表
        entity.addIdProperty();//主键 是 int类型
        entity.addStringProperty("name");
        entity.addLongProperty("time");
        entity.addIntProperty("age");
    }
}
