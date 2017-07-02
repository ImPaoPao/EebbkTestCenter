package com.eebbk.test.performance;

import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
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
import com.eebbk.test.automator.SystemProperties;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.os.SystemClock.sleep;
import static android.support.test.InstrumentationRegistry.getArguments;

@RunWith(AndroidJUnit4.class)
public class PerforTestCase extends Automator {
    public static String TAG = "PerforTestCase";
    public JSONObject sysout = new JSONObject();
//
//    private int sWidth = mDevice.getWidth();
//    private int sHeight=mDevice.getHeight();

    protected FileWriter mWriter;
    protected XmlSerializer mXml;
    protected String mStartTime;

    protected int mCount = 5;
    protected int mType = 0;
    protected String mNumber = "unknown";

    protected String mPkg = "unknown";//当前的被测应用包名


    protected int mSys = 0;
    protected int mApp = 0;

    protected String osVersion = SystemProperties.get("ro.build.version.sdk");//ro.build.version.sdk


    @Before
    public void setUp() throws Exception {
        super.setUp();
        clearRunprocess();
        String count = getArguments().getString("count", "3");
        String type = getArguments().getString("type", "0");
        mNumber = getArguments().getString("number", "unknown");
        mPkg = getArguments().getString("mpackage", "unknown");
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
        initSetup();
        clearRunprocess();
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
        SystemClock.sleep(2000);
        mDevice.waitForIdle();
        try {
            mDevice.pressRecentApps();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mDevice.waitForIdle();
        BySelector cleanBtn = By.res("com.android.systemui", "clean_all");
        mDevice.wait(Until.hasObject(cleanBtn), WAIT_TIME);
        UiObject2 cleanAll = mDevice.findObject(cleanBtn);
        if (cleanAll != null) {
            cleanAll.clickAndWait(Until.newWindow(), WAIT_TIME * 2);
            SystemClock.sleep(1000);
            mDevice.pressHome();
        }
        mDevice.pressHome();
        SystemClock.sleep(2000);
        mDevice.waitForIdle();
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
//        doStartActivity(sys, "1");
//        doStartActivity(app, "0");
    }

    public void doStartActivity(int flag) throws IOException {
//        if (flag == 0 || mType == 0) {
//            if (mSys > 0 && mApp > 0) {
//                doStartActivity(mSys, "1");
//                doStartActivity(mApp, "0");
//            } else {
//                doStartActivity(mSys > 0 ? mSys : mApp, mSys > 0 ? "1" : "0");
//            }
//        }
    }


    /*
    @num 启动的apk个数
    @type 启动apk的类型 sys 1 3app 0
     */
    public void doStartActivity(int num, String type) throws IOException {
//        Context mContext = null;
//        PackageManager mManager = mContext.getPackageManager();
//        String[] categories = {Intent.CATEGORY_LAUNCHER, Intent.CATEGORY_HOME};
//        List<String> packages = new ArrayList();
//        mDevice.waitForIdle(5000);
//        List<PackageInfo> pis = mManager.getInstalledPackages(0);
//        if (num > pis.size()) num = pis.size();
//        for (PackageInfo pi : pis) {
//            if (packages.size() >= num) {
//                break;
//            }
//            if ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == Integer.parseInt(type)) {
//                for (String category : categories) {
//                    Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(
//                            category).setPackage(pi.packageName);
//                    List<ResolveInfo> ris = mManager.queryIntentActivities(intent, 0);
//                    for (ResolveInfo ri : ris) {
//                        if ((ri.activityInfo.name != null) && (pi.packageName != mPkg) && (Integer.parseInt(type)
//                                == 0 ? (!pi.packageName.contains("com.eebbk") && !pi.packageName.contains("com.bbk"))
//                                : true)) {
//                            mDevice.executeShellCommand("am start -W " + pi.packageName + "/" + ri.activityInfo.name);
//                            packages.add(pi.packageName + "/" + ri.activityInfo.name);
//                            mDevice.waitForIdle(5000);
//                            mDevice.pressHome();
//                            break;
//                        }
//                    }
//                }
//            }
//        }
    }

    //图片对比:原图和两个待对比区域
    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Date timeStamp) throws JSONException {
        return doCompare(sourcePng, loadPngRect, null, timeStamp, 0);
    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Date timeStamp, int count) throws
            JSONException {
        return doCompare(sourcePng, loadPngRect, null, timeStamp, count);

    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Rect refreshPngRect, Date timeStamp) throws
            JSONException {
        return doCompare(sourcePng, loadPngRect, refreshPngRect, timeStamp, 0);
    }

