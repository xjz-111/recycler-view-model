package com.leslie.recylerviewmodel.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leslie.recylerviewmodel.MApplication;
import com.leslie.recylerviewmodel.test.data.Alphabet;
import com.leslie.recylerviewmodel.test.data.ImgData;
import com.leslie.recylerviewmodel.test.data.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 作者：xjzhao
 * 时间：2021-07-08 14:03
 */
public class Utils {
    public static int screenWidth;
    public static int screenHeight;

    public static String[] imgs = new String[]
            {       "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnews.chinaxiaokang.com%2Fuploads%2Fimage%2F20170426%2F1493170290679914.jpg&refer=http%3A%2F%2Fnews.chinaxiaokang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624176889&t=1eb6ccc333dd23b433bc9f6f15330e31",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F10572014182%2F0.jpg&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624176889&t=21a66caba42d1a5d5b2f7d88506cb088",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201903%2F12%2F20190312205108_4UwWX.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624176889&t=66fc164d5ca548b021af524427d60627",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload.trends.com.cn%2F2019%2F0604%2F1559619714257.jpg&refer=http%3A%2F%2Fupload.trends.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624176889&t=54ca457e17d67fd2319a05d202ef44cc",
                    "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd10123%2F706%2Fw1075h1231%2F20200401%2F906b-irpunai4320158.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624176889&t=99fdfbceab6a6ea2555498bade3adf64",};
    public static Integer[][] params = new Integer[][]{new Integer[]{422, 300}, new Integer[]{653, 748}, new Integer[]{1600, 2097}, new Integer[]{600, 780}, new Integer[]{1075, 1231}};

    public static String[] videos = new String[]{
            "https://v-cdn.zjol.com.cn/280443.mp4",
            "https://v-cdn.zjol.com.cn/276982.mp4",
            "https://v-cdn.zjol.com.cn/276984.mp4",
            "https://v-cdn.zjol.com.cn/276985.mp4",
            "https://v-cdn.zjol.com.cn/276986.mp4",
            "https://v-cdn.zjol.com.cn/276987.mp4",
            "https://v-cdn.zjol.com.cn/276988.mp4",
            "https://v-cdn.zjol.com.cn/276989.mp4",
            "https://v-cdn.zjol.com.cn/276990.mp4",
            "https://v-cdn.zjol.com.cn/276991.mp4",
    };

    public static void init(){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)
                MApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
    }

    public static List<String> getList1(){
        List<String> list = getList();
        for (int i = 0; i < 20; i++){
            list.add("这是第 ： " + i);
        }
        return list;
    }

    public static List<ImgData> getList2(){
        List<ImgData> list = new ArrayList<>();
        list.add(new ImgData(imgs[0]));
        list.add(new ImgData(imgs[1]));
        return list;
    }

    public static List<ImgData> getList3(){
        List<ImgData> list = new ArrayList<>();
        int length = imgs.length;
        int imgWidth = (screenWidth- 4 * 10) / 2;
        for (int i = 0; i < 20; i++){
            list.add(new ImgData(imgs[i % length], imgWidth, imgWidth * params[i % length][1] / params[i % length][0], "标题内容---" + i));
        }
        return list;
    }


    public static List<Alphabet> getList6(){
        List<Alphabet> list = getList();
        for (char i = 'A'; i <= 'Z'; i++){
            int children = new Random().nextInt(5) + 1;
            for (int j = 0; j < children; j++){
                list.add(new Alphabet(j, String.valueOf(i), "这是" + i + "的第 - " + j + "个元素"));
            }
        }
        return list;
    }

    public static List<Video> getList10(){
        List<Video> list = getList();
        for (int i = 0; i < 10; i++){
            list.add(new Video(videos[i % videos.length], "这是标题 - " + i));
        }
        return list;
    }

    public static List<ImgData> getList11_1(){
        List<ImgData> list = getList();
        int length = imgs.length;
        for (int i = 0; i < 2; i++){
            list.add(new ImgData(imgs[i % length], 0, 0, "标题内容---" + i));
        }
        return list;
    }

    public static List<ImgData> getList11_2(){
        List<ImgData> list = getList();
        for (int i = 0; i < 2; i++){
            list.add(new ImgData(null, 0, 0, "标题内容---" + (2 + i)));
        }
        return list;
    }


    /**
     * 测量View
     * @param view
     * @param restWidth
     * @param restHeight
     */
    public void measure(@NonNull View view, int restWidth, int restHeight) {
        int widthMeasureMode = View.MeasureSpec.EXACTLY;
        int heightMeasureMode = View.MeasureSpec.EXACTLY;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(restWidth, widthMeasureMode),
                    View.MeasureSpec.makeMeasureSpec(restHeight, heightMeasureMode));
            return;
        }
        if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {//WRAP_CONTENT需要AT_MOST的测量模式
            widthMeasureMode = View.MeasureSpec.AT_MOST;
        }
        if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {//WRAP_CONTENT需要AT_MOST的测量模式
            heightMeasureMode = View.MeasureSpec.AT_MOST;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(restWidth, widthMeasureMode),
                View.MeasureSpec.makeMeasureSpec(restHeight, heightMeasureMode));
    }

    /**
     * 获得指定view的bitmap
     * 未使用缓存
     *
     * @param v
     * @return
     */
    @Deprecated//未复用缓存会导致bitmap的频繁创建与销毁，导致频繁gc,性能有所下降，推荐使用getBitmapWithCache
    @Nullable
    public Bitmap getBitmap(@NonNull View v) {
        int width = v.getWidth();
        int height = v.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    private static <T> List<T> getList(){
        return new ArrayList<>();
    }
}
