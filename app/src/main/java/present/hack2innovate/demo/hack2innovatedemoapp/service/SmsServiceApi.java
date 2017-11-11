package present.hack2innovate.demo.hack2innovatedemoapp.service;

/**
 * Created by anweshmishra on 11/11/17.
 */
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsRequest;
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by anweshmishra on 11/11/17.
 */

public interface SmsServiceApi {
    @POST("/send_sms/")
    Call<SmsResponse> sendSms(@Body SmsRequest smsRequest);
}