    public Map<String, String> doCompare(Bitmap sourcePng, Rect loadPngRect, Rect refreshPngRect, Date timeStamp, int
            count) throws JSONException {
        JSONObject obj = new JSONObject();
        Map<String, String> compareResult = new HashMap();
//        int loadResult = 0;
//        int refreshResult = 0;
//        boolean loadFlag = true;
//        Bitmap refreshPng = null;
//        Bitmap loadPng = null;
//        Bitmap des_png = null;
//        int m = 0;
//        do {
//            m++;
//            des_png = mAutomation.takeScreenshot();
//            if (loadFlag) {
//                loadPng = Bitmap.createBitmap(des_png, loadPngRect.left, loadPngRect.top, loadPngRect.width(),
//                        loadPngRect.height());
//                loadResult = BitmapHelper.compare(Bitmap.createBitmap(sourcePng, loadPngRect.left, loadPngRect.top,
//                        loadPngRect.width(), loadPngRect.height()), loadPng);
//            }
//            if (loadFlag && loadResult <= 1) {
//                compareResult.put("loadResult", String.valueOf(loadResult));
//                compareResult.put("loadTime", getCurrentDate());
//                loadFlag = false;
//            }
//            if (refreshPngRect == null) {
//                refreshResult = loadResult;
//            } else {
//                refreshPng = Bitmap.createBitmap(des_png, refreshPngRect.left, refreshPngRect.top,
//                        refreshPngRect.width(), refreshPngRect.height());
//                refreshResult = BitmapHelper.compare(Bitmap.createBitmap(sourcePng, refreshPngRect.left,
//                        refreshPngRect.top,
//                        refreshPngRect.width(), refreshPngRect.height()), refreshPng);
//            }
//            if (((new Date().getTime() - timeStamp.getTime()) > WAIT_TIME * 4) || (loadResult <= 1 && refreshResult <=
//                    1)) {
//                if (!compareResult.containsKey("loadTime")) {
//                    compareResult.put("loadTime", getCurrentDate());
//                    compareResult.put("loadResult", String.valueOf(loadResult));
//                }
//                compareResult.put("refreshTime", getCurrentDate());
//                compareResult.put("refreshResult", String.valueOf(refreshResult));
//                String cycle;
//                if (count > 0) {
//                    cycle = String.valueOf(count);
//                } else {
//                    cycle = String.valueOf(mCount);
//                }
//                mHelper.saveScreenshot(loadPng, mNumber, "load_" + cycle);
//                if (refreshPngRect != null) {
//                    mHelper.saveScreenshot(refreshPng, mNumber, "refresh_" + cycle);
//                }
//                if (loadPng != null && !loadPng.isRecycled()) {
//                    loadPng.recycle();
//                    loadPng = null;
//                }
//                if (refreshPng != null && !refreshPng.isRecycled()) {
//                    refreshPng.recycle();
//                    refreshPng = null;
//                }
//                break;
//            } else {
//                if (loadFlag) {
//                    if (loadPng != null && !loadPng.isRecycled()) {
//                        loadPng.recycle();
//                        loadPng = null;
//                    }
//                }
//                if (refreshPng != null && !refreshPng.isRecycled()) {
//                    refreshPng.recycle();
//                    refreshPng = null;
//                }
//            }
//        } while (loadResult > 1 || refreshResult > 1);
//        instrumentationStatusOut(obj);
        return compareResult;
    }

    //重构代码:模块启动
    public void clickIconStartApp(String folder, String title,  String packageName, String waitUi,long timeout, Rect loadPngRect,int match) {
        clickIconStartApp(folder,title,packageName,waitUi,timeout,loadPngRect,null,match);
    }
    /*
    传递两个区域代表的控件id
    */
    public void clickIconStartApp(String folder, String title, String packageName, String waitUi,
                                  long timeout, String loadId, String refreshId, int match) {
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
        mDevice.wait(Until.hasObject(By.res(packageName, waitUi)), WAIT_TIME * 4);
        SystemClock.sleep(timeout);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);

