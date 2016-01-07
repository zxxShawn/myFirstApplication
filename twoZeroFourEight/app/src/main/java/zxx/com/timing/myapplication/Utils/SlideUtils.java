package zxx.com.timing.myapplication.Utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import zxx.com.timing.myapplication.MainActivity;
import zxx.com.timing.myapplication.Utils.ArrayListUtils;

/**
 * Created by zhangxiaoxu on 2015/7/30.
 */
public class SlideUtils {

    /**
     * 去除空格
     *
     * @param arrayList
     */
    public static void slideSpace(ArrayList<Integer> arrayList) {
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            if ((int) iterator.next() == 0) {
                iterator.remove();
            }
        }
        while (arrayList.size() < 4) {
            arrayList.add(0);
        }
    }

    /**
     * 相同的数合并
     *
     * @param arrayList
     */
    public static void slideMerge(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size() - 1; i++) {
            if (arrayList.get(i) != 0 && arrayList.get(i).equals(arrayList.get(i + 1))) {
                arrayList.set(i, arrayList.get(i) * 2);
                arrayList.remove(i + 1);
                arrayList.add(0);
                MainActivity.score +=arrayList.get(i);
            }
        }
        MainActivity.tvScore.setText(MainActivity.score+"");
    }

    public static void addNewNumber(ArrayList<Integer> arrayList) {
        int emptyNum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == 0) {
                emptyNum++;
            }
        }
        if (emptyNum == 0) {
            return;
        } else if (emptyNum == 1) {
            Random random = new Random();
            int i = 1;
            while (i > 0) {
                int position = random.nextInt(16);
                if (arrayList.get(position) == 0) {
                    int num = 0;
                    Random rd = new Random();
                    num = rd.nextInt(2);
                    if (num == 0) {
                        arrayList.set(position, 2);
                    } else {
                        arrayList.set(position, 4);
                    }
                    i--;
                }
            }
        } else {
            Random random = new Random();
            Random numberRan = new Random();
            int i = numberRan.nextInt(2) + 1;
            while (i > 0) {
                int position = random.nextInt(16);
                if (arrayList.get(position) == 0) {
                    int num = 0;
                    Random rd = new Random();
                    num = rd.nextInt(2);
                    if (num == 0) {
                        arrayList.set(position, 2);
                    } else {
                        arrayList.set(position, 4);
                    }
                    i--;
                }
            }
        }
    }
}
