package com.eebbk.test.performance;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.eebbk.test.automator.Automator;
import com.eebbk.test.common.BitmapHelper;
import com.eebbk.test.common.PackageConstants;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.os.SystemClock.sleep;
import static android.support.test.InstrumentationRegistry.getArguments;

@RunWith(AndroidJUnit4.class)
public class PerforTestCase extends Automator {
    public static String TAG = "PerforTestCase";

    protected FileWriter mWriter;
    protected XmlSerializer mXml;
    protected String mStartTime;

    protected int mCount = 3;
    protected int mType = 0;
    protected String mNumber = "unknown";

    protected String mPkg = "unknown";//当前的被测应用包名


    protected int mSys = 0;
    protected int mApp = 0;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        clearRunprocess();
        String count = getArguments().getString("count", "3");
        String type = getArguments().getString("type", "0");
        mNumber = getArguments().getString("number", "unknown");
        String sys = getArguments().getString("sysnum", "0");
        String app = getArguments().getString("appnum", "0");
        if (TextUtils.isDigitsOnly(sys)) {
            mSys = Integer.parseInt(sys);
        }
        if (TextUtils.isDigitsOnly(app)) {
            mApp = Integer.parseInt(app);
        }
        if (TextUtils.isDigitsOnly(type)) {
            mType = Integer.parseInt(type);
        }
        if (TextUtils.isDigitsOnly(count)) {
            mCount = Integer.parseInt(count);
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
//        initSetup();
//        clearRunprocess();
    }

    public void initSetup() throws UiObjectNotFoundException, IOException {
        initPrimarySetup();
    }

    public void initPrimarySetup() throws UiObjectNotFoundException, IOException {
        mDevice.waitForIdle();
        UiObject2 user = mDevice.findObject(By.res(PackageConstants.Launcher.PACKAGE, "personal_grade"));
        if (!user.getText().contains("小学")) {
            user = mDevice.findObject(By.res(PackageConstants.Launcher.PACKAGE, "personal_head_layout"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "checkbox")), WAIT_TIME);
            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "checkbox"));
            if (user != null) {
                user.click();//点击不再提示
                mDevice.pressBack();//点击不再提示后,返回键即可回到信息编辑页面
                mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "beta_edt_current_main")),
                        WAIT_TIME);
            }
            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "beta_edt_current_main"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "user_grade_editabl"));//年级信息编辑
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            UiScrollable gradeList = new UiScrollable(new UiSelector().className("android.widget.ListView"));
            gradeList.scrollBackward(20);
            user = mDevice.findObject(By.textContains("三年级"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "save_person_btn"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.pressHome();
            mDevice.waitForIdle(10000);
            clearRunprocess();
        }

    }

    public void initMiddleSetup() throws UiObjectNotFoundException, IOException {
        mDevice.waitForIdle();
        UiObject2 user = mDevice.findObject(By.res(PackageConstants.Launcher.PACKAGE, "personal_grade"));
        if (!user.getText().contains("中学")) {
            user = mDevice.findObject(By.res(PackageConstants.Launcher.PACKAGE, "personal_head_layout"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
//            mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "checkbox")), WAIT_TIME);
//            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "checkbox"));
//            if (user != null) {
//                user.click();//点击不再提示
//                mDevice.pressBack();//点击不再提示后,返回键即可回到信息编辑页面
//                mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "beta_edt_current_main")),
//                        WAIT_TIME);
//            }


//
//            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "beta_edt_current_main"));
//            user.clickAndWait(Until.newWindow(), WAIT_TIME);
//            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "user_grade_editabl"));//年级信息编辑

            mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "add_grade_location")), WAIT_TIME
                    * 2);
            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "add_grade_location"));
//            if (user != null) {
            user.clickAndWait(Until.newWindow(), WAIT_TIME);//登录界面年级切换
            SystemClock.sleep(5000);
