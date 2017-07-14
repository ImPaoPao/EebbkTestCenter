package com.eebbk.test.performance;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.eebbk.test.common.PackageConstants.VideosearchStudy;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class VideoSearchStudyTestCase extends PerforTestCase {
    @Test
    public void launchOneVideoStudy() throws IOException, UiObjectNotFoundException, JSONException, InterruptedException {
        clickIconStartApp(null, "智能答疑", VideosearchStudy.PACKAGE, null,3000, null, 1);
    }
}
