package zxx.com.timing.myapplication.Utils;

import java.util.ArrayList;

/**
 * Created by zhangxiaoxu on 2015/7/30.
 */
public class ArrayListUtils {
    public static boolean isSame(ArrayList a,ArrayList b){
        if(a.size()!=b.size()){
            return false;
        }else{
            for(int i=0; i<a.size();i++){
                if(a.get(i)!=b.get(i)){
                    return false;
                }
            }
            return true;
        }
    }
}
