package zxx.com.timing.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import zxx.com.timing.myapplication.Utils.GestureUtils;
import zxx.com.timing.myapplication.Utils.SharePreferencesUtils;
import zxx.com.timing.myapplication.logic.DownSlide;
import zxx.com.timing.myapplication.logic.GameStart;
import zxx.com.timing.myapplication.logic.LeftSlide;
import zxx.com.timing.myapplication.logic.RightSlide;
import zxx.com.timing.myapplication.logic.UpSlide;


public class MainActivity extends ActionBarActivity{

    private GridView mGvMain;
    private Button btnStart;
    private GestureUtils.Screen screen;
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private GestureDetector gestureDetector;
    public static TextView tvScore;
    public static TextView tvHisScore;
    public static long score = 0;
    public static final String HIGHEST_SCORE = "highest_score";
    public static SharedPreferences sp;
    public static long hisScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(HIGHEST_SCORE,MODE_PRIVATE);
        hisScore = SharePreferencesUtils.getSharePreferences();
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvHisScore = (TextView) findViewById(R.id.tv_his_score);
        GameStart.start(arrayList);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void init() {
        gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            public boolean onDown(MotionEvent e) {
                return false;
            }
            public void onShowPress(MotionEvent e) {
            }
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                screen = GestureUtils.getScreenPix(MainActivity.this);
                float x = e2.getX() - e1.getX();
                float y = e2.getY() - e1.getY();
                //限制必须得划过屏幕的1/8才能算划过
                float x_limit = screen.widthPixels / 8;
                float y_limit = screen.heightPixels / 8;
                float x_abs = Math.abs(x);
                float y_abs = Math.abs(y);
                if(x_abs >= y_abs){
                    //gesture left or right
                    if(x > x_limit || x < -x_limit){
                        if(x>0){
                            //right
                            RightSlide.rightSliding(arrayList);
                            mGvMain.setAdapter(new MyAdapter(arrayList));
                            if (isDead()) {
                                if(score>hisScore){
                                    SharePreferencesUtils.setSharePreferences(score);
                                }
                                showfailDialog();
                            }
                        }else if(x<=0){
                            //left
                            LeftSlide.leftSliding(arrayList);
                            mGvMain.setAdapter(new MyAdapter(arrayList));
                            if (isDead()) {
                                if(score>hisScore){
                                    SharePreferencesUtils.setSharePreferences(score);
                                }
                                showfailDialog();
                            }
                        }
                    }
                }else{
                    //gesture down or up
                    if(y > y_limit || y < -y_limit){
                        if(y>0){
                            DownSlide.downSliding(arrayList);
                            mGvMain.setAdapter(new MyAdapter(arrayList));
                            if (isDead()) {
                                if(score>hisScore){
                                    SharePreferencesUtils.setSharePreferences(score);
                                }
                                showfailDialog();
                            }
                        }else if(y<=0){
                            UpSlide.upSliding(arrayList);
                            mGvMain.setAdapter(new MyAdapter(arrayList));
                            if (isDead()) {
                                if(score>hisScore){
                                    SharePreferencesUtils.setSharePreferences(score);
                                }
                                showfailDialog();
                            }
                        }
                    }
                }
                return true;
            }
        });
        btnStart = (Button) findViewById(R.id.btn_start);
        mGvMain = (GridView) findViewById(R.id.gv_main);
        mGvMain.setAdapter(new MyAdapter(arrayList));

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("重新开始");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameStart.start(arrayList);
                        mGvMain.setAdapter(new MyAdapter(arrayList));
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    private class MyAdapter extends BaseAdapter {

        private ArrayList<Integer> mData = new ArrayList<>();

        public MyAdapter(ArrayList<Integer> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.gv_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_num);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (mData.get(position) == 0) {
                viewHolder.textView.setText("");
                viewHolder.textView.setBackgroundResource(R.color.color_0);
            } else {
                viewHolder.textView.setText(mData.get(position) + "");
                switch (mData.get(position)){
                    case 2:
                        viewHolder.textView.setBackgroundResource(R.color.color_2);
                        break;
                    case 4:
                        viewHolder.textView.setBackgroundResource(R.color.color_4);
                        break;
                    case 8:
                        viewHolder.textView.setBackgroundResource(R.color.color_8);
                        break;
                    case 16:
                        viewHolder.textView.setBackgroundResource(R.color.color_16);
                        break;
                    case 32:
                        viewHolder.textView.setBackgroundResource(R.color.color_32);
                        break;
                    case 64:
                        viewHolder.textView.setBackgroundResource(R.color.color_64);
                        break;
                    case 128:
                        viewHolder.textView.setBackgroundResource(R.color.color_128);
                        break;
                    default:
                        viewHolder.textView.setBackgroundResource(R.color.color_other);
                        break;
                }
            }
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView textView;
    }

    public boolean isDead(){
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).equals(0)){
                return false;
            }
        }
        for(int i = 0;i<4;i++){
            for(int j = 0;j<3;j++){
                if(arrayList.get(i*4+j).equals((arrayList.get(i*4+j+1)))){
                    return false;
                }
            }
        }
        for (int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                if(arrayList.get(i*4+j).equals((arrayList.get(i*4+j+4)))){
                    return false;
                }
            }
        }
        return true;
    }

    public void showfailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("游戏结束");
        builder.setNegativeButton("再来一局", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameStart.start(arrayList);
                mGvMain.setAdapter(new MyAdapter(arrayList));
            }
        });
        builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.show();
    }
}
