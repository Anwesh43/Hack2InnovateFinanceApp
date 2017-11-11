package present.hack2innovate.demo.hack2innovatedemoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import present.hack2innovate.demo.hack2innovatedemoapp.utils.RealmSingleton;
import present.hack2innovate.demo.hack2innovatedemoapp.viewpresenter.UserPresenter;
import present.hack2innovate.demo.hack2innovatedemoapp.views.CardsView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmSingleton.init(this);
        setContentView(new CardsView(this));
//        UserPresenter userPresenter = new UserPresenter(this);
//        userPresenter.fetchAndSaveUser();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},0);
            startService(new Intent(this, SmsReceivingService.class));
        }
        else {
            startService(new Intent(this, SmsReceivingService.class));
        }
    }
    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl("http://172.16.14.245:8000/").addConverterFactory(GsonConverterFactory.create()).build();
    }
}
