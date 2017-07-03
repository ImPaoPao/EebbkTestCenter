package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
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
//

    @Test
    public void compareTest() throws JSONException, IOException {
        //匹配度测试
        clearRunprocess();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPkg = BbkMiddleMarket.PACKAGE;
    }

    @Test
    public void launchBbkMiddleMarket() throws IOException, UiObjectNotFoundException, JSONException,
            InterruptedException {
//        Rect refreshPngRect = new Rect(0, 0, mDevice.getDisplayWidth(), mDevice.getDisplayHeight() - 80);
//        Rect loadPngRect = new Rect(0, mDevice.getDisplayHeight() - 70, mDevice.getDisplayWidth(), mDevice
// .getDisplayHeight());
        clickIconStartApp(null, "应用商店", BbkMiddleMarket.PACKAGE, "apk_button", 5000, "home_tab_radioGroup", null, 10);
    }

    //首页点击应用→应用详情加载完成
    @Test
    public void showBbkMAppDetails() throws FileNotFoundException, JSONException {
        mHelper.openBbkMiddleMarket();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME * 6);
        UiObject2 apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name"));
        apk.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "view_pager")), WAIT_TIME * 6);
        SystemClock.sleep(5000);
        apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "view_pager"));
        Rect refreshPngRect = apk.getVisibleBounds();
        //详情
        apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "view_pager_detail_tv"));
        Rect loadPngRect = apk.getVisibleBounds();

        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name")), WAIT_TIME);
            apk = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name"));
            startTestRecord();
            apk.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
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
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //个人中心未登录，点击头像→个人中心页面加载完成
    @Test
    public void showBbkMSelfInfo() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 user = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id"));
        user.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Personal.PACKAGE, "layout_userinfo")), WAIT_TIME * 2);
        user = mDevice.findObject(By.res(Personal.PACKAGE, "layout_userinfo"));
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = user.getVisibleBounds();
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id")), WAIT_TIME);
            user = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "user_icon_id"));
            startTestRecord();
            user.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击版权声明→页面加载完成
    @Test
    public void showBbkMCopyright() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id"));
        copyright.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "tv_copy_right_content")), WAIT_TIME);
        copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "tv_copy_right_content"));
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = copyright.getVisibleBounds();
        mDevice.pressBack();
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id")), WAIT_TIME);
            copyright = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "copyright_declare_item_id"));
            startTestRecord();
            copyright.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //下载10个应用，点击下载中心→下载列表加载完成
    @Test
    public void showBbkMDownloadList() throws FileNotFoundException, JSONException {
        openBbkMine();
        UiObject2 download = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id"));
        download.click();
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_name")), WAIT_TIME * 4);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() / 2);
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id")), WAIT_TIME);
            download = mDevice.findObject(By.res(BbkMiddleMarket.PACKAGE, "download_center_item_id"));
            startTestRecord();
            download.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
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
