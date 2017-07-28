package com.eebbk.test.performance;

import android.support.test.runner.AndroidJUnit4;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class BbkAppTestCase extends PerforTestCase {

    @Test
    public void getMethods() throws JSONException, IOException {
        //获取当前类中方法
        clearRunprocess();
        JSONObject obj = new JSONObject();
//        this.getClass().getMethods();
//        Method m[] = this.getClass().getDeclaredMethods();
//        for (int i = 0; i < m.length; i++) {
//            String metName = m[i].getName();
//            obj.put("method:"+String.valueOf(i),metName);
//        }
//        instrumentationStatusOut(obj);
        obj.put("classes:", PackageConstants.class.getClasses());
        instrumentationStatusOut(obj);
    }


    /**
     * 语文学习
     */
    @Test
    public void launchHaneiLearning() throws IOException, JSONException {
        clickIconStartApp("语文学习", PackageConstants.HanziLearning.LABEL, PackageConstants.HanziLearning.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchChaziCi() throws IOException, JSONException {
        clickIconStartApp("语文学习", PackageConstants.Chazici.LABEL, PackageConstants.Chazici.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchRelaxedtheme() throws IOException, JSONException {
        clickIconStartApp("语文学习", PackageConstants.Relaxedtheme.LABEL, PackageConstants.Relaxedtheme.PACKAGE,
                null, 5000, null, 1);
    }

    /**
     * 数学学习
     */
    @Test
    public void launchMathAnimation() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.MathAnimation.LABEL, PackageConstants.MathAnimation.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchMaindoexercise() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.Maindoexercise.LABEL, PackageConstants.Maindoexercise.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchInterestingapplications() throws IOException, JSONException {
        clickIconStartApp("数学学习", PackageConstants.Interestingapplications.LABEL, PackageConstants
                .Interestingapplications.PACKAGE, null, 5000, null, 1);
    }

    /**
     * 英语学习
     */
    @Test
    public void launchRecitewords() throws IOException, JSONException {
        clickIconStartApp("英语学习", PackageConstants.Recitewords.LABEL, PackageConstants.Recitewords.PACKAGE, null, 5000,
                null, 1);
    }


    @Test
    public void launchEnglishDict() throws IOException, JSONException {
        clickIconStartApp("英语学习", "查单词", PackageConstants.EebbkDict.PACKAGE, null, 5000, null, 1);
    }

    /**
     *
     */
    @Test
    public void launchOnesearch() throws IOException, JSONException {
        clickIconStartApp(null, "扫条码", PackageConstants.Onesearch.PACKAGE, null, 5000, null, 1);
    }

    @Test
    public void launchPrimarylisten() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Primarylisten.LABEL, PackageConstants.Primarylisten.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchBbkNewword() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkNewword.LABEL, PackageConstants.BbkNewword.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchOnekeycleaner() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Onekeycleaner.LABEL, PackageConstants.Onekeycleaner.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchFeedback() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Feedback.LABEL, PackageConstants.Feedback.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchBbkSafe() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkSafe.LABEL, PackageConstants.BbkSafe.PACKAGE, null, 5000, null, 1);
    }

    @Test
    public void launchTimetable() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Timetable.LABEL, PackageConstants.Timetable.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchBrowser() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Browser.LABEL, PackageConstants.Browser.PACKAGE, null, 5000, null, 1);
    }


    @Test
    public void launchFileexplorer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Fileexplorer.LABEL, PackageConstants.Fileexplorer.PACKAGE, null, 5000,
                null, 1);
    }


    @Test
    public void launchPersonal() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Personal.LABEL, PackageConstants.Personal.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchElectronicalbum() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Electronicalbum.LABEL, PackageConstants.Electronicalbum.PACKAGE, null,
                5000, null, 1);
    }

    @Test
    public void launchRecorder() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Recorder.LABEL, PackageConstants.Recorder.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchDeskclock() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Deskclock.LABEL, PackageConstants.Deskclock.PACKAGE, null, 5000, null,
                1);
    }


    @Test
    public void launchBbkWallpaper() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkWallpaper.LABEL, PackageConstants.BbkWallpaper.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchCalendar() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Calendar.LABEL, PackageConstants.Calendar.PACKAGE, null, 5000, null,
                1);
    }

    @Test
    public void launchVideoPlayer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.VideoPlayer.LABEL, PackageConstants.VideoPlayer.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchBbkMusicPlayer() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.BbkMusicPlayer.LABEL, PackageConstants.BbkMusicPlayer.PACKAGE,
                null, 5000, null, 1);
    }

}


