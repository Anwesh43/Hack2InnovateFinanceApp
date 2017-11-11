package present.hack2innovate.demo.hack2innovatedemoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import present.hack2innovate.demo.hack2innovatedemoapp.utils.MainViewContainer;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.RealmSingleton;
import present.hack2innovate.demo.hack2innovatedemoapp.viewpresenter.UserPresenter;
import present.hack2innovate.demo.hack2innovatedemoapp.views.BottomMenuView;
import present.hack2innovate.demo.hack2innovatedemoapp.views.CardsView;
import present.hack2innovate.demo.hack2innovatedemoapp.views.PieChartView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealmSingleton.init(this);
//        UserPresenter userPresenter = new UserPresenter(this);
//        userPresenter.fetchAndSaveUser();
        setContentView(R.layout.activity_main);
        initUI();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},0);
            startService(new Intent(this, SmsReceivingService.class));
        }
        else {
            startService(new Intent(this, SmsReceivingService.class));
        }
    }
    private void initUI() {

        final MainViewContainer mainViewContainer = new MainViewContainer((LinearLayout)findViewById(R.id.view_container));
        final LinearLayout bottomView = (LinearLayout)findViewById(R.id.bottom_menu);
        final CardsView breakupView = new CardsView(this);
        final PieChartView personalChartView = new PieChartView(this,true);
        final PieChartView businessChartView = new PieChartView(this,false);

        BottomMenuView bottomMenuView = new BottomMenuView(this);
        bottomMenuView.addMenu("Breakup", new BottomMenuView.BottomMenuOnClickListener() {
            @Override
            public void onClick() {
                mainViewContainer.addAnotherView(breakupView);
            }
        });
        bottomMenuView.addMenu("Personal", new BottomMenuView.BottomMenuOnClickListener() {
            @Override
            public void onClick() {
                mainViewContainer.addAnotherView(personalChartView);
            }
        });
        bottomMenuView.addMenu("Bussiness", new BottomMenuView.BottomMenuOnClickListener() {
            @Override
            public void onClick() {
                mainViewContainer.addAnotherView(businessChartView);
            }
        });
        bottomView.addView(bottomMenuView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mainViewContainer.setDefaultView(breakupView);
        bottomView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("height of bottomview",""+bottomView.getHeight());
            }
        });
        mainViewContainer.getLinearLayout().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("height of mainview",""+mainViewContainer.getLinearLayout().getHeight());
            }
        });
    }
    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl("http://172.16.14.245:8000/").addConverterFactory(GsonConverterFactory.create()).build();
    }
}
