package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

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
        mDevice.findObject(new UiSelector().descriptionContains("英语听说"));
    }

    //小学

    /**
     * 英语挂件
     * 1.泡泡少儿词汇  com.eebbk.vtraining:id/widget_paopao_goto
     * 2.剑桥少儿英语 com.eebbk.vtraining:id/widget_jianqiao_goto
     *3.新概念英语 com.eebbk.vtraining:id/widget_new_goto
     *4.同步英语 com.eebbk.syncenglish:id/widgetListenBtn
     *5.英语听说 android.appwidget.AppWidgetHostView content-desc mDevice.findObject(new UiSelector().descriptionContains
     *("英语听说"));
     *6.查单词 com.eebbk.dict:id/widget_enterBtn
     */
    @Test
    public void launchBubleKid() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp("语文学习", "同步语文", PackageConstants.SynChinese.PACKAGE, "refresh", 2000, null, 10);
        Object icon = mHelper.openPendant("widget_paopao_goto","com.eebbk.vtraining",true);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
//        UiObject obj = mDevice.findObject(new UiSelector().descriptionContains("英语听说"));
//        obj.clickAndWaitForNewWindow();
    }




    /**
     * 语文挂件
     * 1.同步语文 com.eebbk.synchinese:id/widget_enterBtn
     * 2.快乐学汉字 com.eebbk.hanziLearning.activity:id/widget_enterBtn
     * 3.查字词 content-desc:查字词  com.eebbk.dict:id/widget_enterBtn
     * 4.小诸葛语基础班 com.eebbk.vtraining:id/widget_chinese_zhuge_goto
     * 5.阅读专项辅导 com.eebbk.vtraining:id/widget_chinese_read_goto
     * 6.百变精灵作文 com.eebbk.vtraining:id/widget_chinese_change_goto
     */
    /**
     * 数学挂件
     * 1.同步数学 com.eebbk.synmath:id/widget_enterBtn
     * 2.好题精练 com.bbk.studyos.launcher  content-desc
     * 3.一键搜  com.bbk.studyos.launcher  content-desc /com.eebbk.onesearchdark:id/app_widget_iamge1
     * 4.解体技巧专项 com.eebbk.vtraining:id/widget_tigao_goto
     * 5.学而思奥数 com.eebbk.vtraining:id/widget_peiyou_goto
     * 6.趣味应用题 com.eebbk.interestingapplications_junior:id/widget_enterBtn
     */

    //中学
    /**
     * 一键搜 com.bbk.studyos.launcher content-desc 挂件
     * 名师辅导班 content-desc 名师辅导班 挂件
     */
}