        UiObject2 view = mDevice.findObject(By.res(packageName, loadId));
        Rect loadPngRect =view.getVisibleBounds();
        Rect refreshPngRect =null;
        Bitmap refreshSource = null;
        if(refreshId!=null){
            view = mDevice.findObject(By.res(packageName, refreshId));
            refreshPngRect =view.getVisibleBounds() ;
            refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                    refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        }else{
            refreshPngRect = new Rect(0, 0, source_png.getWidth(), loadPngRect.top);
        }
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon(folder, title, packageName);
            if (icon instanceof UiObject2) {
                startTestRecord();
                ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
            } else {
                try {
                    startTestRecord();
                    ((UiObject) icon).clickAndWaitForNewWindow();
                } catch (UiObjectNotFoundException e) {
                    // Nothing to do
                }
            }
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), match);
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
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
            loadSource = null;
        }
        if (refreshSource != null && !refreshSource.isRecycled()) {
            refreshSource.recycle();
            refreshSource = null;
        }
    }


    /*
    folder:文件夹名称,可为空
    title:应用图标名称
    timeout:首次保存源图片的等待时长
    packageName:应用包名
    waitUi:首次判断加载完成的标记UI
    loadPngRect:加载区域
    refreshPngRect:刷新区域
    match:匹配度
     */
    public void clickIconStartApp(String folder, String title, String packageName, String waitUi,
                                  long timeout, Rect loadPngRect, Rect refreshPngRect, int match) {
        JSONObject obj = new JSONObject();
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
        mDevice.wait(Until.hasObject(By.res(packageName, waitUi)), WAIT_TIME * 4);
        SystemClock.sleep(timeout);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        if(loadPngRect==null){
            loadPngRect = new Rect(0, 0, source_png.getWidth(), source_png.getHeight());
        }
        Bitmap loadSource = Bitmap.createBitmap(source_png, loadPngRect.left, loadPngRect.top,
                loadPngRect.width(), loadPngRect.height());
        Bitmap refreshSource = null;
        if(refreshPngRect!=null){
            refreshSource = Bitmap.createBitmap(source_png, refreshPngRect.left,
                    refreshPngRect.top, refreshPngRect.width(), refreshPngRect.height());
        }
        clearRunprocess();
        for (int i = 0; i < mCount; i++) {
            doStartActivity(i);
            icon = mHelper.openIcon(folder, title, packageName);
            if (icon instanceof UiObject2) {
                startTestRecord();
                ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
            } else {
                try {
                    startTestRecord();
                    ((UiObject) icon).clickAndWaitForNewWindow();
                } catch (UiObjectNotFoundException e) {
                    // Nothing to do
                }
            }
            Map<String, String> compareResult = doCompare(loadPngRect, refreshPngRect, loadSource, refreshSource, new
                    Date(), (i + 1), match);
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
        if (source_png != null && !source_png.isRecycled()) {
            source_png.recycle();
            source_png = null;
        }
        if (loadSource != null && !loadSource.isRecycled()) {
            loadSource.recycle();
            loadSource = null;
        }
        if (refreshSource != null && !refreshSource.isRecycled()) {
            refreshSource.recycle();
            refreshSource = null;
        }
    }

    //无内容加载部分,默认匹配度为10
    public Map<String, String> doCompare(Rect loadPngRect, Bitmap loadSource, Date timeStamp, int count,int match) throws
            JSONException {
        return doCompare(loadPngRect, null, loadSource,null, timeStamp, count, match);
    }
    public Map<String, String> doCompare(Rect loadPngRect, Bitmap loadSource, Date timeStamp, int count) throws
            JSONException {
        return doCompare(loadPngRect, null, loadSource,null, timeStamp, count, 10);
    }

    //不传递匹配度,则默认为10
    public Map<String, String> doCompare(Rect loadPngRect, Rect refreshPngRect, Bitmap loadSource,
                                         Bitmap refreshSource, Date timeStamp, int count) throws JSONException {
        return doCompare(loadPngRect, refreshPngRect, loadSource,
                refreshSource, timeStamp, count, 10);
    }

    public Map<String, String> doCompare(Rect loadPngRect, Rect refreshPngRect, Bitmap loadSource, Bitmap
            refreshSource, Date timeStamp, int count, int match) throws JSONException {
        JSONObject obj = new JSONObject();
        Map<String, String> compareResult = new HashMap();
        int loadResult = 0;
        int refreshResult = 0;
        boolean loadFlag = true;
        Bitmap refreshPng = null;
        Bitmap loadPng = null;
        Bitmap des_png = null;
        String lastTime = null;
        String startScreenTime = null;
        int m = 0;
        do {
            m++;
            lastTime = startScreenTime;
            startScreenTime = getCurrentDate();
            des_png = mAutomation.takeScreenshot();
            if (loadFlag) {
                loadPng = Bitmap.createBitmap(des_png, loadPngRect.left, loadPngRect.top, loadPngRect.width(),
                        loadPngRect.height());
                loadResult = BitmapHelper.compare(loadSource, loadPng);
                obj.put(String.valueOf(count) + "_" + String.valueOf(m) + "loadResult:", loadResult);
            }
            if (loadFlag && loadResult <= match) {
                compareResult.put("loadResult", String.valueOf(loadResult));
                if (mPkg == PackageConstants.Vtraining.PACKAGE) {
                    compareResult.put("loadTime", lastTime);
                } else {
                    compareResult.put("loadTime", startScreenTime);
                }
                obj.put(String.valueOf(count) + "_" + String.valueOf(m) + "lastTime:", lastTime);
                obj.put(String.valueOf(count) + "_" + String.valueOf(m) + "startScreenTime:", startScreenTime);
                loadFlag = false;
            }
            if (refreshPngRect == null) {
                refreshResult = loadResult;
            } else {
                refreshPng = Bitmap.createBitmap(des_png, refreshPngRect.left, refreshPngRect.top,
                        refreshPngRect.width(), refreshPngRect.height());
                refreshResult = BitmapHelper.compare(refreshSource, refreshPng);
            }
            obj.put(String.valueOf(count) + "_" + String.valueOf(m) + "end:", getCurrentDate());
            if (((new Date().getTime() - timeStamp.getTime()) > WAIT_TIME * 4) || (loadResult <= match &&
                    refreshResult <= match)) {
                if (!compareResult.containsKey("loadTime")) {
                    compareResult.put("loadTime", startScreenTime);
                    compareResult.put("loadResult", String.valueOf(loadResult));
                }
                compareResult.put("refreshTime", startScreenTime);
                compareResult.put("refreshResult", String.valueOf(refreshResult));
                String cycle;
                if (count > 0) {
                    cycle = String.valueOf(count);
                } else {
                    cycle = String.valueOf(mCount);
                }
                mHelper.saveScreenshot(loadPng, mNumber, "load_" + cycle);
                if (refreshPngRect != null) {
                    mHelper.saveScreenshot(refreshPng, mNumber, "refresh_" + cycle);
                }
                if (loadPng != null && !loadPng.isRecycled()) {
                    loadPng.recycle();
                    loadPng = null;
                }
                if (refreshPng != null && !refreshPng.isRecycled()) {
                    refreshPng.recycle();
                    refreshPng = null;
                }
                if (des_png != null && !des_png.isRecycled()) {
                    des_png.recycle();
                    des_png = null;
                }
                break;
            } else {
                if (loadFlag) {
                    if (loadPng != null && !loadPng.isRecycled()) {
                        loadPng.recycle();
                        loadPng = null;
                    }
                }
                if (refreshPng != null && !refreshPng.isRecycled()) {
                    refreshPng.recycle();
                    refreshPng = null;
                }
            }
            if (des_png != null && !des_png.isRecycled()) {
                des_png.recycle();
                des_png = null;
            }
        } while (loadResult > match || refreshResult > match);
        instrumentationStatusOut(obj);
        return compareResult;
    }
}