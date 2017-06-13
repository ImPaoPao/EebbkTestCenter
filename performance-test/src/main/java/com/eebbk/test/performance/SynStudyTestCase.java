package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants;
import com.eebbk.test.common.PackageConstants.SynStudy;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class SynStudyTestCase extends PerforTestCase {
    @Override
    public void initSetup() throws UiObjectNotFoundException, IOException {
        mPkg = SynStudy.PACKAGE;
        initMiddleSetup();
    }

    // 学科同步
    @Test
    public void launchSynStudy() throws IOException, UiObjectNotFoundException, JSONException,
            InterruptedException {
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "syn_widget_new_math")), WAIT_TIME);
        UiObject2 icon = mDevice.findObject(By.res(SynStudy.PACKAGE, "syn_widget_new_math"));
        icon.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
        mDevice.waitForIdle();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight()/3);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "syn_widget_new_math")), WAIT_TIME);
            icon = mDevice.findObject(By.res(SynStudy.PACKAGE, "syn_widget_new_math"));
            startTestRecord();
            icon.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(),(i+1));
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
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


    //点击添加按钮→下载界面加载完成
    @Test
    public void addDiscipline() throws IOException, JSONException {
        openSynStudy();
        //com.eebbk.synstudy:id/book_searchbook 有书本的时候 刷新
        //com.eebbk.synstudy:id/book_add 添加
        //com.eebbk.synstudy:id/book_del 删除
        //书本列表 android.widget.ListView -> LinearLayout->View
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
        UiObject2 add = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_add"));
        add.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_list")), WAIT_TIME * 4);
        UiObject2 bookList = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_list"));
        Rect rt = bookList.getVisibleBounds();
        mDevice.waitForIdle();
        SystemClock.sleep(20000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), 80);
        //界面刷新出来
        Rect refreshPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        SystemClock.sleep(1000);
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
            add = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_add"));
            startTestRecord();
            add.click();
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

    //点击书本→书本目录加载完成
    @Test
    public void showDisciplineContent() throws FileNotFoundException, JSONException {
        openSynStudy();
    }

    //点击教辅目录→进入课本详情
    @Test
    public void showDisciplineDetails() {
    }

    //教材内容界面，点击右下角目录按钮→目录加载完成
    @Test
    public void showDisciplineContentDetails() {
    }

    //教材全解界面，点击右下角目录中教材内容全解→页面加载完成
    @Test
    public void showDisciplineContentOfTeaching() {
    }

    //教材全解界面，点击典型例题→页面加载完成
    @Test
    public void showDisciplineTypical() {
    }

    //书架界面10本书，点击刷新→刷新完成
    @Test
    public void refreshDiscipline() throws JSONException, FileNotFoundException {
        openSynStudy();
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_searchbook")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //中间刷新的那个黑色的带彩色点的小方块
        Rect loadPngRect = new Rect(source_png.getWidth() / 2 - 40, source_png.getHeight() / 2 - 40,
                source_png.getWidth() / 2 + 40, source_png.getHeight() / 2 + 40);
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_searchbook")), WAIT_TIME);
            UiObject2 refresh = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_searchbook"));
            startTestRecord();
            refresh.click();
            SystemClock.sleep(200);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            SystemClock.sleep(2000);
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    private void openSynStudy() {
        mDevice.wait(Until.hasObject(By.res(PackageConstants.SynStudy.PACKAGE, "syn_widget_new_chinese")), WAIT_TIME);
        UiObject2 synStudy = mDevice.findObject(By.res(PackageConstants.SynStudy.PACKAGE, "syn_widget_new_chinese"));
        synStudy.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.waitForIdle();
    }
}
