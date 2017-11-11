package present.hack2innovate.demo.hack2innovatedemoapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import present.hack2innovate.demo.hack2innovatedemoapp.constants.AppConstants;
import present.hack2innovate.demo.hack2innovatedemoapp.dao.SmsResponseDao;
import present.hack2innovate.demo.hack2innovatedemoapp.dao.UserDao;
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsResponse;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.RealmSingleton;
import retrofit2.http.Path;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class CardsView extends View{
    private List<DetailsCard> detailsCard = new ArrayList<>();
    private SmsResponseDao smsResponseDao;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CardsView(Context context, AttributeSet attrs) {
        super(context,attrs);
        smsResponseDao = new SmsResponseDao(RealmSingleton.getInstance());
        initCardDetails();
    }
    public CardsView(Context context) {
        super(context);
        smsResponseDao = new SmsResponseDao(RealmSingleton.getInstance());
        initCardDetails();
    }
    public void initCardDetails() {
        List<SmsResponse> responses = smsResponseDao.findAll();
        float b_money = 0, p_money = 0,all_money = 0;
        String curr_type = "INR";
        for(SmsResponse smsResponse:responses) {
            if(smsResponse.isPersonal_account()) {
                p_money += smsResponse.getExpense();
            }
            else {
                b_money += smsResponse.getExpense();
            }
            all_money += smsResponse.getExpense();
        }
        detailsCard.add(new DetailsCard("All Expense",curr_type,all_money));
        detailsCard.add(new DetailsCard("Personal Expense",curr_type,p_money));
        detailsCard.add(new DetailsCard("Business Expense",curr_type,b_money));
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#E0E0E0"));
        if(detailsCard.size()>0) {
            float size = 3*canvas.getHeight()/(4*detailsCard.size()+1);
            float y = size/3;
            for (DetailsCard detailCard : detailsCard) {
                detailCard.draw(canvas, paint, canvas.getWidth()/2-size/2, y,size,size);
                y+= size+size/3;
            }
        }
    }
    private class DetailsCard {
        private String text;
        private float price;
        private String currency_type;
        public DetailsCard(String text,String currency_type,float price) {
            this.text = text;
            this.price = price;
            this.currency_type = currency_type;
        }
        public void draw(Canvas canvas,Paint paint,float x,float y,float w,float h) {
            android.graphics.Path path = new android.graphics.Path();
            path.addRoundRect(new RectF(x,y,x+w,y+h),Math.max(w,h)/10,Math.max(w,h)/10, android.graphics.Path.Direction.CW);
            canvas.clipPath(path);
            paint.setColor(Color.parseColor("#1DE9B6"));
            canvas.drawRect(new RectF(x,y,x+w,y+h/2),paint);
            paint.setTextSize(Math.min(w,h)/15);
            paint.setColor(Color.WHITE);
            canvas.drawText(text,x+w/2-paint.measureText(text)/2,y+h/4,paint);
            canvas.drawRect(new RectF(x,y+h/2,x+w,y+h),paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(""+AppConstants.currencyMap.get(currency_type)+price,x+w/2-paint.measureText(text)/2,y+3*h/4,paint);
        }
        public int hashCode() {
            return text.hashCode()+(int)price;
        }
    }
}
