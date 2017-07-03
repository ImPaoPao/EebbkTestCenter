package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * 桌面挂件启动速度
 */

@RunWith(AndroidJUnit4.class)
public class PendantTestCase extends PerforTestCase {
    //Demo
    @Test
    public void launchSynChinese() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp("语文学习", "同步语文", PackageConstants.SynChinese.PACKAGE, "refresh", 2000, null, 10);
    }

    //
    /*
    1.泡泡少儿词汇
    2.剑桥少儿英语
    3.新概念英语

     */


}