//                mDevice.pressBack();//点击不再提示后,返回键即可回到信息编辑页面
//                mDevice.wait(Until.hasObject(By.res(PackageConstants.Personal.PACKAGE, "beta_edt_current_main")),
//                        WAIT_TIME);
//            }
//
//            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            UiScrollable gradeList = new UiScrollable(new UiSelector().className("android.widget.ListView"));
            gradeList.scrollForward(20);
            user = mDevice.findObject(By.textContains("高中"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
//            user = mDevice.findObject(By.res(PackageConstants.Personal.PACKAGE, "save_person_btn"));
//            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.pressHome();
            mDevice.waitForIdle(10000);
            clearRunprocess();
        }
    }

    public void startTestRecord() {
        Log.i(TAG, "record start time ");
        mStartTime = getCurrentDate();
    }

    public void stopTestRecord(String value) {
        Log.i(TAG, "record endtime and infos");
        if (mStartTime != null) {
            try {
                mXml.text("\n");
                mXml.startTag(null, "Segment");
                mXml.attribute(null, "starttime", mStartTime);
                mXml.attribute(null, "memory", value);
                mXml.attribute(null, "endtime", getCurrentDate());
                mXml.endTag(null, "Segment");
            } catch (IOException e) {
                // Nothing to do
            }
        }
        mStartTime = null;
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


    //前置条件1:执行用例前启动app
    /*
    @sys 启动系统应用个数
    @app 启动三方应用个数
    */
    public void doStartActivity(int sys, int app) throws IOException {
        doStartActivity(sys, "1");
        doStartActivity(app, "0");
    }

    public void doStartActivity(int flag) throws IOException {
        JSONObject obj = new JSONObject();
        if (flag == 0 || mType == 0) {
            if (mSys > 0 && mApp > 0) {
                try {
                    obj.put("dostart:mSys&mApp ", (mSys & mApp));
                    obj.put("dostart:mSys ", mSys);
                    obj.put("dostart:mApp ", mApp);
                    obj.put("pkg:pkg ", mPkg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                doStartActivity(mSys, "1");
                doStartActivity(mApp, "0");
            } else {
                try {
                    obj.put("dostart else:mSys & mApp ", (mSys & mApp));
                    obj.put("dostart else:mSys ", mSys);
                    obj.put("dostart else:mApp ", mApp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                doStartActivity(mSys > 0 ? mSys : mApp, mSys > 0 ? "1" : "0");
            }
        }
        instrumentationStatusOut(obj);
    }


    /*
    @num 启动的apk个数
    @type 启动apk的类型 sys 1 3app 0
     */
    public void doStartActivity(int num, String type) throws IOException {
        PackageManager mManager = mContext.getPackageManager();
        String[] categories = {Intent.CATEGORY_LAUNCHER, Intent.CATEGORY_HOME};
        List<String> packages = new ArrayList();
        mDevice.waitForIdle(5000);
        JSONObject obj = new JSONObject();
        List<PackageInfo> pis = mManager.getInstalledPackages(0);
        if (num > pis.size()) num = pis.size();
        for (PackageInfo pi : pis) {
            if (packages.size() >= num) {
                break;
            }
            if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == Integer.parseInt(type)) {
                for (String category : categories) {
                    Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(
                            category).setPackage(pi.packageName);
                    List<ResolveInfo> ris = mManager.queryIntentActivities(intent, 0);
                    for (ResolveInfo ri : ris) {
                        if ((ri.activityInfo.name != null) && (pi.packageName != mPkg) && (Integer.parseInt(type)
                                == 0 ? (!pi.packageName.contains("com.eebbk") && !pi.packageName.contains("com.bbk"))
                                : true)) {
                            mDevice.executeShellCommand("am start -W " + pi.packageName + "/" + ri.activityInfo.name);
                            packages.add(pi.packageName + "/" + ri.activityInfo.name);
                            mDevice.waitForIdle(5000);
                            mDevice.pressHome();
                            break;
                        }
                    }
                }
            }
        }
        try {
            obj.put("packages", packages);
        } catch (JSONException je) {
            //
        }
        instrumentationStatusOut(obj);
    }

    //截图
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

    //图片对比
    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Date timeStamp) throws JSONException {
        return doCompare(sourcePng, loadPngRect, null, timeStamp);

    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Rect refreshPngRect, Date timeStamp) throws
            JSONException {
        return doCompare(sourcePng, loadPngRect, refreshPngRect, timeStamp, 0);
    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Rect refreshPngRect, Date timeStamp, int
            count) throws
            JSONException {
        JSONObject obj = new JSONObject();
        int m = 0;
        Map<String, String> compareResult = new HashMap();
        int loadResult = 0;
        int refreshResult = 0;
        boolean loadFlag = true;
        Bitmap refreshPng = null;
        Bitmap loadPng = null;
        Bitmap thumbnail;//= null;
        do {
            m++;
            Bitmap des_png = mAutomation.takeScreenshot();
            if (loadFlag) {
                loadPng = Bitmap.createBitmap(des_png, loadPngRect.left, loadPngRect.top, loadPngRect.width(),
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
                refreshPng = Bitmap.createBitmap(des_png, refreshPngRect.left, refreshPngRect.top,
                        refreshPngRect.width(), refreshPngRect.height());
                refreshResult = BitmapHelper.compare(Bitmap.createBitmap(sourcePng, refreshPngRect.left,
                        refreshPngRect.top,
                        refreshPngRect.width(), refreshPngRect.height()), refreshPng);
            } else {
                refreshResult = loadResult;
            }
            obj.put(String.valueOf(m) + "refreshResult:", refreshResult);
            if (((new Date().getTime() - timeStamp.getTime()) > WAIT_TIME * 4) || (loadResult <= 1 & refreshResult <=
                    1)) {
                obj.put(String.valueOf(m) + "timeout or result:", "saveScreenShot");
                if (!compareResult.containsKey("loadTime")) {
                    compareResult.put("loadTime", getCurrentDate());
                    compareResult.put("loadResult", String.valueOf(loadResult));
                }
                compareResult.put("refreshTime", getCurrentDate());
                compareResult.put("refreshResult", String.valueOf(refreshResult));
                thumbnail = ThumbnailUtils.extractThumbnail(loadPng, mDevice
                        .getDisplayWidth(), mDevice.getDisplayHeight());
                String forloop;
                if (count > 0) {
                    forloop = String.valueOf(count);
                } else {
                    forloop = String.valueOf(mCount);
                }
                mHelper.saveScreenshot(thumbnail, mNumber, "load_" + forloop);
                thumbnail = ThumbnailUtils.extractThumbnail(refreshPng, mDevice.getDisplayWidth(), mDevice
                        .getDisplayHeight());
                mHelper.saveScreenshot(thumbnail, mNumber, "refresh_" + forloop);
                SystemClock.sleep(1000);
                break;
            }
        } while (loadResult > 1 || refreshResult > 1);
        instrumentationStatusOut(obj);
        return compareResult;
    }

    public void clickLauncherIconStartApp(String folder, String title, String packageName, String waitUi) throws
            IOException, JSONException {
        Object icon = mHelper.openIcon(folder, title, packageName);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.wait(Until.hasObject(By.res(packageName, waitUi)), WAIT_TIME);
        mDevice.waitForIdle();
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        //下方菜单条
        Rect loadPngRect = getLoadRect(source_png);
        Rect refreshPngRect = getRefreshRect(source_png);//除了下方条外的其它部分。
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon(folder, title, packageName);
            if (icon instanceof UiObject2) {
                startTestRecord();
                ((UiObject2) icon).click();
            } else {
                try {
                    startTestRecord();
                    ((UiObject) icon).click();
                } catch (UiObjectNotFoundException e) {
                    // Nothing to do
                }
            }
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            mDevice.wait(Until.hasObject(By.res(packageName, waitUi)), WAIT_TIME);
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.pressHome();
            if (mType == 1) {
                mDevice.pressHome();
            } else {
                clearRunprocess();
            }
            mDevice.waitForIdle();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }


    public Rect getLoadRect(Bitmap source_png) {
        Rect loadPngRect = new Rect(0, source_png.getHeight() - 70, source_png.getWidth(), source_png.getHeight());
        return loadPngRect;
    }

    public Rect getRefreshRect(Bitmap source_png) {
        Rect refreshPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight() - 200);
        return refreshPngRect;
    }
}




