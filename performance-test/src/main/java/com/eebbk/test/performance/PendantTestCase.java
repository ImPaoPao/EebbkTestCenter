package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 桌面挂件启动速度
 */

@RunWith(AndroidJUnit4.class)
public class PendantTestCase extends PerforTestCase {


    @Test
    public void compareTest() throws JSONException, IOException {
        //获取当前类中方法
        clearRunprocess();
        JSONObject obj =  new JSONObject();
        this.getClass().getMethods();
        Method m[] = this.getClass().getDeclaredMethods();
        for (int i = 0; i < m.length; i++) {
            String metName = m[i].getName();
            obj.put("method:"+String.valueOf(i),metName);
        }
        instrumentationStatusOut(obj);
    }


    //Demo
//    @Test
//    public void launchSynChinese() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
//            InterruptedException {
//        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
//        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
//        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
//        clickIconStartApp("语文学习", "同步语文", PackageConstants.SynChinese.PACKAGE, "refresh", 2000, null, 10);
//        mDevice.findObject(new UiSelector().descriptionContains("英语听说"));
//    }

    //小学

    /**
     * 英语挂件
     * 1.泡泡少儿词汇  com.eebbk.vtraining:id/widget_paopao_goto
     * 2.剑桥少儿英语 com.eebbk.vtraining:id/widget_jianqiao_goto
     * 3.新概念英语 com.eebbk.vtraining:id/widget_new_goto
     * 4.同步英语 com.eebbk.syncenglish:id/widgetListenBtn
     * 5.英语听说 android.appwidget.AppWidgetHostView content-desc mDevice.findObject(new UiSelector().descriptionContains
     * ("英语听说"));
     * 6.查单词 com.eebbk.dict:id/widget_enterBtn
     */

    private void startVtraining(String title, int match, boolean isId) throws IOException, JSONException {
        clickIconStartApp(null, title, PackageConstants.Vtraining.PACKAGE, null, 5000, null, match, false, isId);
    }

    private void startApp(String title, String pkg, int match, boolean isId) throws IOException, JSONException {
        clickIconStartApp(null, title, pkg, null, 5000, null, match, false, isId);
    }
//    private void startByContentDesc(String title,String pkg,int match) throws IOException, JSONException {
//        clickIconStartApp(null, title, pkg, null, 5000, null, match, false, false);
//    }
//    private void startById(String title,String pkg,int match) throws IOException, JSONException {
//        clickIconStartApp(null, title, pkg, null, 5000, null, match, false, true);
//    }

    @Test
    public void launchSynEnglishPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("widgetListenBtn", PackageConstants.SyncEnglish.PACKAGE, 1, true);
    }

    @Test
    public void launchEnglishTalkPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("英语听说", PackageConstants.EnglishTalk.PACKAGE, 1, false);
    }

    @Test
    public void launchEnDict() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        startApp("查单词", PackageConstants.SyncEnglish.PACKAGE, 1, false);
    }

    @Test
    public void launchNew() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        startVtraining("widget_new_goto", 1, true);
    }

    @Test
    public void launchBubleKid() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        startVtraining("widget_paopao_goto", 1, true);
    }

    @Test
    public void launchJianQiao() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        startVtraining("widget_jianqiao_goto", 1, true);
    }

//    @Test
//    public void launchBubleKid() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
//            InterruptedException {
//        clickIconStartApp(null, "widget_paopao_goto", PackageConstants.Vtraining.PACKAGE, null, 5000, null,1,false,
// true);
//    }


    /**
     * 语文挂件
     * 1.同步语文 com.eebbk.synchinese:id/widget_enterBtn
     * 2.快乐学汉字 com.eebbk.hanziLearning.activity:id/widget_enterBtn
     * 3.查字词 content-desc:查字词  com.eebbk.dict:id/widget_enterBtn
     * 4.小诸葛语基础班 com.eebbk.vtraining:id/widget_chinese_zhuge_goto
     * 5.阅读专项辅导 com.eebbk.vtraining:id/widget_chinese_read_goto
     * 6.百变精灵作文 com.eebbk.vtraining:id/widget_chinese_change_goto
     */

    @Test
    public void launchHanziLeaning() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("widget_enterBtn", "com.eebbk.hanziLearning.activity", 1, true);
    }

    @Test
    public void launchChazici() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("查字词", PackageConstants.EebbkDict.PACKAGE, 1, false);
    }

    @Test
    public void launchSynChinesePendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("widget_enterBtn", PackageConstants.SynChinese.PACKAGE, 1, true);
    }

    @Test
    public void launchZhuge() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startVtraining("widget_chinese_zhuge_goto", 1, true);
    }

    @Test
    public void launchRead() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startVtraining("widget_chinese_read_goto", 1, true);
    }

    @Test
    public void launchChange() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startVtraining("widget_chinese_change_goto", 1, true);
    }

    /**
     * 数学挂件
     * 1.同步数学 com.eebbk.synmath:id/widget_enterBtn
     * 2.好题精练 com.bbk.studyos.launcher  content-desc
     * 3.一键搜  com.bbk.studyos.launcher  content-desc /com.eebbk.onesearchdark:id/app_widget_iamge1
     * 4.解体技巧专项 com.eebbk.vtraining:id/widget_tigao_goto
     * 5.学而思奥数 com.eebbk.vtraining:id/widget_peiyou_goto
     * 6.趣味应用题 com.eebbk.interestingapplications_junior:id/widget_enterBtn
     */
    @Test
    public void launchIntrestingApp() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("widget_enterBtn", "com.eebbk.interestingapplications_junior", 1, true);
    }

    @Test
    public void launchTigao() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startVtraining("widget_tigao_goto", 1, true);
    }

    @Test
    public void launchPeiyou() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startVtraining("widget_peiyou_goto", 1, true);
    }

    @Test
    public void launchSynMathPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("widget_enterBtn", PackageConstants.SynMath.PACKAGE, 1, true);
    }

    @Test
    public void launchQuestionPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("layout", PackageConstants.QuestionDatabase.PACKAGE, 1, true);
    }

    @Test
    public void launchOneSearchPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        startApp("app_widget_iamge1", PackageConstants.OneSearchDark.PACKAGE, 1, true);
    }
    //中学

    /**
     * 一键搜 com.bbk.studyos.launcher content-desc 挂件
     * 名师辅导班 content-desc 名师辅导班 挂件
     */
    @Test
    public void launchVtrainingPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        initMiddleSetup();
        startVtraining("fake_goto_home", 1, true);
    }
}