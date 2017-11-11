package present.hack2innovate.demo.hack2innovatedemoapp.viewpresenter;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import present.hack2innovate.demo.hack2innovatedemoapp.MainActivity;
import present.hack2innovate.demo.hack2innovatedemoapp.R;
import present.hack2innovate.demo.hack2innovatedemoapp.dao.UserDao;
import present.hack2innovate.demo.hack2innovatedemoapp.models.User;
import present.hack2innovate.demo.hack2innovatedemoapp.service.UserService;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class UserPresenter {
    private MainActivity activity;
    private UserDao userDao;
    private UserService userService;
    private TextView textView;
    public UserPresenter(MainActivity activity) {
        this.activity = activity;
        Realm.init(activity);
        Realm realm = Realm.getDefaultInstance();
        userDao = new UserDao(realm);
        Retrofit retrofit = RetrofitSingleton.getInstance();
        textView = (TextView)activity.findViewById(R.id.users);
        userService = retrofit.create(UserService.class);
    }
    private void displayUsers(List<User> users) {
        StringBuilder msg = new StringBuilder();
        for (User user : users) {
            msg.append(user.toString() + "\n");
            userDao.create(user);
        }
        textView.setText(msg);
    }
    public void fetchAndSaveUser() {
        if(userDao.findAll().size() > 0) {
            Toast.makeText(activity,""+userDao.findAll().size(),Toast.LENGTH_SHORT).show();
            displayUsers(userDao.findAll());

        }
        else {
            userService.getUsers().enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> users = response.body();
                    displayUsers(users);
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }
    }
}
