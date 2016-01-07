package zxx.com.timing.myapplication.logic;


import java.util.ArrayList;
import java.util.Random;

import zxx.com.timing.myapplication.Utils.ArrayListUtils;
import zxx.com.timing.myapplication.Utils.SlideUtils;

/**
 * Created by zhangxiaoxu on 2015/7/30.
 */
public class DownSlide {
    public static ArrayList<Integer> lineList = new ArrayList<Integer>();

    public static void downSliding(ArrayList<Integer> arrayList) {

        ArrayList<Integer> oldList = new ArrayList<Integer>();
        for (int i = 0; i < arrayList.size(); i++) {
            oldList.add(arrayList.get(i));
        }

        for (int i = 0; i < 4; i++) {
            lineList.clear();
            lineList.add(arrayList.get(i + 12));
            lineList.add(arrayList.get(i + 8));
            lineList.add(arrayList.get(i + 4));
            lineList.add(arrayList.get(i));
            SlideUtils.slideSpace(lineList);
            SlideUtils.slideMerge(lineList);

            for (int j = 0; j < 4; j++) {
                arrayList.set(i + 12 - 4 * j, lineList.get(j));
            }
        }
        if (!ArrayListUtils.isSame(arrayList, oldList)) {
            SlideUtils.addNewNumber(arrayList);
        }

    }

}
