package com.eebbk.test.performance;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.eebbk.test.common.PackageConstants;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

/**
 * 中学挂件
 */
public class MiddlePendantTestCase extends PerforTestCase {

    @Override
    public void initSetup() throws UiObjectNotFoundException, IOException {
        initMiddleSetup();
    }

    @Test
    public void launchVtrainingPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        clickIconStartApp(null, "fake_goto_home", PackageConstants.Vtraining.PACKAGE, null, 5000, null, 1, false, true);
    }

    /**
     * 中学
     * 一键搜
     * 智能答疑
     */
    @Test
    public void launchMiddleOnesearchPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        clickIconStartApp(null, "app_widget_s3_iamge1", PackageConstants.OneSearchDark.PACKAGE, null, 5000,
                "btn_start_one_search", "overlay_view", 1, false, true);
    }

    @Test
    public void launchMiddleVideosearchStudyPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        clickIconStartApp(null, "app_widget_s3_iamge1", PackageConstants.VideosearchStudy.PACKAGE, null, 5000, null, 1,
                false, true);
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
        clickIconStartApp("词典", "古代汉语", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchModernChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "现代汉语", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchProChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "专业词典", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchNewEngChDict() throws IOException, JSONException {
        clickIconStartApp("词典", "新英汉", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchIdiomDict() throws IOException, JSONException {
        clickIconStartApp("词典", "成语大辞典", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }

    @Test
    public void launchChEngDict() throws IOException, JSONException {
        clickIconStartApp("词典", "汉英大辞典", PackageConstants.EebbkDict.PACKAGE, null, 5000,
                null, 1);
    }
}
