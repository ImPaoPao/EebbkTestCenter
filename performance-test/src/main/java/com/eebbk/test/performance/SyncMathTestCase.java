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
import android.widget.ListView;

import com.eebbk.test.common.PackageConstants.MathAnimation;
import com.eebbk.test.common.PackageConstants.SynMath;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class SyncMathTestCase extends PerforTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPkg = SynMath.PACKAGE;
    }

    @Test
    public void launchSynMath() throws IOException, UiObjectNotFoundException, JSONException, InterruptedException {
        Object icon = mHelper.openIcon("数学学习", "同步数学", SynMath.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "refreshBtnId")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect  = new Rect(0,0,source_png.getWidth(),source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon("数学学习", "同步数学", SynMath.PACKAGE);
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
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            //mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "refresh")), WAIT_TIME);
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

    //addMathBook 点击添加按钮→下载界面加载完成 OK
    @Test
    public void addMathBook() throws IOException, JSONException {
        //com.eebbk.synmath:id/net_data
        mHelper.openSynMath();
        UiObject2 add = mDevice.findObject(By.res(SynMath.PACKAGE, "net_data"));
        add.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "book_list")), WAIT_TIME * 4);
        UiObject2 bookList = mDevice.findObject(By.res(SynMath.PACKAGE, "book_list"));
        Rect rt = bookList.getVisibleBounds();
        mDevice.waitForIdle();
        SystemClock.sleep(10000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), 80);
        //界面刷新出来
        Rect refreshPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMath();
            startTestRecord();
            mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "net_data")), WAIT_TIME);
            add = mDevice.findObject(By.res(SynMath.PACKAGE, "net_data"));
            add.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击书本→书本目录加载完成
    @Test
    public void showSynMathBook() throws IOException, JSONException {
        mHelper.openSynMath();
        mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
        UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
        List<UiObject2> children = booklist.getChildren();
        UiObject2 child = children.get(children.size() / 2);
        UiObject2 clickBook = child.getChildren().get(1);
        clickBook.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menu_back_btn")), WAIT_TIME * 4);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        mDevice.pressHome();
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMath();
            mDevice.wait(Until.hasObject(By.clazz("android.widget.ListView")), WAIT_TIME);
            booklist = mDevice.findObject(By.clazz("android.widget.ListView"));
            children = booklist.getChildren();
            child = children.get(children.size() / 2);
            clickBook = child.getChildren().get(1);
            startTestRecord();
            clickBook.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            SystemClock.sleep(500);
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //changeSynMathBook 进入课本目录界面，点击左边换书按钮→书架界面显示完成  OK
    @Test
    public void changeSynMathBook() throws IOException, JSONException {
        mHelper.openSynMath();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMathBook();
            mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menu_back_btn")), WAIT_TIME);
            UiObject2 changBook = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_back_btn"));
            startTestRecord();
            changBook.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            SystemClock.sleep(1000);
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //进入课本目录界面，点击动画讲解→动画讲解界面加载完成
    @Test
    public void showExplanationSynMathContent() throws IOException, JSONException {
        //com.eebbk.synmath:id/gotoMathAnimationBtnId
        mHelper.openSynMathBook();
        UiObject2 gotoMathAnimationBtn = mDevice.findObject(By.res(SynMath.PACKAGE, "gotoMathAnimationBtnId"));
        gotoMathAnimationBtn.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(MathAnimation.PACKAGE, "barup")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMathBook();
            gotoMathAnimationBtn = mDevice.findObject(By.res(SynMath.PACKAGE, "gotoMathAnimationBtnId"));
            startTestRecord();
            gotoMathAnimationBtn.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, null, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //进入课本目录界面，点击动画讲解右边的下载按钮→下载页面加载完成
    @Test
    public void downloadExplanatinSynMathContent() throws IOException, JSONException {
        //com.eebbk.synmath:id/add_download_button
        mHelper.openSynMathBook();
        mDevice.wait(Until.hasObject(By.res(MathAnimation.PACKAGE, "add_download_button")), WAIT_TIME * 2);
        UiObject2 download = mDevice.findObject(By.res(SynMath.PACKAGE, "add_download_button"));
        download.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(MathAnimation.PACKAGE, "actionSlideExpandableListView1")), WAIT_TIME * 4);
        SystemClock.sleep(15000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), 80);
        //中间 "正在加载"刷新框
        Rect refreshPngRect = new Rect(0, 100, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMathBook();
            mDevice.wait(Until.hasObject(By.res(MathAnimation.PACKAGE, "add_download_button")), WAIT_TIME * 2);
            download = mDevice.findObject(By.res(SynMath.PACKAGE, "add_download_button"));
            startTestRecord();
            download.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击教辅目录→进入课本详情
    @Test
    public void showDetailsSynMathBook() throws IOException, JSONException {
        mHelper.openSynMathBook();
        UiObject2 book = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_book_btn"));
        book.clickAndWait(Until.newWindow(), WAIT_TIME);
        UiObject2 list = mDevice.findObject(By.res(SynMath.PACKAGE, "treelist"));
        List<UiObject2> children = list.getChildren();
        UiObject2 detailBook = children.get(1);//第一课时
        detailBook.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menuBtnId")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
        //中间 "正在加载"刷新框
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMathBook();
            book = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_book_btn"));
            book.clickAndWait(Until.newWindow(), WAIT_TIME);
            list = mDevice.findObject(By.res(SynMath.PACKAGE, "treelist"));
            children = list.getChildren();
            detailBook = children.get(1);//第一课时
            startTestRecord();
            detailBook.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //书架界面10本书，点击刷新→刷新完成
    @Test
    public void refreshSynMath() throws IOException, JSONException {
        //刷新 com.eebbk.synmath:id/refreshBtnId
        mHelper.openSynMath();
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "refreshBtnId")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //中间刷新的那个黑色的带彩色点的小方块
        Rect loadPngRect = new Rect(source_png.getWidth() / 2 - 40, source_png.getHeight() / 2 - 40,
                source_png.getWidth() / 2 + 40, source_png.getHeight() / 2 + 40);
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMath();
            mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "refreshBtnId")), WAIT_TIME);
            UiObject2 refresh = mDevice.findObject(By.res(SynMath.PACKAGE, "refreshBtnId"));
            startTestRecord();
            refresh.clickAndWait(Until.newWindow(), WAIT_TIME);
            SystemClock.sleep(200);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //书本内容界面，点击左上角目录按钮，点击知识讲解→知识讲解内容加载完成
    @Test
    public void showExplanationSynMathBook() throws IOException, JSONException {
        //目录 com.eebbk.synmath:id/menuBtnId
        //com.eebbk.synmath:id/TextViewId  text知识讲解
        mHelper.openSynMathBook();
        UiObject2 book = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_book_btn"));
        book.clickAndWait(Until.newWindow(), WAIT_TIME);
        UiObject2 list = mDevice.findObject(By.res(SynMath.PACKAGE, "treelist"));
        List<UiObject2> children = list.getChildren();
        UiObject2 detailBook = children.get(1);//第一课时
        detailBook.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menuBtnId")), WAIT_TIME);
        book = mDevice.findObject(By.res(SynMath.PACKAGE, "menuBtnId"));
        book.clickAndWait(Until.newWindow(), WAIT_TIME);
        UiObject2 explanation = mDevice.findObject(By.text("知识讲解"));
        explanation.clickAndWait(Until.newWindow(), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 150, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynMathBook();
            UiObject2 book2 = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_book_btn"));
            book2.clickAndWait(Until.newWindow(), WAIT_TIME);
            UiObject2 list2 = mDevice.findObject(By.res(SynMath.PACKAGE, "treelist"));
            children = list2.getChildren();
            detailBook = children.get(1);//第一课时
            detailBook.clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menuBtnId")), WAIT_TIME);
            detailBook = mDevice.findObject(By.res(SynMath.PACKAGE, "menuBtnId"));
            detailBook.clickAndWait(Until.newWindow(), WAIT_TIME);
            UiObject2 explanation2 = mDevice.findObject(By.text("知识讲解"));
            startTestRecord();
            mHelper.longClick(explanation2);
            SystemClock.sleep(200);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
        }
    }
}
