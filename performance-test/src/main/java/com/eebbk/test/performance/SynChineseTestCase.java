package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;
import android.widget.ListView;

import com.eebbk.test.common.PackageConstants.EebbkDict;
import com.eebbk.test.common.PackageConstants.HanziLearning;
import com.eebbk.test.common.PackageConstants.SynChinese;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class SynChineseTestCase extends PerforTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        mPkg = SynChinese.PACKAGE;
    }

    @Test
    public void launchSynChinese() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp("语文学习", "同步语文", SynChinese.PACKAGE, "refresh",2000, null, 10);
    }

    private void openOneChineseBook() {
        mHelper.openSynChinese();
        if (mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "refresh")), WAIT_TIME)) {
            mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
            UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
            List<UiObject2> children = booklist.getChildren();
            UiObject2 child = children.get(children.size() / 2); //中间排书
            UiObject2 add = child.getChildren().get(1);//中间本书
            add.clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.waitForIdle();
        }
    }

    //添加按钮 界面加载完成
    @Test
    public void addChineseBook() throws IOException, JSONException {
        mHelper.openSynChinese();
        mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
        UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
        List<UiObject2> children = booklist.getChildren();
        UiObject2 child = children.get(0); //第一排书
        UiObject2 add = child.getChildren().get(0);//"添加"图片
        add.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "book_list")), WAIT_TIME * 4);
        UiObject2 bookList = mDevice.findObject(By.res(SynChinese.PACKAGE, "book_list"));
        Rect refreshPngRect = bookList.getVisibleBounds();
        bookList = mDevice.findObject(By.res(SynChinese.PACKAGE, "search"));
        Rect loadPngRect = bookList.getVisibleBounds();
        mDevice.waitForIdle();
        SystemClock.sleep(10000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            SystemClock.sleep(3000);
            mHelper.openSynChinese();
            mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
            booklist = mDevice.findObject(By.clazz(ListView.class));
            children = booklist.getChildren();
            child = children.get(0);
            add = child.getChildren().get(0);
            startTestRecord();
            add.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击书本→书本内容界面显示完成
    @Test
    public void showSynChineseBook() throws IOException, JSONException {
        openOneChineseBook();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 20, source_png.getWidth(), source_png.getHeight());
//        UiObject2 view = mDevice.findObject(By.text("课文"));
//        Rect loadPngRect =view.getVisibleBounds();
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynChinese();
            mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
            UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
            List<UiObject2> children = booklist.getChildren();
            UiObject2 child = children.get(children.size() / 2); //中间排书
            UiObject2 add = child.getChildren().get(1);//中间本书
            startTestRecord();
            add.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            mDevice.waitForIdle();
            mDevice.pressHome();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }

    }

    //点击教辅目录→进入课本详情
    @Test
    public void showDetailsSynChineseBook() throws IOException, JSONException {
        openOneChineseBook();
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 3);
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        UiObject2 view = mDevice.findObject(By.text("课文"));
        Rect loadPngRect = view.getVisibleBounds();
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openOneChineseBook();
            startTestRecord();
            mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 3);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            SystemClock.sleep(2000);
            mDevice.pressHome();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击查字词→调转到词典界面
    @Test
    public void syncChineseAccessDict() throws IOException, JSONException {
        openOneChineseBook();
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2);
        SystemClock.sleep(5000);//跳转到查字词 界面
        mHelper.longClick(640, 65);//点击查字典坐标
        mDevice.wait(Until.hasObject(By.res(EebbkDict.PACKAGE, "miaohong_dictedit")), WAIT_TIME);//描红词典界面
        SystemClock.sleep(5000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openOneChineseBook();
            mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2);
            SystemClock.sleep(5000);//跳转到有查字词的界面
            startTestRecord();
            mHelper.longClick(640, 65);//点击查字词坐标
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.wait(Until.hasObject(By.res(EebbkDict.PACKAGE, "miaohong_dictedit")), WAIT_TIME);//描红词典界面
            clearRunprocess();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //生字页面，点击一个生字，点击写一写→进入写一写界面
    @Test
    public void synChineseNewWord() throws IOException, JSONException {
        //生字 写一写 com.eebbk.synchinese:id/new_word_goWriteBtnId
        openOneChineseBook();
        mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 3);
        SystemClock.sleep(5000);
        mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 30);//点击字词
        SystemClock.sleep(2000);
        mHelper.longClick(100, 340);//第一个字
        mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "new_word_goWriteBtnId")), WAIT_TIME);//写一写
        UiObject2 newWord = mDevice.findObject(By.res(SynChinese.PACKAGE, "new_word_goWriteBtnId"));
        newWord.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(HanziLearning.PACKAGE, "hanziZoomWriteView")), WAIT_TIME);//汉子学习
        SystemClock.sleep(2000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(0, 0, 400, 400);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            openOneChineseBook();
            mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 3);
            SystemClock.sleep(5000);
            mHelper.longClick(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 30);//点击字词
            SystemClock.sleep(2000);
            mHelper.longClick(100, 340);//第一个字
            mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "new_word_goWriteBtnId")), WAIT_TIME);//写一写
            newWord = mDevice.findObject(By.res(SynChinese.PACKAGE, "new_word_goWriteBtnId"));
            startTestRecord();
            newWord.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.wait(Until.hasObject(By.res(HanziLearning.PACKAGE, "hanziZoomWriteView")), WAIT_TIME);//汉子学习
            clearRunprocess();
            SystemClock.sleep(1000);
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //书架界面10本书，点击刷新→刷新完成
    @Test
    public void synChineseRefresh() throws IOException, JSONException {
        mHelper.openSynChinese();
        mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "refresh")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //中间刷新的那个黑色的带彩色点的小方块
        Rect loadPngRect = new Rect(source_png.getWidth() / 2 - 40, source_png.getHeight() / 2 - 40,
                source_png.getWidth() / 2 + 40, source_png.getHeight() / 2 + 40);
        SystemClock.sleep(1000);
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            mHelper.openSynChinese();
            mDevice.wait(Until.hasObject(By.res(SynChinese.PACKAGE, "refresh")), WAIT_TIME);
            UiObject2 refresh = mDevice.findObject(By.res(SynChinese.PACKAGE, "refresh"));
            startTestRecord();
            refresh.clickAndWait(Until.newWindow(), WAIT_TIME);
            SystemClock.sleep(200);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date(), (i + 1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            SystemClock.sleep(2000);
            mDevice.pressHome();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }
}