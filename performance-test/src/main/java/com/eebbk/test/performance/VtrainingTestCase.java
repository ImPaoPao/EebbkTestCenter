package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants;
import com.eebbk.test.common.PackageConstants.Vtraining;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class VtrainingTestCase extends PerforTestCase {
    @Test
    public void launchVtraining() throws IOException, UiObjectNotFoundException, InterruptedException, JSONException {
        Object icon = mHelper.openIcon(null, "名师辅导班", Vtraining.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "my_plan_banner_scale_id")), WAIT_TIME * 6);
        SystemClock.sleep(10000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        UiObject2 view = mDevice.findObject(By.res(Vtraining.PACKAGE, "home_tab_view"));
        Rect loadPngRect = view.getVisibleBounds();
        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() - 70);
        //Rect loadPngRect = new Rect(0, source_png.getHeight() - 70, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon(null, "名师辅导班", Vtraining.PACKAGE);
            if (icon instanceof UiObject2) {
                startTestRecord();
                ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
            } else {
                try {
                    startTestRecord();
                    ((UiObject) icon).clickAndWaitForNewWindow();
                } catch (UiObjectNotFoundException e) {
                    // Nothing to do
                }
            }
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
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
            source_png=null;
        }
    }

    //点击首页更多精彩→课程列表页面，加载完成
    @Test
    public void showVtMoreList() throws IOException, JSONException {
        mHelper.openVtraining();
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_more_layout")), WAIT_TIME * 4);
        UiObject2 more = mDevice.findObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_more_layout"));
        more.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "homerecommended_more_relativelayout_id")), WAIT_TIME
                * 4);//等待列表中item加载出来。
        more = mDevice.findObject(By.res(Vtraining.PACKAGE, "homerecommended_more_title_bar_id"));
        Rect loadPngRect = more.getVisibleBounds();
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "homerecommended_more_recyclerview")), WAIT_TIME);
        more = mDevice.findObject(By.res(Vtraining.PACKAGE, "homerecommended_more_recyclerview"));
        Rect refreshPngRect = more.getVisibleBounds();
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openVtraining();
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_more_layout")), WAIT_TIME * 4);
            more = mDevice.findObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_more_layout"));
            startTestRecord();
            more.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击选课→科目→课程包封面，加载完成
    @Test
    public void showVtCourse() throws JSONException, IOException {
        openVt("选课");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "select_course_class_type_view")), WAIT_TIME * 4);
        UiObject2 course = mDevice.findObject(By.res(Vtraining.PACKAGE, "select_course_class_type_view"));
        course.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "PullToRefreshRecyclerView_recyclerview")), WAIT_TIME
                * 4);
        course = mDevice.findObject(By.res(Vtraining.PACKAGE, "course_package_title_bar_id"));
        Rect rt = course.getVisibleBounds();
        course = mDevice.findObject(By.res(Vtraining.PACKAGE, "PullToRefreshRecyclerView_recyclerview"));
        Rect rtd = course.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect refreshPngRect = new Rect(rtd.left, rtd.top, rtd.right, rtd.bottom);
        Rect loadPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openVt("选课");
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "select_course_class_type_view")), WAIT_TIME * 2);
            course = mDevice.findObject(By.res(Vtraining.PACKAGE, "select_course_class_type_view"));
            startTestRecord();
            course.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //选课界面，点击banner图名师在这里图片→名师页面加载完成
    @Test
    public void showVtCourseTeacher() throws IOException, JSONException {
        //select_course_teacher 选课 最上方的图 名师在这里->左上角。
        openVt("选课");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "select_course_teacher")), WAIT_TIME);
        UiObject2 course = mDevice.findObject(By.res(Vtraining.PACKAGE, "select_course_teacher"));
        Rect crt = course.getVisibleBounds();
        mHelper.longClick(crt.right / 10, crt.top + 25);//名师在这里
        //名师头像刷新出来
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "item_famousteacher_img_head_1")), WAIT_TIME * 4);
        //切换时  上方 选课变为名师 表示页面切换，上方的横向标题栏有变化 famous_teacher_indicator
        //course = mDevice.findObject(By.res(Vtraining.PACKAGE, "famous_teacher_view1"));
        course = mDevice.findObject(By.res(Vtraining.PACKAGE, "famous_teacher_indicator"));
        Rect loadPngRect = course.getVisibleBounds();
        //下方名师信息view
        course = mDevice.findObject(By.res(Vtraining.PACKAGE,
                "famous_teacher_studyphase_pulltoRefreshRecyclerView"));
        Rect refreshPngRect = course.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openVt("选课");
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "select_course_teacher")), WAIT_TIME * 2);
            startTestRecord();
            mDevice.click(crt.right / 10, crt.top + 25);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }


    //点击名师头像→名师详情加载完成 showVtTeacherInfo
    @Test
    public void showVtTeacherInfo() throws IOException, JSONException {
        //点击头像 显示名师信息 一页加载完全 不区分切换和刷新
        openVt("选课");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "select_course_teacher")), WAIT_TIME);
        UiObject2 course = mDevice.findObject(By.res(Vtraining.PACKAGE, "select_course_teacher"));
        Rect crt = course.getVisibleBounds();
        mHelper.longClick(crt.right / 10, crt.top + 25); //名师在这里
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "item_famousteacher_img_head_1")), WAIT_TIME * 6);
        course = mDevice.findObject(By.res(Vtraining.PACKAGE, "item_famousteacher_img_head_1"));
        course.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "teacher_area_id")), WAIT_TIME);
        course = mDevice.findObject(By.res(Vtraining.PACKAGE, "teacher_area_id"));
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(course.getVisibleBounds().left, course.getVisibleBounds().top, source_png.getWidth
                (), source_png.getHeight() / 2);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "item_famousteacher_img_head_1")), WAIT_TIME);
            course = mDevice.findObject(By.res(Vtraining.PACKAGE, "item_famousteacher_img_head_1"));
            startTestRecord();
            course.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //我的界面，点击排行榜→排行榜页面，加载完成
    @Test
    public void showVtRanking() throws JSONException, FileNotFoundException {
        openVt("我的");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_rank_layout")), WAIT_TIME * 6);
        UiObject2 rank = mDevice.findObject(By.res(Vtraining.PACKAGE, "mine_rank_layout"));
        rank.clickAndWait(Until.newWindow(), WAIT_TIME);
        //排行列表刷新出来
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "item_week_item_click_id")), WAIT_TIME * 6);
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "list")), WAIT_TIME);
        rank = mDevice.findObject(By.res(PackageConstants.Android.PACKAGE, "list"));
        Rect refreshPngRect = rank.getVisibleBounds();//list 包含了整个排行榜页面，不包含最上方的return bar
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "week_rank_video_study_id")), WAIT_TIME);
        rank = mDevice.findObject(By.res(Vtraining.PACKAGE, "week_rank_video_study_id"));//最上方的视频学习榜挑战习题榜
        Rect loadPngRect;
        if (rank != null) {
            loadPngRect = rank.getVisibleBounds();
        } else {
            loadPngRect = new Rect(0, 40, mDevice.getDisplayWidth(), 100);
        }

        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_rank_layout")), WAIT_TIME);
            rank = mDevice.findObject(By.res(Vtraining.PACKAGE, "mine_rank_layout"));
            startTestRecord();
            rank.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }


    //已加入5个课程，进入我的界面，点击已加入课程→列表内容加载完成
    @Test
    public void showVtJoinCourse() throws JSONException, FileNotFoundException {
        BySelector by = By.text("已加入课程");
        openVt("我的");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_setting_layout")), WAIT_TIME);
        UiObject2 join = mDevice.findObject(by);
        join.clickAndWait(Until.newWindow(), WAIT_TIME);
        if (mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "my_plan_no_plan_tip_id")), WAIT_TIME)) {
            join = mDevice.findObject(By.res(Vtraining.PACKAGE, "my_plan_no_plan_tip_id"));
        } else if (mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "title_bar_btn_edit")), WAIT_TIME * 4)) {
            join = mDevice.findObject(By.res(PackageConstants.Android.PACKAGE, "list"));
        }
        Rect refreshPngRect = join.getVisibleBounds();
        join = mDevice.findObject(By.res(Vtraining.PACKAGE, "title_bar_title_name"));
        Rect loadPngRect = join.getVisibleBounds();
        //Rect loadPngRect = new Rect(rt.left,rt.top,rt.right,rt.bottom/2);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_setting_layout")), WAIT_TIME);
            join = mDevice.findObject(by);
            startTestRecord();
            join.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }


    //已下载5个课程，进入我的界面，点击已下载课程→列表内容加载完成
    @Test
    public void showVtDownloadCourse() throws FileNotFoundException, JSONException {
        BySelector by = By.text("我的下载");
        openVt("我的");
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_setting_layout")), WAIT_TIME);
        UiObject2 download = mDevice.findObject(by);
        download.clickAndWait(Until.newWindow(), WAIT_TIME);
        if (mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "tips_no_download_layout")), WAIT_TIME)) {
            download = mDevice.findObject(By.res(Vtraining.PACKAGE, "tips_no_download_layout"));
        } else if (mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "title_bar_btn_edit")), WAIT_TIME * 2)) {
            download = mDevice.findObject(By.res(PackageConstants.Android.PACKAGE, "list"));
        }
        Rect refreshPngRect = download.getVisibleBounds();
        download = mDevice.findObject(By.res(Vtraining.PACKAGE, "title_bar_title_name"));
        Rect loadPngRect = download.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mine_setting_layout")), WAIT_TIME * 2);
            download = mDevice.findObject(by);
            startTestRecord();
            download.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击课程包封面→视频播放界面加载完成
    //@Test
