package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants;
import com.eebbk.test.common.PackageConstants.QuestionDatabase;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class QuestionDatabaseTestCase extends PerforTestCase {
    @Test
    public void launchQuestionDatabase() throws IOException, UiObjectNotFoundException, InterruptedException,
            JSONException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp(null, "好题精练", QuestionDatabase.PACKAGE, "exercise_view_pager", 5000,
                "exercise_main_infos_layout", "e_list_chpaters", 1);
    }

    //点击智能练习目录→题目加载完成
    @Test
    public void showQdExample() throws IOException, JSONException {
        openQd();
        UiObject2 exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "exercise_text_title"));
        exam.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.gone(By.res(QuestionDatabase.PACKAGE, "exercise_text_title")), WAIT_TIME * 4);
        mDevice.wait(Until.gone(By.res(QuestionDatabase.PACKAGE, "exercise_webview")), WAIT_TIME * 2);
        SystemClock.sleep(10000);
        Rect loadPngRect = new Rect(80, 20, 160, 60);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
//        SystemClock.sleep(2000);
        Rect refreshPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight() / 6);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openQd();
            mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "exercise_text_title")), WAIT_TIME * 2);
            exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "exercise_text_title"));
            startTestRecord();
            exam.clickAndWait(Until.newWindow(), WAIT_TIME);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
//            SystemClock.sleep(1000);
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

    //点击例题讲解目录→题目加载完成
    @Test
    public void showQdExamExplanation() throws IOException, JSONException {
        openQd("home_img_tab_select_exercises");
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "select_subject_current_chapter_layout")),
                WAIT_TIME * 4);
        SystemClock.sleep(5000);
        UiObject2 exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "select_exercise_view_pager"));
        Rect refreshPngRect = exam.getVisibleBounds();
        exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "select_exercise_filter_view"));
        Rect loadPngRect = exam.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
//        SystemClock.sleep(2000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openQd();
            mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "home_img_tab_select_exercises")),
                    WAIT_TIME * 2);
            exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "home_img_tab_select_exercises"));
            startTestRecord();
            exam.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
//            SystemClock.sleep(1000);
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
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

    //点击真题密卷科目→真题目录界面加载完成
    @Test
    public void showQdRealExamList() throws IOException, JSONException {
        openQd("home_img_tab_paper");
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "paper_main_item_grid_subject")),
                WAIT_TIME * 4);
        SystemClock.sleep(5000);
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "paper_main_search_layout")), WAIT_TIME);
        UiObject2 exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "paper_main_search_layout"));
        Rect rte = exam.getVisibleBounds();
        Rect loadPngRect = new Rect(rte.width() / 4, rte.top, rte.width() * 3 / 4, rte.bottom);
        exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "home_viewpager_content"));
        Rect rt = exam.getVisibleBounds();
        exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "home_linear_tab_container"));//下方的菜单条
        Rect refreshPngRect = new Rect(rt.left, rte.bottom, rt.right, rt.bottom - exam.getVisibleBounds().height());
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //        SystemClock.sleep(2000);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openQd();
            mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "home_img_tab_paper")), WAIT_TIME * 2);
            exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "home_img_tab_paper"));
            startTestRecord();
            exam.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
//            SystemClock.sleep(1000);
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
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

    //点击真题目录界面目录→题目加载完成
    @Test
    public void showQdRealExamContent() throws IOException, JSONException {
//        openQd("home_img_tab_paper");
//        openQd("home_img_tab_norisuke");

        mHelper.openQuestionDatabse();
        mDevice.waitForIdle();
        BySelector byMenu = By.res(QuestionDatabase.PACKAGE, "home_img_tab_paper");
        UiObject2 click = mDevice.findObject(byMenu);
        if (click != null) {
            click.clickAndWait(Until.newWindow(),WAIT_TIME);
            mDevice.waitForIdle();
        }else{
            click = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "home_img_tab_norisuke"));
            click.clickAndWait(Until.newWindow(),WAIT_TIME);
        }

        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "paper_main_item_grid_subject")),
                WAIT_TIME * 4);
        UiObject2 exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "paper_main_item_grid_subject"));
        List<UiObject2> children = exam.getChildren();
        exam = children.get(0);
        exam.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "paper_normal_item_text_paper_title")),
                WAIT_TIME * 4);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
//        SystemClock.sleep(2000);
        Rect refreshPngRect = new Rect(0, 100, source_png.getWidth(), source_png.getHeight() / 2);
        Rect loadPngRect = new Rect(0, 24, mDevice.getDisplayWidth(), 90);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openQd("home_img_tab_paper");
            mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "paper_main_item_grid_subject")),
                    WAIT_TIME * 4);
            exam = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "paper_main_item_grid_subject"));
            children = exam.getChildren();
            exam = children.get(0);
            startTestRecord();
            exam.click();
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
//            SystemClock.sleep(1000);
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
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

    //点击排行榜→排行榜页面加载完成
    @Test
    public void showQdRanking() throws IOException, JSONException {
        openQd();
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "exercise_main_rank")), WAIT_TIME * 4);
        UiObject2 rank = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "exercise_main_rank"));
        rank.clickAndWait(Until.newWindow(), WAIT_TIME);
        //等待排行榜中每一条排行信息
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "rank_all_info_layout")), WAIT_TIME * 6);
        rank = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "rank_img_back"));//返回的箭头
        Rect loadPngRect = rank.getVisibleBounds();
        rank = mDevice.findObject(By.res(PackageConstants.Android.PACKAGE, "list"));
        Rect refreshPngRect = rank.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
//        SystemClock.sleep(2000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openQd();
            mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "exercise_main_rank")), WAIT_TIME * 2);
            rank = mDevice.findObject(By.res(QuestionDatabase.PACKAGE, "exercise_main_rank"));
            startTestRecord();
            rank.clickAndWait(Until.newWindow(), WAIT_TIME);
//            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i +
// 1));
//            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
//                    ("loadResult"), compareResult.get("refreshResult"));
//            SystemClock.sleep(1000);
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), 1, 0);
            stopTestRecord(compareResult.get("lastTime"), compareResult.get("loadTime"), compareResult.get
                    ("refreshTime"), compareResult.get("loadResult"), compareResult.get("refreshResult"));
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

    private void openQd() {
        mHelper.openQuestionDatabse();
        mDevice.wait(Until.hasObject(By.res(QuestionDatabase.PACKAGE, "exercise_text_title")), WAIT_TIME * 4);//等待页面加载出来
        mDevice.waitForIdle();
    }

    private void openQd(String menuId) {
        mHelper.openQuestionDatabse();
        mDevice.waitForIdle();
        BySelector byMenu = By.res(QuestionDatabase.PACKAGE, menuId);
        UiObject2 click = mDevice.findObject(byMenu);
        if (click != null) {
            click.click();
            mDevice.waitForIdle();
        }
    }
    //有做题记录，点击做题概况→做题概况页面加载完成
    //有错题记录，点击错题本→错题界面加载完成
}