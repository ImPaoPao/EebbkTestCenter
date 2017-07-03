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
        JSONObject obj =  new JSONObject();
//        this.getClass().getMethods();
//        Method m[] = this.getClass().getDeclaredMethods();
//        for (int i = 0; i < m.length; i++) {
//            String metName = m[i].getName();
//            obj.put("method:"+String.valueOf(i),metName);
//        }
//        instrumentationStatusOut(obj);
        obj.put("classes:",PackageConstants.class.getClasses());
        instrumentationStatusOut(obj);
    }

    /**
     * 语文学习
     */
    @Test
    public void launchHanziLearning() throws IOException, JSONException {
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

    /**数学学习
     *
     */
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

    /**英语学习
     *
     */
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

    /**
     *
     */
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

    @Test
    public void launchAncientprose() throws IOException, JSONException {
        clickIconStartApp(null, PackageConstants.Ancientprose.LABEL, PackageConstants.Ancientprose.PACKAGE, "", 2000,
                null, 10);
    }

    //九年级

    /**
     * 词典
     * 新英汉
     * 汉英大辞典
     * 现代汉语
     * 古代汉语
     * 成语大辞典
     * 专业词典
     */
    @Test
    public void launchAncientChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "古代汉语", PackageConstants.EebbkDict.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchModernChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "现代汉语", PackageConstants.EebbkDict.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchProChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "专业词典", PackageConstants.EebbkDict.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchNewEngChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "新英汉", PackageConstants.EebbkDict.PACKAGE, "", 2000,
                null, 10);
    }

    @Test
    public void launchIdiomDict() throws IOException, JSONException {
        clickIconStartApp("词典", "成语大辞典", PackageConstants.EebbkDict.PACKAGE, null, 2000,
                null, 10);
    }

    @Test
    public void launchChEngDict() throws IOException, JSONException {
        clickIconStartApp("词典", "汉英大辞典", PackageConstants.EebbkDict.PACKAGE, "", 2000,
                null, 10);
    }
}


