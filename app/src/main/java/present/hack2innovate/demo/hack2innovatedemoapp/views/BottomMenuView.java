package present.hack2innovate.demo.hack2innovatedemoapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 12/11/17.
 */

public class BottomMenuView extends View{
    private ConcurrentLinkedQueue<BottomMenu> bottomMenus = new ConcurrentLinkedQueue<>();
    private int time = 0,i=0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BottomMenuView(Context context) {
        super(context);
    }
    public void addMenu(String text,BottomMenuOnClickListener bottomMenuOnClickListener) {
        bottomMenus.add(new BottomMenu(text,bottomMenuOnClickListener));
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#2980b9"));
        if(time == 0 && bottomMenus.size()>0) {
            float w = canvas.getWidth(), h = canvas.getHeight(),gap = w/bottomMenus.size(),x=gap/2,y = h/2;
            for(BottomMenu bottomMenu:bottomMenus) {
                bottomMenu.setDimension(x,y,gap,h);
                x+=gap;
            }
        }
        int index = 0;
        for(BottomMenu bottomMenu:bottomMenus) {
            bottomMenu.draw(canvas,paint,(i==index)?Color.WHITE:Color.parseColor("#E0E0E0"));
            index++;
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int index = 0;
            for(BottomMenu bottomMenu:bottomMenus) {
                if(i == index) {
                    continue;
                }
                if(bottomMenu.handleTap(event.getX(),event.getY())) {
                    bottomMenu.bottomMenuOnClickListener.onClick();
                    i = index;
                    postInvalidate();
                }
                index++;
            }
        }
        return true;
    }
    private class BottomMenu {
        private String text;
        private BottomMenuOnClickListener bottomMenuOnClickListener;
        private float x,y,w,h;
        public BottomMenu(String text,BottomMenuOnClickListener bottomMenuOnClickListener) {
            this.text = text;
            this.bottomMenuOnClickListener = bottomMenuOnClickListener;
        }
        public void setDimension(float x,float y,float w,float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        public void draw(Canvas canvas, Paint paint,int color) {
            paint.setColor(color);
            paint.setTextSize(h/2);
            canvas.drawText(text,x-paint.measureText(text)/2,y,paint);
        }
        public boolean handleTap(float x,float y) {
            return x>=this.x-w/2 && x<=this.x+w/2 && y>=this.y - h/2 && y<=this.y+h/2;
        }
    }
    public interface BottomMenuOnClickListener {
        void onClick();
    }
}
