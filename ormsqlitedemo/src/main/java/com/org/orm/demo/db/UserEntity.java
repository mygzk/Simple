package com.org.orm.demo.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 属性说明文档
 * http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Local-Annotations
 */
@DatabaseTable(tableName = "User_Table")
public class UserEntity {
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

  //  private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  /*  public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
*/

    @Override
    public String toString() {

        String str = "id=" + id + "  name=" + name ;
        return str;
    }
}
