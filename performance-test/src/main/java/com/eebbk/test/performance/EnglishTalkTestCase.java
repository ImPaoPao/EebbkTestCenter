package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants.EnglishTalk;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class EnglishTalkTestCase extends PerforTestCase {

    @Test
    public void launchEnglishTalk() throws IOException, UiObjectNotFoundException, InterruptedException, JSONException {
        clickIconStartApp(null, "英语听说", EnglishTalk.PACKAGE, "main_player_controller", 5000,
                "main_player_controller", null, 1);
    }

    //选择教材添加后，点击教材目录→进入播放界面
    @Test
    public void showEtPlayUi() throws IOException, UiObjectNotFoundException, InterruptedException,
            JSONException {
        mHelper.openEnglishTalkContent();
        mDevice.waitForIdle();
        SystemClock.sleep(2000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "detail_book_top_layout_id")), WAIT_TIME);
        UiObject2 top = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "detail_book_top_layout_id"));
        Rect loadPngRect = top.getVisibleBounds();
        Rect refreshPngRect = new Rect(0, loadPngRect.bottom, source_png.getWidth(), source_png.getHeight());
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "database_list_listview_id")), WAIT_TIME);
            UiObject2 listBook = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "database_list_listview_id"));
            List<UiObject2> children = listBook.getChildren();
            UiObject2 addBook = children.get(0);
            startTestRecord();
            addBook.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
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

    //播放界面，点击右下角目录按钮→目录加载完成
    @Test
    public void showEtPlayAudioContent() throws IOException, UiObjectNotFoundException, InterruptedException,
            JSONException {
        mHelper.openEnglishTalkContent();
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id")), WAIT_TIME);
        UiObject2 bookChild = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id"));
        bookChild.clickAndWait(Until.newWindow(), WAIT_TIME);
        SystemClock.sleep(1000);
        //播放暂停
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 40);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "play_more")), WAIT_TIME);
        bookChild = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "play_more"));
        bookChild.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "player_function_view_layout_id")), WAIT_TIME);
        UiObject2 player_function_view_layout_id = mDevice.findObject(By.res(EnglishTalk.PACKAGE,
                "player_function_view_layout_id"));
        Rect rt = player_function_view_layout_id.getVisibleBounds();
//        SystemClock.sleep(2000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
//        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
//        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "play_more")), WAIT_TIME);
            bookChild = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "play_more"));
            startTestRecord();
            bookChild.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
    }

    //播放界面，点击目录按钮中任意目录→播放内容加载完成
    @Test
    public void showEtPlayAudio() throws IOException, UiObjectNotFoundException, InterruptedException,
            JSONException {
        mHelper.openEnglishTalkContent();
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id")), WAIT_TIME);
        UiObject2 bookChild = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id"));
        bookChild.clickAndWait(Until.newWindow(), WAIT_TIME);
        SystemClock.sleep(1000);
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 40);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "player_function_bottom_layout_id")), WAIT_TIME);
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
//        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id")), WAIT_TIME);
            bookChild = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "item_book_menu_child_root_id"));
            startTestRecord();
            bookChild.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            mDevice.pressBack();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击我的排行→排行榜加载完成
    @Test
    public void showEtRanking() throws IOException, UiObjectNotFoundException, InterruptedException, JSONException {
        mHelper.openEnglishTalk();
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "homepage_func_rank_id")), WAIT_TIME);
        UiObject2 ranking = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "homepage_func_rank_id"));
        ranking.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "list")), WAIT_TIME);
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
//        Rect loadPngRect = new Rect(0, source_png.getHeight() - mDevice.getDisplayHeight() * 80 / 1024, source_png
//                .getWidth(), source_png.getHeight());
//        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() - 100 * mDevice
//                .getDisplayHeight() / 1024);
//        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
//                loadPngRect.width(), loadPngRect.height());
//        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
//                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "homepage_func_rank_id")), WAIT_TIME);
            ranking = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "homepage_func_rank_id"));
            startTestRecord();
            ranking.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
//            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
//                    Date(), (i + 1), 1, 0);
            Map<String, String> compareResult = doCompare(null, null, source_png, null, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
            mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "list")), WAIT_TIME);
            mDevice.pressBack();
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
}
