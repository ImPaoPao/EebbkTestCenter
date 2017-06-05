package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.BitmapHelper;
import com.eebbk.test.common.PackageConstants.BbkMiddleMarket;
import com.eebbk.test.common.PackageConstants.Personal;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class BbkMiddleMarketTestCase extends PerforTestCase {

    @Test
    public void compareTest() throws JSONException, FileNotFoundException {
        //匹配度测试
        startTestRecord();
        JSONObject obj = new JSONObject();
        FileInputStream fis1 = new FileInputStream("/sdcard/tt.png");
        FileInputStream fis2 = new FileInputStream("/sdcard/tt1.png");
//        Bitmap png1 = Bitmap.createBitmap(BitmapFactory.decodeStream(fis1),mDevice.getDisplayWidth()
//                / 2 - 30, mDevice.getDisplayHeight() / 2 - 30, 60, 60);
//        Bitmap png2 = Bitmap.createBitmap(BitmapFactory.decodeStream(fis2),mDevice.getDisplayWidth()
//                / 2 - 30, mDevice.getDisplayHeight() / 2 - 30, 60, 60);
//        obj.put("compare result", BitmapHelper.compare(Bitmap.createBitmap(png1, 0, 310, mDevice.getDisplayWidth(),
//                mDevice.getDisplayHeight() - 310), Bitmap.createBitmap(png2, 0, 310, mDevice.getDisplayWidth(),
//                mDevice.getDisplayHeight() - 310)));
        obj.put("compare result 2 ", BitmapHelper.compare(BitmapFactory.decodeStream
                (fis1), BitmapFactory.decodeStream(fis2)));
//        obj.put("compare result", BitmapHelper.compare(png1,png2));
//        mHelper.takeScreenshot(new Rect(0, mDevice.getDisplayHeight() / 4, mDevice.getDisplayWidth() * 2 / 5,
//                mDevice.getDisplayHeight() * 3 / 4), new File("/sdcard/tt1.png"), 1);
        instrumentationStatusOut(obj);
        stopTestRecord();
    }


    @Test
    public void launchBbkMiddleMarket() throws IOException, UiObjectNotFoundException, JSONException,
            InterruptedException {
        BySelector bySynBbkMarket = By.text("应用商店");
        Bitmap source_png = getHomeSourceScreen(bySynBbkMarket, BbkMiddleMarket.PACKAGE, "apk_button", 10000);
        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() - 80);
        Rect loadPngRect = new Rect(0, source_png.getHeight() - 70, source_png.getWidth(), source_png.getHeight());
        for (int i = 0; i < mCount; i++) {
            swipeCurrentLauncher();
            mDevice.wait(Until.hasObject(bySynBbkMarket), WAIT_TIME);
            UiObject2 bbkMarket = mDevice.findObject(bySynBbkMarket);
            startTestRecord();
            bbkMarket.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME);
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressHome();
            clearRunprocess();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //com.eebbk.bbkmiddlemarket:id/tab_category 分类
    //com.eebbk.bbkmiddlemarket:id/tab_home 首页
    //com.eebbk.bbkmiddlemarket:id/tab_special 专题
    //com.eebbk.bbkmiddlemarket:id/tab_mine 我
    //com.eebbk.bbkmiddlemarket:id/home_tab_radioGroup 下方的tab


    //首页点击应用→应用详情加载完成
    @Test
    public void showBbkMAppDetails() throws FileNotFoundException, JSONException {
        mHelper.openBbkMiddleMarket();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME*6);
        UiObject2 apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name"));
        apk.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "success_view")), WAIT_TIME * 4);
        apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "success_view"));
        Rect rt = apk.getVisibleBounds();
        //详情
        apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "view_pager_detail_tv"));
        Rect rtd = apk.getVisibleBounds();

        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(rtd.left, rtd.top, rtd.right, rtd.bottom);
        Rect refreshPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name")), WAIT_TIME);
            apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name"));
            startTestRecord();
            apk.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //分类页面，点击语文→内容加载完成
    @Test
    public void showBbkMClassCh() throws FileNotFoundException, JSONException {
        mHelper.openBbkMiddleMarket();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "tab_category")), WAIT_TIME * 4);
        UiObject2 category = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tab_category"));
        category.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "titleTv")), WAIT_TIME);
        category = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tagNameTv"));
        category.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "tabs")), WAIT_TIME);
        category = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tabs"));
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME * 4);
        Rect rt = category.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        Rect refreshPngRect = new Rect(0, source_png.getHeight() - rt.bottom, source_png.getWidth(), source_png
                .getHeight());
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "titleTv")), WAIT_TIME);
            category = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tagNameTv"));
            startTestRecord();
            category.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //进入专题，点击APP竞品推荐→界面加载完成
    @Test
    public void showBbkMRecomend() {

    }

    //个人中心未登录，点击头像→个人中心页面加载完成
    @Test
    public void showBbkMSelfInfo() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 user = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id"));
        user.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Personal.PACKAGE, "layout_userinfo")), WAIT_TIME * 2);
        user = mDevice.findObject(By.res(Personal.PACKAGE, "layout_userinfo"));
        Rect rt = user.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id")), WAIT_TIME);
            user = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id"));
            startTestRecord();
            user.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //com.eebbk.bbkmiddlemarket:id/download_center_item_id 下载中心
    //com.eebbk.bbkmiddlemarket:id/copyright_declare_item_id版权声明


    //点击版权声明→页面加载完成
    @Test
    public void showBbkMCopyright() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id"));
        copyright.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "tv_copy_right_content")), WAIT_TIME);
        copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tv_copy_right_content"));
        Rect rt = copyright.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id")), WAIT_TIME);
            copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id"));
            startTestRecord();
            copyright.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //下载10个应用，点击下载中心→下载列表加载完成
    @Test
    public void showBbkMDownloadList() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 download= mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id"));
        download.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name")), WAIT_TIME*4);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(0,0,source_png.getWidth(),source_png.getHeight()/2);
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id")), WAIT_TIME);
            download = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id"));
            startTestRecord();
            download.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    private void openBbkMine() {
        mHelper.openBbkMiddleMarket();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "tab_mine")), WAIT_TIME * 4);
        UiObject2 category = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tab_mine"));
        category.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id")), WAIT_TIME);
    }
}
