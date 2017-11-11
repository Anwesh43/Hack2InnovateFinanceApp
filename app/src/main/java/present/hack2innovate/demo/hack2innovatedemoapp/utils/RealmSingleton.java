package present.hack2innovate.demo.hack2innovatedemoapp.utils;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class RealmSingleton {
    private static Realm realm;
    public static void init(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }
    public static Realm getInstance() {
        return realm;
    }
}
