package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants.Vision;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class VisionTestCase extends PerforTestCase {
    //视力保护:优化点，动态画面的获取，获取控件 ，计时。
    @Test
    public void launchVision() throws IOException, UiObjectNotFoundException, JSONException, InterruptedException {
        BySelector byVision = By.text("视力保护");
        Bitmap source_png = getHomeSourceScreen(byVision, Vision.PACKAGE, 2000);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        for (int i = 0; i < mCount; i++) {
            swipeCurrentLauncher();
            mDevice.wait(Until.hasObject(byVision), WAIT_TIME);
            UiObject2 vision = mDevice.findObject(byVision);
            startTestRecord();
            vision.clickAndWait(Until.newWindow(), WAIT_TIME);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressHome();
            clearRunprocess();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击眼保健操→显示眼保健操界面
    @Test
    public void showVisionAnimation() throws FileNotFoundException, JSONException, RemoteException {
        mHelper.openVision();
        SystemClock.sleep(1000);
        int rotation = mDevice.getDisplayRotation();
        SystemClock.sleep(3000);
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 40);
        if (mDevice.getDisplayRotation() != rotation) {
            Bitmap source_png = mHelper.takeScreenshot(mNumber);
            SystemClock.sleep(1000);
            Rect loadPngRect = new Rect(0, 0, source_png.getWidth() / 2, source_png.getHeight() / 2);
            Rect refreshPngRect = loadPngRect;
            mDevice.pressBack();
            SystemClock.sleep(1000);
            for (int i = 0; i < mCount; i++) {
                startTestRecord();
                mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 40);
                Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
                stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                        ("loadResult"), compareResult.get("refreshResult"));
                SystemClock.sleep(1000);
                mDevice.pressBack();
                SystemClock.sleep(1000);
            }
            if (!source_png.isRecycled()) {
                source_png.recycle();
            }
        }
    }

    //点击设置按钮→显示设置界面
    @Test
    public void showVisionSettings() throws JSONException, FileNotFoundException, InterruptedException {
        mHelper.openVision();
        SystemClock.sleep(1000);
        mDevice.click(mDevice.getDisplayWidth() -45, 65);
        mDevice.wait(Until.hasObject(By.text("设置")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(1000);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth() / 2, source_png.getHeight() / 2);
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            startTestRecord();
            mDevice.click(mDevice.getDisplayWidth() -45, 65);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressBack();
            SystemClock.sleep(2000);
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击护眼小知识→显示不严小知识界面
    @Test
    public void showVisionProtection() throws JSONException, FileNotFoundException {
        mHelper.openVision();
        mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() * 2 / 3, mDevice.getDisplayWidth() /
                2, mDevice
                .getDisplayHeight() / 3, 20);
        //滑动到最下面,可以点击护眼小知识菜单
        SystemClock.sleep(1000);
        mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 30);
        mDevice.wait(Until.hasObject(By.text("护眼小知识")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        SystemClock.sleep(2000);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        Rect refreshPngRect = loadPngRect;
        mDevice.pressBack();
        SystemClock.sleep(2000);
        for (int i = 0; i < mCount; i++) {
            startTestRecord();
            mDevice.click(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() - 30);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
            SystemClock.sleep(2000);
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }
}
