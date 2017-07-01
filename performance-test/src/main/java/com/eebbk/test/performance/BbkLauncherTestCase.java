package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants.Android;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class BbkLauncherTestCase extends PerforTestCase {
    /*
    *桌面加载速度
     */
    @Test
    public void loadBbkLauncher() throws RemoteException, IOException, JSONException, UiObjectNotFoundException {
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            clearLauncherCatch();
            mDevice.waitForIdle();
            startTestRecord();
            mDevice.pressHome();
            Map<String, String> compareResult = doCompare(loadPngRect,loadSource,new Date(),(i+1));
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(3000);
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
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
        }
    }

    private void clearLauncherCatch() throws IOException, UiObjectNotFoundException {
        mDevice.executeShellCommand("am start -W com.android.settings/.Settings");
        mDevice.wait(Until.hasObject(By.textContains("应用程序")), WAIT_TIME);
        UiObject2 app = mDevice.findObject(By.textContains("应用程序"));
        app.clickAndWait(Until.newWindow(), WAIT_TIME);
        UiScrollable noteList = new UiScrollable(new UiSelector().className("android.widget.ListView"));
        UiObject note = null;
        note = noteList.getChildByText(new UiSelector().className("android.widget.TextView"), "桌面", true);
        assertThat(note, notNullValue());
        note.clickAndWaitForNewWindow();
        mDevice.wait(Until.hasObject(By.text("存储")), WAIT_TIME);
        app = mDevice.findObject(By.textContains("存储"));
        app.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.textContains("清除数据")), WAIT_TIME);
        app = mDevice.findObject(By.textContains("清除数据"));
        app.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(Android.PACKAGE, BUTTON1)), WAIT_TIME);
        app = mDevice.findObject(By.res(Android.PACKAGE, BUTTON1));
        app.click();
        SystemClock.sleep(1000);
    }
}