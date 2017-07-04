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
    public void launchOnesearchPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        clickIconStartApp(null, "app_widget_s3_iamge1", PackageConstants.OneSearchDark.PACKAGE, null, 5000, null, 1,
                false, true);
    }

    @Test
    public void launchVideosearchStudyPendant() throws IOException, UiObjectNotFoundException, JSONException,
            RemoteException, InterruptedException {
        clickIconStartApp(null, "app_widget_s3_iamge1", PackageConstants.VideosearchStudy.PACKAGE, null, 5000, null, 1,
                false, true);
    }
}
