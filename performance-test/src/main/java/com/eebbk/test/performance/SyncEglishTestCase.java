package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;
import android.widget.ListView;

import com.eebbk.test.common.PackageConstants.BbkMiddleMarket;
import com.eebbk.test.common.PackageConstants.SyncEnglish;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(AndroidJUnit4.class)
public class SyncEglishTestCase extends PerforTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPkg = SyncEnglish.PACKAGE;
    }


    @Test
    public void launchSyncEnglish() throws IOException, UiObjectNotFoundException, InterruptedException, JSONException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        Rect loadPngRect = new Rect(0, 0, mDevice.getDisplayWidth(), mDevice.getDisplayHeight()*3/20);
        Rect refreshPngRect = new Rect(0, loadPngRect.bottom, mDevice.getDisplayWidth(), mDevice.getDisplayHeight());
        clickIconStartApp("英语学习", "同步英语", SyncEnglish.PACKAGE, "imageview_mainbookshelf_blackboard", 3000,
                loadPngRect, refreshPngRect,1);
    }

    //前置条件：首页下载好十本书
    //书架界面10本书，点击刷新→刷新完成
    @Test
    public void synEnglishRefresh() throws IOException, JSONException, InterruptedException {
        mHelper.openSyncEnglishMain();
        UiObject2 refresh = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "refresh"));
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //中间刷新的那个黑色的带彩色点的小方块
        Rect loadPngRect = new Rect(source_png.getWidth() / 2 - 30, source_png.getHeight() / 2 - 30,
                source_png.getWidth() / 2 + 30, source_png.getHeight() / 2 + 30);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            startTestRecord();
            refresh.clickAndWait(Until.newWindow(), WAIT_TIME);
            SystemClock.sleep(200);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(loadPngRect, null, loadSource, null, new Date(), (i + 1),
                    1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
            loadSource = null;
        }
    }

    //点击添加按钮→下载界面加载完成
    @Test
    public void addSyncEnglishBook() throws IOException, JSONException {
        //获取屏幕截图
        mHelper.openSyncEnglishMain();
        mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "add_id")), WAIT_TIME);
        UiObject2 add = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "add_id"));
        add.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "iv_cover")), WAIT_TIME * 4);
        SystemClock.sleep(10000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
//        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), 80);
//        add = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "search"));
//        Rect loadPngRect = add.getVisibleBounds();
//        add = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "nd_gridview"));
//        Rect refreshPngRect = add.getVisibleBounds(); //new Rect(0, 100, source_png.getWidth(), source_png.getHeight());
//        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
//                loadPngRect.width(), loadPngRect.height());
//        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
//                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        mDevice.pressBack();
        mDevice.waitForIdle();
        mDevice.pressHome();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSyncEnglishMain();
            mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "add_id")), WAIT_TIME * 2);
            UiObject2 add2 = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "add_id"));
            startTestRecord();
            add2.clickAndWait(Until.newWindow(), WAIT_TIME);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new Date(), (i + 1),
                    1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
