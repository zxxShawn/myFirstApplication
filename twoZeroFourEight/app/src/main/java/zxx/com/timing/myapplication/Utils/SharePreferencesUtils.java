package zxx.com.timing.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import zxx.com.timing.myapplication.MainActivity;

/**
 * Created by zhangxiaoxu on 2015/8/4.
 */
public class SharePreferencesUtils {

    public static void setSharePreferences(Long score){
        SharedPreferences.Editor editor = MainActivity.sp.edit();
        editor.putLong(MainActivity.HIGHEST_SCORE,score);
        editor.commit();
    }
    public static long getSharePreferences(){
        long hisScore = MainActivity.sp.getLong(MainActivity.HIGHEST_SCORE,0);
        return hisScore;
    }
}
