package present.hack2innovate.demo.hack2innovatedemoapp.models;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class SmsRequest {
    private String sms_message,sms_sender,time;

    public String getSms_message() {
        return sms_message;
    }

    public void setSms_message(String sms_message) {
        this.sms_message = sms_message;
    }

    public String getSms_sender() {
        return sms_sender;
    }

    public void setSms_sender(String sms_sender) {
        this.sms_sender = sms_sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SmsRequest(String sms_message, String sms_sender, String time) {
        this.sms_message = sms_message;
        this.sms_sender = sms_sender;
        this.time = time;

    }
}
