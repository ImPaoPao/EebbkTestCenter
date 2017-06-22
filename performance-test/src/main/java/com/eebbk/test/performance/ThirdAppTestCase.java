package com.eebbk.test.performance;

import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ThirdAppTestCase extends PerforTestCase{
    private String headline = "com.ss.android.article.news";///.activity.MainActivity
    private String meiYan = "com.meitu.meiyancamera";///com.meitu.myxj.home.activity.HomeActivity
    private String iQiYi = "com.qiyi.video";///org.qiyi.android.video.MainActivity
    private String taoBao = "com.taobao.taobao";///com.taobao.tao.homepage.MainActivity3
    private String qqMusic = "com.tencent.qqmusic";///.activity.AppStarterActivity
    private String king = "com.tencent.tmgp.sgame";///.SGameActivity
    //private Rect rt = new Rect(0,0,mDevice.getDisplayWidth(),mDevice.getDisplayHeight());
    @Test
    public void launchMeiYan() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "美颜相机", meiYan, "waitui", 20000);
    }


    //新闻资讯类：今日头条
    @Test
    public void launchHeadline() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "今日头条", headline, "waitui", 20000);
    }

    //视频类：爱奇艺
    @Test
    public void launchQiYi() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "爱奇艺", iQiYi, "waitui", 20000);
    }

    //音乐类：QQ音乐

    @Test
    public void launchQQMusic() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "QQ音乐", qqMusic, "waitui", 20000);
    }

    //电商购物：淘宝
    @Test
    public void launchTaoBao() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "手机淘宝", taoBao, "waitui", 20000);
    }


    //大型游戏：王者荣耀
    //@Test
    public void launchKing() throws IOException, JSONException {
        clickLauncherIconStartApp(null, "王者荣耀", king, "waitui", 25000);
    }

    //网页浏览
    /*
        http://www.eebbk.com
        http://www.qq.com
        http://www.baidu.com
        http://www.sina.com
        http://www.sohu.com
        http://www.taobao.com
        http://www.jd.com
     */


    //@Test
//    public void launchWebView() throws IOException, JSONException, UiObjectNotFoundException {
//        String pkg = "com.UCMobile";
//        Object icon = mHelper.openIcon(null, "UC浏览器", pkg);
//        if (icon instanceof UiObject2) {
//            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
//        } else {
//            try {
//                ((UiObject) icon).clickAndWaitForNewWindow();
//            } catch (UiObjectNotFoundException e) {
//                // Nothing to do
//            }
//        }
//        mDevice.waitForIdle();
//        mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 10);
//        mDevice.wait(Until.hasObject(By.res(pkg, "edittext")), WAIT_TIME);
//        UiObject2 edt = mDevice.findObject(By.res(pkg, "edittext"));
//        List<UiObject2> children = edt.getChildren();
//        UiObject2 child = children.get(0);
//        JSONObject obj = new JSONObject();
//        obj.put("=====", edt);
//        obj.put("child", child);
//        obj.put("child text ", child.getText());
//        obj.put("&&&&&&", "edt.getText()");
//        obj.put("))))))))", edt.getApplicationPackage());
//        instrumentationStatusOut(obj);
//        mDevice.pressBack();
//        SystemClock.sleep(1000);
//        child.clear();
//        child.setText("ffffffffff");
//
////        mDevice.wait(Until.hasObject(By.res(pkg, "cancel")), WAIT_TIME);
////        UiObject2 view2 = mDevice.findObject(By.res(pkg, "cancel"));
////        view2.clickAndWait(Until.newWindow(), WAIT_TIME);
////        com.UCMobile
////        content - desc homepage_search
////                [0, 270][1080, 419]搜索框坐标
//        /*
//        搜索或输入网址
//        com.UCMobile:id/cancel 取消/搜索
//         */
//    }
}