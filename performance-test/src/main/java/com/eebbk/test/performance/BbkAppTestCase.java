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
public class BbkAppTestCase.java extends PerforTestCase {
    //语文学习
    @Test
    public void launchSynChinese() throws IOException, UiObjectNotFoundException, JSONException, RemoteException,
            InterruptedException {
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect, refreshPngRect, match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadPngRect,match)
        //clickIconStartApp(folder, title, packageName, waitUi,timeout, loadId, idrefreshId, match)
        clickIconStartApp("语文学习", "同步语文", SynChinese.PACKAGE, "refresh",2000, null, 10);
    }
    @Test
	public void launchPersonal {
		public static final String PACKAGE = "com.bbk.personal";
		public static final String LABEL = "个人中心";
		clickIconStartApp(null, Personal.LABEL,Personal.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchHanziLearning {
		public static final String PACKAGE = "com.eebbk.hanziLearning.activity";
		public static final String LABEL = "快乐学汉字";
		clickIconStartApp(null, HanziLearning.LABEL,HanziLearning.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchEebbkDict {
		public static final String PACKAGE = "com.eebbk.dict";
		public static final String LABEL = "描红词典";
		clickIconStartApp(null, EebbkDict.LABEL,EebbkDict.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchMathAnimation {
		public static final String PACKAGE = "com.eebbk.mathanimation";
		public static final String LABEL = "快乐数学动画";
		clickIconStartApp(null, MathAnimation.LABEL,MathAnimation.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBbkTranslation {
		public static final String PACKAGE = "com.eebbk.translation";
		public static final String LABEL = "整句翻译";
		clickIconStartApp(null, BbkTranslation.LABEL,BbkTranslation.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBbkNewword {
		public static final String PACKAGE = "com.eebbk.newword";
		public static final String LABEL = "生词库";
		clickIconStartApp(null, BbkNewword.LABEL,BbkNewword.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBbkWallpaper {
		public static final String PACKAGE = "com.eebbk.studyos.wallpaper";
		public static final String LABEL = "随心壁纸";
		clickIconStartApp(null, BbkWallpaper.LABEL,BbkWallpaper.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchPrimarylisten {
		public static final String PACKAGE = "com.bbk.primarylisten";
		public static final String LABEL = "早晚听";
		clickIconStartApp(null, Primarylisten.LABEL,Primarylisten.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchPlaymanual {
		public static final String PACKAGE = "com.eebbk.playmanual";
		public static final String LABEL = "用户手册";
		clickIconStartApp(null, Playmanual.LABEL,Playmanual.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchServicecenter {
		public static final String PACKAGE = "com.eebbk.servicecenter";
		public static final String LABEL = "服务中心";
		clickIconStartApp(null, Servicecenter.LABEL,Servicecenter.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchRecorder {
		public static final String PACKAGE = "com.bbk.recorder";
		public static final String LABEL = "录音";
		clickIconStartApp(null, Recorder.LABEL,Recorder.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBBKUserSevice {
		public static final String PACKAGE = "com.eebbk.bfc.app.bfcbehavior";
		public static final String LABEL = "BBKUserSevice";
		clickIconStartApp(null, BBKUserSevice.LABEL,BBKUserSevice.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBbkSafe {
		public static final String PACKAGE = "com.eebbk.studyos.bbksafe";
		public static final String LABEL = "应用广告过滤";
		clickIconStartApp(null, BbkSafe.LABEL,BbkSafe.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchDeskclock {
		public static final String PACKAGE = "com.eebbk.deskclock";
		public static final String LABEL = "闹钟";
		clickIconStartApp(null, Deskclock.LABEL,Deskclock.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchUScannerService {
		public static final String PACKAGE = "com.eebbk.uscanner";
		public static final String LABEL = "UScannerService";
		clickIconStartApp(null, UScannerService.LABEL,UScannerService.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchElectronicalbum {
		public static final String PACKAGE = "com.eebbk.electronicalbum.activity";
		public static final String LABEL = "我的相册";
		clickIconStartApp(null, Electronicalbum.LABEL,Electronicalbum.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchInterestingapplications {
		public static final String PACKAGE = "com.eebbk.interestingapplications_junior";
		public static final String LABEL = "趣味应用题";
		clickIconStartApp(null, Interestingapplications.LABEL,Interestingapplications.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchOnekeycleaner {
		public static final String PACKAGE = "com.bbk.studyos.onekeycleaner";
		public static final String LABEL = "一键清理";
		clickIconStartApp(null, launchOnekeycleaner.LABEL,launchOnekeycleaner.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchOtaupdate {
		public static final String PACKAGE = "com.eebbk.tools.otaupdate";
		public static final String LABEL = "系统升级";
		clickIconStartApp(null, Otaupdate.LABEL,Otaupdate.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchCalendar {
		public static final String PACKAGE = "com.eebbk.calendar";
		public static final String LABEL = "日历";
		clickIconStartApp(null, Calendar.LABEL,Calendar.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchPadStudyOsTheme {
		public static final String PACKAGE = "com.eebbk.padstudyostheme";
		public static final String LABEL = "PadStudyOsTheme";
		clickIconStartApp(null, PadStudyOsTheme.LABEL,PadStudyOsTheme.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchRelaxedtheme {
		public static final String PACKAGE = "com.eebbk.relaxedtheme";
		public static final String LABEL = "轻松作文";
		clickIconStartApp(null, Relaxedtheme.LABEL,Relaxedtheme.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchAncientprose {
		public static final String PACKAGE = "com.eebbk.ancientprose";
		public static final String LABEL = "古文观止";
		clickIconStartApp(null, Ancientprose.LABEL,Ancientprose.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBrowser {
		public static final String PACKAGE = "com.eebbk.browser";
		public static final String LABEL = "浏览器";
		clickIconStartApp(null, Browser.LABEL,Browser.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchOnesearch {
		public static final String PACKAGE = "com.eebbk.onesearch";
		public static final String LABEL = "扫条码";
		clickIconStartApp(null, Onesearch.LABEL,Onesearch.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchMaindoexercise {
		public static final String PACKAGE = "com.eebbk.doexercise.view.maindoexercise";
		public static final String LABEL = "速算练习";
		clickIconStartApp(null, Maindoexercise.LABEL,Maindoexercise.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchFileexplorer {
		public static final String PACKAGE = "com.bbk.fileexplorer";
		public static final String LABEL = "资源管理";
		clickIconStartApp(null, Fileexplorer.LABEL,Fileexplorer.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchTimetable {
		public static final String PACKAGE = "com.bbk.timetable";
		public static final String LABEL = "课程表";
		clickIconStartApp(null, Timetable.LABEL,Timetable.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchRecitewords {
		public static final String PACKAGE = "com.eebbk.recitewords";
		public static final String LABEL = "背单词";
		clickIconStartApp(null, Recitewords.LABEL,Recitewords.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchParentSupport {
		public static final String PACKAGE = "com.eebbk.parentsupport";
		public static final String LABEL = "家长管理";
		clickIconStartApp(null, ParentSupport.LABEL,ParentSupport.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchVideoPlayer {
		public static final String PACKAGE = "com.eebbk.videoplayer";
		public static final String LABEL = "影视欣赏";
		clickIconStartApp(null, VideoPlayer.LABEL,VideoPlayer.PACKAGE, "",2000, null, 10);
	}
    @Test
	public void launchBbkMusicPlayer {
		public static final String PACKAGE = "com.eebbk.musicplayer";
		public static final String LABEL = "音乐";
		clickIconStartApp(null, BbkMusicPlayer.LABEL,BbkMusicPlayer.PACKAGE, "",2000, null, 10);
	}
}


