package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class LauncherTestCase extends PerforTestCase {
    /*
    *查看最近内存信息：
     */
    @Test
    public void launchRecentMemory() throws RemoteException, IOException, JSONException {
        for (int i = 0; i < mCount; i++) {
            if ((mSys & mApp) > 0) {
                doStartActivity(mSys, mApp);
            } else {
                doStartActivity(mSys > 0 ? mSys : mApp, mSys > 0 ? "1" : "0");
            }
            startTestRecord();
            mDevice.pressRecentApps();
            mDevice.wait(Until.hasObject(By.res(PackageConstants.AndroidLauncher.PACKAGE, "memory_info")), WAIT_TIME);
            UiObject2 recent = mDevice.findObject(By.res(PackageConstants.AndroidLauncher.PACKAGE, "memory_info"));
            String value = recent.getText();
            stopTestRecord(value);
            clearRunprocess();
        }
    }
}