//        if (loadSource != null && !loadSource.isRecycled()) {
//            loadSource.recycle();
//            loadSource = null;
//        }
//        if (refreshSource != null && !refreshSource.isRecycled()) {
//            refreshSource.recycle();
//            refreshSource = null;
//        }
    }

    //点击书本→书本内容界面显示完成
    @Test
    public void showSyncEnglishBook() throws JSONException, IOException {
        //获取屏幕截图
        mHelper.openSyncEnglishMain();
        mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
        UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
        List<UiObject2> children = booklist.getChildren();
        UiObject2 child = children.get(children.size() / 2);
        child.getChildren().get(1).clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.waitForIdle();
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, source_png.getHeight()*2/3,source_png.getWidth(), source_png.getHeight());
        Rect refreshPngRect = new Rect(loadPngRect.left, 0,loadPngRect.width(),loadPngRect.top);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSyncEnglishMain();
            mDevice.wait(Until.hasObject(By.clazz("android.widget.ListView")), WAIT_TIME);
            booklist = mDevice.findObject(By.clazz("android.widget.ListView"));
            children = booklist.getChildren();
            child = children.get(children.size() / 2);
            UiObject2 click = child.getChildren().get(1);
            startTestRecord();
            click.clickAndWait(Until.newWindow(), WAIT_TIME);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new Date(), (i
                            + 1),
                    1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
            loadSource = null;
        }
        if (refreshSource != null && !refreshSource.isRecycled()) {
            refreshSource.recycle();
            refreshSource = null;
        }
    }

    //书本内容界面点击头像→个人信息页面加载完成
    @Test
    public void syncEnglishSelfInfo() throws FileNotFoundException, JSONException {
        mHelper.openSyncEnglishBook();
        mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id")), WAIT_TIME * 1);
        UiObject2 dropDown = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id"));
        Rect rt = dropDown.getVisibleBounds();
        //点击下拉环
        mHelper.longClick(rt.right - mDevice.getDisplayWidth()*35/768, rt.height() / 2);
        SystemClock.sleep(2000);
        //点击头像
        mHelper.longClick(mDevice.getDisplayWidth()*60/768, rt.height() / 2);
        SystemClock.sleep(2000);
        //截图保存
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //Rect loadPngRect = new Rect(0, source_png.getHeight() - 400, source_png.getWidth(), source_png.getHeight());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            //点击下拉环
            mHelper.longClick(rt.right - mDevice.getDisplayWidth()*35/768, rt.height() / 2);
            SystemClock.sleep(2000);
            startTestRecord();
            //点击头像
            //mHelper.longClick(60, rt.height() / 2);
            mDevice.click(mDevice.getDisplayWidth()*60/768, rt.height() / 2);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
    }

    //书本内容界面点击趣味测验→测验页面内容加载完成
    @Test
    public void syncEnglishFunTest() throws IOException, JSONException {
        mHelper.openSyncEnglishBook();
        UiObject2 dropDown = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id"));
        Rect rt = dropDown.getVisibleBounds();
        //点击下拉环
        mHelper.longClick(rt.right - mDevice.getDisplayWidth()*35/768, rt.height() / 2);
        SystemClock.sleep(2000);
        //点击趣味测试
        mHelper.longClick(rt.right - mDevice.getDisplayWidth()*45/768, rt.height() / 2);
        mDevice.waitForIdle();
        SystemClock.sleep(5000);
        //截图保存
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
//        SystemClock.sleep(1000);
//        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            //点击下拉环
            mHelper.longClick(rt.right - mDevice.getDisplayWidth()*35/768, rt.height() / 2);
            SystemClock.sleep(2000);
            startTestRecord();
            //点击趣味测试
            mDevice.click(rt.right - mDevice.getDisplayWidth()*45/768, rt.height() / 2);
//            mHelper.longClick(rt.right - mDevice.getDisplayWidth()*45/768, rt.height() / 2);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //趣味测验点击欧拉学英语→跳转到商店页面加载完成
    @Test
    public void syncEnglishOlaAccessBbkMarket() throws JSONException, IOException {
        mHelper.openSyncEnglishBook();
        UiObject2 dropDown = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id"));
        Rect rt = dropDown.getVisibleBounds();
        //点击下拉环
        mHelper.longClick(rt.right - mDevice.getDisplayWidth()*35/768, rt.height() / 2);
        SystemClock.sleep(2000);
        //点击趣味测试
        mHelper.longClick(rt.right - mDevice.getDisplayWidth()*45/768, rt.height() / 2);
        mDevice.waitForIdle();
        SystemClock.sleep(2000);
        //点击欧拉英语
        mHelper.longClick(mDevice.getDisplayWidth() / 4, mDevice.getDisplayHeight() / 2);
        mDevice.wait(Until.hasObject(By.res(BbkMiddleMarket.PACKAGE, "apk_button")), WAIT_TIME);
        SystemClock.sleep(10000);
        //截图保存
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);

        Rect loadPngRect = new Rect(mDevice.getDisplayWidth()*284/1536, mDevice.getDisplayHeight() - mDevice.getDisplayHeight() / 20,
                mDevice.getDisplayWidth()*436/1536,
                mDevice.getDisplayHeight());
        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), loadPngRect.top);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
//            //点击下拉环
//            mHelper.longClick(rt.right - 35, rt.height() / 2);
//            SystemClock.sleep(2000);
            startTestRecord();
            //点击欧拉英语
            mDevice.click(mDevice.getDisplayWidth() / 4, mDevice.getDisplayHeight() / 2);
            //mHelper.longClick(mDevice.getDisplayWidth() / 4, mDevice.getDisplayHeight() / 2);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource,refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
            loadSource = null;
        }
        if (refreshSource != null && !refreshSource.isRecycled()) {
            refreshSource.recycle();
            refreshSource = null;
        }
    }
}
