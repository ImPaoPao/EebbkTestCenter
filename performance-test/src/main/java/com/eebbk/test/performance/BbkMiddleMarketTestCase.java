package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants.BbkMiddleMarket;
import com.eebbk.test.common.PackageConstants.Personal;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class BbkMiddleMarketTestCase extends PerforTestCase {

    @Test
    public void compareTest() throws JSONException, FileNotFoundException {
        //匹配度测试

    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPkg = BbkMiddleMarket.PACKAGE;
    }

    @Test
    public void launchBbkMiddleMarket() throws IOException, UiObjectNotFoundException, JSONException,
            InterruptedException {
        Object icon = mHelper.openIcon(null, "应用商店", BbkMiddleMarket.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME * 4);
        mDevice.waitForIdle(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() - 80);
        Rect loadPngRect = new Rect(0, source_png.getHeight() - 70, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon(null, "应用商店", BbkMiddleMarket.PACKAGE);
            if (icon instanceof UiObject2) {
                startTestRecord();
                ((UiObject2) icon).click();
            } else {
                try {
                    startTestRecord();
                    ((UiObject) icon).click();
                } catch (UiObjectNotFoundException e) {
                    // Nothing to do
                }
            }
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            //mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME);
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressHome();
            if (mType == 1) {
                mDevice.pressHome();
            } else {
                clearRunprocess();
            }
            mDevice.waitForIdle();
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