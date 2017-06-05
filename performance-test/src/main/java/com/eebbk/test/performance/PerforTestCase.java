package com.eebbk.test.performance;

import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.eebbk.test.automator.Automator;
import com.eebbk.test.common.BitmapHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.os.SystemClock.sleep;
import static android.support.test.InstrumentationRegistry.getArguments;

@RunWith(AndroidJUnit4.class)
public class PerforTestCase extends Automator {
    public static String TAG = "PerforTestCase";

    private FileWriter mWriter;
    private XmlSerializer mXml;
    private String mStartTime;

    protected int mCount = 2;
    protected int mType = 0;//0:冷启动 1:热启动
    protected String mNumber = "unknown";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        clearRunprocess();
        String count = getArguments().getString("count", "3");
        String type = getArguments().getString("type", "0");
        mNumber = getArguments().getString("number", "unknown");
        if (TextUtils.isDigitsOnly(count)) {
            mCount = Integer.parseInt(count);
        }
        if (TextUtils.isDigitsOnly(type)) {
            mType = Integer.parseInt(type);
        }

        File out = new File("/sdcard/performance-test/", mNumber);
        if (out.exists()) {
            File[] files = out.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        } else {
            out.mkdirs();
        }

        mWriter = new FileWriter(new File(out, "result.xml"));
        mXml = Xml.newSerializer();
        mXml.setOutput(mWriter);
        mXml.startDocument("UTF-8", false);
        mXml.text("\n");
        mXml.startTag(null, "Record");
    }


    public void startTestRecord() {
        Log.i(TAG, "record start time ");
        mStartTime = getCurrentDate();
    }

    public void stopTestRecord() {
        Log.i(TAG, "record endtime and infos");
        if (mStartTime != null) {
            try {
                mXml.text("\n");
                mXml.startTag(null, "Segment");
                mXml.attribute(null, "starttime", mStartTime);
                mXml.attribute(null, "endtime", getCurrentDate());
                mXml.endTag(null, "Segment");
            } catch (IOException e) {
                // Nothing to do
            }
        }
        mStartTime = null;
    }

    public void stopTestRecord(String loadtime, String refreshTime, String loadResult, String refreshResult) {
        Log.i(TAG, "record endtime and infos");
        if (mStartTime != null) {
            try {
                mXml.text("\n");
                mXml.startTag(null, "Segment");
                mXml.attribute(null, "starttime", mStartTime);
                mXml.attribute(null, "loadtime", loadtime);
                mXml.attribute(null, "refreshtime", refreshTime);
                mXml.attribute(null, "loadresult", loadResult);
                mXml.attribute(null, "refreshresult", refreshResult);
                mXml.attribute(null, "endtime", getCurrentDate());
                mXml.endTag(null, "Segment");
            } catch (IOException e) {
                // Nothing to do
            }
        }
        mStartTime = null;
    }

    public void clearRunprocess() throws IOException {
        mDevice.pressHome();
        mDevice.waitForIdle();
        mDevice.executeShellCommand("am start -W com.android.systemui/.recents.RecentsActivity");
        mDevice.waitForIdle();
        BySelector cleanAll = By.res("com.android.systemui", "clean_all");
        mDevice.wait(Until.hasObject(cleanAll), WAIT_TIME);
        UiObject2 cleanAllObj = mDevice.findObject(cleanAll);
        cleanAllObj.clickAndWait(Until.newWindow(), WAIT_TIME * 2);
        mDevice.pressHome();
        mDevice.waitForIdle();
    }

    public void swipeCurrentLauncher() {
        mDevice.pressHome();
        for (int j = 0; j < 3; j++)
            mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2, 0, mDevice.getDisplayHeight
                    () / 2, 20);
    }


    @After
    public void tearDown() throws IOException {
        mXml.text("\n");
        mXml.endTag(null, "Record");
        mXml.endDocument();
        mWriter.flush();
        mWriter.close();
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault());
        return sdf.format(new Date());
    }

    //output instrumentation status info
    public void instrumentationStatusOut(JSONObject obj) {
        Bundle b = new Bundle();
        b.putString(Instrumentation.REPORT_KEY_STREAMRESULT, obj.toString());
        InstrumentationRegistry.getInstrumentation().sendStatus(0, b);
    }


    protected Bitmap getHomeSourceScreen(BySelector bySelector, String startPackage, String widgetFlag, long
            waitTime) throws IOException, InterruptedException {
        swipeCurrentLauncher();
        mDevice.wait(Until.hasObject(bySelector), WAIT_TIME);
        UiObject2 synChineseObj = mDevice.findObject(bySelector);
        synChineseObj.clickAndWait(Until.newWindow(), WAIT_TIME);
        if (widgetFlag == null) {
            sleep(waitTime);
        } else {
            mDevice.wait(Until.hasObject(By.res(startPackage, widgetFlag)), WAIT_TIME);
            sleep(waitTime);
        }
        mDevice.takeScreenshot(new File("/sdcard/performance-test/" + mNumber + "/" + mNumber + ".png"));
        FileInputStream source_fis = new FileInputStream("/sdcard/performance-test/" + mNumber + "/" + mNumber + "" +
                ".png");
        Bitmap source_png = BitmapFactory.decodeStream(source_fis);
        mDevice.pressHome();
        clearRunprocess();
        return source_png;
    }


    protected Bitmap getHomeSourceScreen(BySelector bySelector, String startPackage, long waitTime) throws
            IOException, InterruptedException {
        return getHomeSourceScreen(bySelector, startPackage, null, waitTime);
    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Date timeStamp) throws JSONException {
        return doCompare(sourcePng, loadPngRect, null, timeStamp);

    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Rect refreshPngRect, Date timeStamp) throws
            JSONException {
        JSONObject obj = new JSONObject();
        int m = 0;
        Map<String, String> compareResult = new HashMap();
        int loadResult = 0;
        int refreshResult = 0;
        boolean loadFlag = true;
        do {
            m++;
            Bitmap des_png = mAutomation.takeScreenshot();
            if (loadFlag) {
                Bitmap loadPng = Bitmap.createBitmap(des_png, loadPngRect.left, loadPngRect.top, loadPngRect.width(),
                        loadPngRect.height());
                loadResult = BitmapHelper.compare(Bitmap.createBitmap(sourcePng, loadPngRect.left, loadPngRect.top,
                        loadPngRect.width(), loadPngRect.height()), loadPng);
            }
            if (loadResult <= 1 && loadFlag) {
                compareResult.put("loadResult", String.valueOf(loadResult));
                compareResult.put("loadTime", getCurrentDate());
                obj.put(String.valueOf(m) + "loadResult***************:", loadResult);
                obj.put(String.valueOf(m) + "loadtime***************:", getCurrentDate());
                obj.put(String.valueOf(m) + "mStartTime***************:", mStartTime);
                loadFlag = false;
            }
            if (refreshPngRect != null) {
                Bitmap refreshPng = Bitmap.createBitmap(des_png, refreshPngRect.left, refreshPngRect.top,
                        refreshPngRect.width(), refreshPngRect.height());
                refreshResult = BitmapHelper.compare(Bitmap.createBitmap(sourcePng, refreshPngRect.left,
                        refreshPngRect.top,
                        refreshPngRect.width(), refreshPngRect.height()), refreshPng);
                if (refreshPng != null && !refreshPng.isRecycled()) {
                    refreshPng.recycle();
                }
            } else {
                refreshResult = loadResult;
            }
            obj.put(String.valueOf(m) + "refreshResult:", refreshResult);
            if ((new Date().getTime() - timeStamp.getTime()) > WAIT_TIME * 4) {
                break;
            }
        } while (loadResult > 1 || refreshResult > 1);
        if (!compareResult.containsKey("loadTime")){
            compareResult.put("loadTime", getCurrentDate());
            compareResult.put("loadResult", String.valueOf(loadResult));
        }
        compareResult.put("refreshResult", String.valueOf(refreshResult));
        compareResult.put("refreshTime", getCurrentDate());
        instrumentationStatusOut(obj);
        return compareResult;
    }
}




