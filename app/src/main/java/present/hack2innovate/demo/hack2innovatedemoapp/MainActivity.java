package present.hack2innovate.demo.hack2innovatedemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import present.hack2innovate.demo.hack2innovatedemoapp.viewpresenter.UserPresenter;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserPresenter userPresenter = new UserPresenter(this);
        userPresenter.fetchAndSaveUser();
    }
}