//    public void showVtCourseVideo() {
//    }

    //点击首页视频缩略图→视频播放加载完成
    //@Test
    public void showVtVideo() throws FileNotFoundException, JSONException {
        mHelper.openVtraining();
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_item_image")), WAIT_TIME * 4);
        UiObject2 more = mDevice.findObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_item_image"));
        more.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "videoview")), WAIT_TIME * 6);
        SystemClock.sleep(10000);
        more = mDevice.findObject(By.res(Vtraining.PACKAGE, "tab_item_id"));
        Rect loadPngRect = more.getVisibleBounds();
        more = mDevice.findObject(By.res(Vtraining.PACKAGE, "videoview"));
        Rect refreshPngRect = more.getVisibleBounds();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        mDevice.pressBack();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_item_image")), WAIT_TIME * 4);
            more = mDevice.findObject(By.res(Vtraining.PACKAGE, "mainpage_introduce_item_image"));
            startTestRecord();
            more.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    private void openVt(String menu) {
        BySelector byMenu = By.text(menu);
        mHelper.openVtraining();
        mDevice.wait(Until.hasObject(byMenu), WAIT_TIME);
        UiObject2 click = mDevice.findObject(byMenu);
        if (click != null) {
            click.click();
            mDevice.waitForIdle();
        }
    }
}
