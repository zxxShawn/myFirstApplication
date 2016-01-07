package zxx.com.timing.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by zhangxiaoxu on 2015/7/31.
 */
public class TGridView extends GridView{
    public TGridView(Context context) {
        super(context);
    }

    public TGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }
}
