package present.hack2innovate.demo.hack2innovatedemoapp;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsRequest;
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsResponse;
import present.hack2innovate.demo.hack2innovatedemoapp.service.SmsServiceApi;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.RetrofitSingleton;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.SmsReceiverUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class SmsReceivingService extends Service {
    private SmsServiceApi smsServiceApi;

    public Binder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    public void onCreate() {
        final Context context =  getApplicationContext();
        Retrofit retrofit = RetrofitSingleton.getInstance();
        final SmsServiceApi smsServiceApi = retrofit.create(SmsServiceApi.class);
        SmsReceiverUtil.init(context, new SmsReceiverUtil.OnReceiveSmsListener() {
            @Override
            public void onReceiveSms(String message, String sender, String time) {
                smsServiceApi.sendSms(new SmsRequest(message,sender,time)).enqueue(new Callback<SmsResponse>() {
                    @Override
                    public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                        Log.d("success",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<SmsResponse> call, Throwable t) {
                        Log.d("failure",t.getMessage());
                    }
                });
            }
        });
    }
}
