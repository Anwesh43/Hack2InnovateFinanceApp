package present.hack2innovate.demo.hack2innovatedemoapp.models;

import java.util.Locale;

import io.realm.RealmObject;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class User extends RealmObject {
    private String name;
    private Integer age;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String toString() {
        return String.format(Locale.ENGLISH,"My name is %s and age is %d",name,age);
    }
}
