package present.hack2innovate.demo.hack2innovatedemoapp.dao;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import present.hack2innovate.demo.hack2innovatedemoapp.models.User;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class UserDao implements BaseDao<User>{
    private Realm realm;
    public UserDao(Realm realm) {
        this.realm = realm;
    }
    public void create(User user) {
        this.realm.beginTransaction();
        this.realm.insert(user);
        this.realm.commitTransaction();
    }
    public List<User> findAll() {
        List<User> users = new LinkedList<>();
        RealmResults<User> userRealmResults = realm.where(User.class).findAll();
        for(User user:userRealmResults) {
            users.add(user);
        }
        return users;
    }
}
