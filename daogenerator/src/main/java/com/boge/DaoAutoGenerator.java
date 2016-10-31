package com.boge;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoAutoGenerator {

    public static void main(String[] args){

        Schema schema = new Schema(1 , "com.boge.entity");
        schema.setDefaultJavaPackageDao("com.boge.dao");

        addLocalAndRecomendBook(schema);

        try {
            new DaoGenerator().generateAll(schema , "./app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addLocalAndRecomendBook(Schema schema) {
        Entity entity = schema.addEntity("LocalAndRecomendBook");

        entity.addIdProperty();
        entity.addStringProperty("Path");
        entity.addLongProperty("size");
        entity.addStringProperty("bookId");
        entity.addStringProperty("cover");
        entity.addStringProperty("title").notNull();
        entity.addStringProperty("lastChapter");
        entity.addBooleanProperty("isLocal");
        entity.addBooleanProperty("hasUp");
    }

}
