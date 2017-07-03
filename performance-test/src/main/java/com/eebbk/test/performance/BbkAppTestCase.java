package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class BbkAppTestCase extends PerforTestCase {
    //语文学习
    @Test
    public void launchSynChinese() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp("语文学习", "同步语文", PackageConstants.SynChinese.PACKAGE, "refresh", 2000, null, 10);
    }

    @Test
    public void launchHanziLearning() throws IOException, JSONException {
        //启动描红词典
        clickIconStartApp("语文学习", PackageConstants.HanziLearning.LABEL, PackageConstants.HanziLearning.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchChaziCi() throws IOException, JSONException {
        clickIconStartApp("语文学习", PackageConstants.Chazici.LABEL, PackageConstants.Chazici.PACKAGE, "", 2000, null,
                10);
    }

    @Test
    public void launchRelaxedtheme() throws IOException, JSONException {
        clickIconStartApp("语文学习", PackageConstants.Relaxedtheme.LABEL, PackageConstants.Relaxedtheme.PACKAGE,
                "", 2000, null, 10);
    }

//    @Test
//    public void launchEebbkDict() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.dict";
//        public static final String LABEL = "描红词典";
//        clickIconStartApp(null, PackageConstants.EebbkDict.LABEL, PackageConstants.EebbkDict.PACKAGE, "", 2000,
// null, 10);
//    }

    //数学学习

    @Test
    public void launchMathAnimation() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.MathAnimation.LABEL, PackageConstants.MathAnimation.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchMaindoexercise() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.Maindoexercise.LABEL, PackageConstants.Maindoexercise.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchInterestingapplications() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.Interestingapplications.LABEL, PackageConstants
                .Interestingapplications.PACKAGE, "", 2000, null, 10);
    }

    //英语学习

    @Test
    public void launchRecitewords() throws IOException, JSONException {
        clickIconStartApp("英语学习", PackageConstants.Recitewords.LABEL, PackageConstants.Recitewords.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchBbkTranslation() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkTranslation.LABEL, PackageConstants.BbkTranslation.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchEnglishDict() throws IOException, JSONException {
        clickIconStartApp(null, "查单词", PackageConstants.EebbkDict.PACKAGE, "", 2000, null, 10);
    }


    //三个学习

    @Test
    public void launchOnesearch() throws IOException, JSONException {
        clickIconStartApp(null, "扫条码", PackageConstants.Onesearch.PACKAGE, "", 2000, null, 10);
    }

    @Test
    public void launchPrimarylisten() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Primarylisten.LABEL, PackageConstants.Primarylisten.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchBbkNewword() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkNewword.LABEL, PackageConstants.BbkNewword.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchOnekeycleaner() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Onekeycleaner.LABEL, PackageConstants.Onekeycleaner.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchFeedback() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Feedback.LABEL, PackageConstants.Feedback.PACKAGE, "", 2000, null, 10);
    }

    @Test
    public void launchBbkSafe() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkSafe.LABEL, PackageConstants.BbkSafe.PACKAGE, "", 5000, null, 10);
    }

    @Test
    public void launchTimetable() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Timetable.LABEL, PackageConstants.Timetable.PACKAGE, "", 2000, null,
                10);
    }

    @Test
    public void launchBrowser() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Browser.LABEL, PackageConstants.Browser.PACKAGE, "", 2000, null, 10);
    }


    @Test
    public void launchFileexplorer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Fileexplorer.LABEL, PackageConstants.Fileexplorer.PACKAGE, "", 2000,
                null, 10);
    }


    @Test
    public void launchPersonal() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Personal.LABEL, PackageConstants.Personal.PACKAGE, "", 2000, null, 10);
    }

    @Test
    public void launchElectronicalbum() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Electronicalbum.LABEL, PackageConstants.Electronicalbum.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchRecorder() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Recorder.LABEL, PackageConstants.Recorder.PACKAGE, "", 2000, null, 10);
    }

    @Test
    public void launchDeskclock() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Deskclock.LABEL, PackageConstants.Deskclock.PACKAGE, "", 2000, null,
                10);
    }


    @Test
    public void launchBbkWallpaper() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkWallpaper.LABEL, PackageConstants.BbkWallpaper.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchCalendar() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Calendar.LABEL, PackageConstants.Calendar.PACKAGE, "", 2000, null, 10);
    }

    @Test
    public void launchVideoPlayer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.VideoPlayer.LABEL, PackageConstants.VideoPlayer.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchBbkMusicPlayer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkMusicPlayer.LABEL, PackageConstants.BbkMusicPlayer.PACKAGE,
                "", 2000, null, 10);
    }
    //一键换壁纸：显示为桌面应用

    //三个同步挂件
    /*
    1.泡泡少儿词汇
    2.剑桥少儿英语
    3.新概念英语

     */


    @Test
    public void launchPlaymanual() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.playmanual";
//        public static final String LABEL = "用户手册";
        clickIconStartApp(null, PackageConstants.Playmanual.LABEL, PackageConstants.Playmanual.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchServicecenter() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.servicecenter";
//        public static final String LABEL = "服务中心";
        clickIconStartApp(null, PackageConstants.Servicecenter.LABEL, PackageConstants.Servicecenter.PACKAGE, "",
                2000, null, 10);
    }


    @Test
    public void launchBBKUserSevice() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.bfc.app.bfcbehavior";
//        public static final String LABEL = "BBKUserSevice";
        clickIconStartApp(null, PackageConstants.BBKUserSevice.LABEL, PackageConstants.BBKUserSevice.PACKAGE, "",
                2000, null, 10);
    }


    @Test
    public void launchUScannerService() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.uscanner";
//        public static final String LABEL = "UScannerService";
        clickIconStartApp(null, PackageConstants.UScannerService.LABEL, PackageConstants.UScannerService.PACKAGE, "",
                2000, null, 10);
    }


    @Test
    public void launchOtaupdate() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.tools.otaupdate";
//        public static final String LABEL = "系统升级";
        clickIconStartApp(null, PackageConstants.Otaupdate.LABEL, PackageConstants.Otaupdate.PACKAGE, "", 2000, null,
                10);
    }


    @Test
    public void launchPadStudyOsTheme() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.padstudyostheme";
//        public static final String LABEL = "PadStudyOsTheme";
        clickIconStartApp(null, PackageConstants.PadStudyOsTheme.LABEL, PackageConstants.PadStudyOsTheme.PACKAGE, "",
                2000, null, 10);
    }

    @Test
    public void launchAncientprose() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.ancientprose";
//        public static final String LABEL = "古文观止";
        clickIconStartApp(null, PackageConstants.Ancientprose.LABEL, PackageConstants.Ancientprose.PACKAGE, "", 2000,
                null, 10);
    }


    @Test
    public void launchParentSupport() throws IOException, JSONException {
//        public static final String PACKAGE = "com.eebbk.parentsupport";
//        public static final String LABEL = "家长管理";
        clickIconStartApp(null, PackageConstants.ParentSupport.LABEL, PackageConstants.ParentSupport.PACKAGE, "",
                2000, null, 10);
    }

}


