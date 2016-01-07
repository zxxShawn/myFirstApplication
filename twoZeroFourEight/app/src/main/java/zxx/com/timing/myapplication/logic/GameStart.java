package zxx.com.timing.myapplication.logic;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Random;

import zxx.com.timing.myapplication.MainActivity;
import zxx.com.timing.myapplication.Utils.SharePreferencesUtils;

/**
 * Created by zhangxiaoxu on 2015/7/30.
 */
public class GameStart extends Activity {
    public static int position1;
    public static int position2;
    public static int sencondnum;

    public static void start(ArrayList<Integer> arrayList) {
        MainActivity.score = 0;
        MainActivity.tvScore.setText(MainActivity.score+"");
        MainActivity.hisScore = SharePreferencesUtils.getSharePreferences();
        MainActivity.tvHisScore.setText(MainActivity.hisScore+"");
        arrayList.clear();
        for (int i = 0; i < 16; i++) {
            arrayList.add(0);
        }
        Random random = new Random();
        position1 = random.nextInt(16);
        position2 = random.nextInt(16);
        while (position1 == position2) {
            position2 = random.nextInt(16);
        }
        sencondnum = random.nextInt(2);
        arrayList.set(position1, 2);
        if (sencondnum == 0) {
            arrayList.set(position2, 2);
        } else {
            arrayList.set(position2, 4);
        }
    }
}
