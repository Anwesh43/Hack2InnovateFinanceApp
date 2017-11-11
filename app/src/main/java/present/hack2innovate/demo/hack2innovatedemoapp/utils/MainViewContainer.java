package present.hack2innovate.demo.hack2innovatedemoapp.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by anweshmishra on 12/11/17.
 */

public class MainViewContainer {
    private LinearLayout linearLayout;
    public MainViewContainer(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }
    public void setDefaultView(View view) {
        if(linearLayout.getChildCount() == 0) {
            linearLayout.addView(view,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void addAnotherView(View view) {
        if(linearLayout.getChildCount() == 1) {
            linearLayout.removeAllViews();
            setDefaultView(view);

        }
    }
}
