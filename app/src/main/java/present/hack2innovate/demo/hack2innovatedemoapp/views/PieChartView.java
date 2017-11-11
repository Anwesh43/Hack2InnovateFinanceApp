package present.hack2innovate.demo.hack2innovatedemoapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import present.hack2innovate.demo.hack2innovatedemoapp.constants.AppConstants;
import present.hack2innovate.demo.hack2innovatedemoapp.dao.SmsResponseDao;
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsResponse;
import present.hack2innovate.demo.hack2innovatedemoapp.utils.RealmSingleton;

/**
 * Created by anweshmishra on 12/11/17.
 */

public class PieChartView extends View {
    private PieChart pieChart;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SmsResponseDao smsResponseDao;
    private String word = "Business Expense";
    public PieChartView(Context context,boolean personalAccount) {
        super(context);
        smsResponseDao = new SmsResponseDao(RealmSingleton.getInstance());
        initPieChart(personalAccount);
        if(personalAccount) {
            word = "Personal Expense";
        }
    }
    public void initPieChart(boolean personalAccount) {
        List<PieChartValue> pieChartValues = new ArrayList<>();
        List<SmsResponse> responses = smsResponseDao.getPersonalAccountTypes(personalAccount);
        if(responses.size() > 0) {
            SmsResponse response = responses.get(0);
            String curr_type = response.getType();
            float curr_value = response.getExpense();
            for(int i=1;i<responses.size();i++) {
                SmsResponse smsResponse = responses.get(i);
                String type = smsResponse.getType();
                if(type.equals(curr_type)) {
                    curr_value += smsResponse.getExpense();
                }
                else {
                    pieChartValues.add(new PieChartValue(curr_type,curr_value));
                    curr_value = smsResponse.getExpense();
                    curr_type = type;
                }
            }
            pieChartValues.add(new PieChartValue(curr_type,curr_value));
        }
        pieChart = new PieChart(pieChartValues);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#E0E0E0"));
        paint.setColor(Color.BLACK);
        paint.setTextSize(canvas.getHeight()/25);
        canvas.drawText(word,canvas.getWidth()/2-paint.measureText(word)/2,canvas.getHeight()/10,paint);
        pieChart.draw(canvas,paint);
    }
    private class PieChart {
        private List<PieChartValue> pieChartValues = new ArrayList<>();
        private int sum = 0;
        public PieChart(List<PieChartValue> pieChartValues) {
            this.pieChartValues = pieChartValues;
            countSum();
        }
        private void countSum() {
            for(PieChartValue pieChartValue:pieChartValues) {
                sum += pieChartValue.value;
            }
        }
        public void draw(Canvas canvas, Paint paint) {
            int i = 0;
            float w = canvas.getWidth(),h = canvas.getHeight();
            float x = w/2,y = h/3,r = w/4,deg = 0,rx = w/10,ry = h/3+w/4+w/20,gap = h/2-3*w/4;
            for(PieChartValue pieChartValue:pieChartValues) {
                paint.setColor(Color.parseColor(AppConstants.colorCodes[i]));
                float sweepDeg = 360*(pieChartValue.value/sum);
                canvas.drawArc(new RectF(x-r,y-r,x+r,y+r),deg,sweepDeg,true,paint);
                deg+=sweepDeg;
                canvas.drawRoundRect(new RectF(rx-w/30,ry-w/30,rx+w/30,ry+w/30),w/80,w/80,paint);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(h/140);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawLine(rx+w/30+w/20,ry,rx+w/30+w/10,ry,paint);
                paint.setTextSize(w/25);
                canvas.drawText(pieChartValue.label,rx+w/30+w/6,ry+w/50,paint);
                ry += w/10;
                i++;
            }
        }
    }
    private class PieChartValue {
        private String label;
        private float value;
        public PieChartValue(String label,float value) {
            this.label = label;
            this.value = value;
        }
        public int hashCode() {
            return label.hashCode()+(int)value;
        }
    }
}
