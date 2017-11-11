package present.hack2innovate.demo.hack2innovatedemoapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

import present.hack2innovate.demo.hack2innovatedemoapp.MainActivity;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class SmsReceiverUtil {
    public static void init(Context context, final OnReceiveSmsListener onReceiveSmsListener) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle  = intent.getExtras();
                if(bundle != null) {
                    Object[] pduDatas = (Object[]) bundle.get("pdus");
                    for(Object pduData:pduDatas) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pduData);
                        String smsBody = smsMessage.getMessageBody();
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                        onReceiveSmsListener.onReceiveSms(smsBody,sender,new Date().toString());
                    }
                }
            }
        },intentFilter);
    }
    public interface OnReceiveSmsListener {
        void onReceiveSms(String message, String sender, String time);
    }
}
