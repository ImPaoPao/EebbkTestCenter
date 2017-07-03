package com.eebbk.test.automator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.eebbk.test.common.PackageConstants.BbkMiddleMarket;
import com.eebbk.test.common.PackageConstants.EnglishTalk;
import com.eebbk.test.common.PackageConstants.Launcher;
import com.eebbk.test.common.PackageConstants.OneSearchDark;
import com.eebbk.test.common.PackageConstants.QuestionDatabase;
import com.eebbk.test.common.PackageConstants.SynChinese;
import com.eebbk.test.common.PackageConstants.SynMath;
import com.eebbk.test.common.PackageConstants.SyncEnglish;
import com.eebbk.test.common.PackageConstants.SystemUi;
import com.eebbk.test.common.PackageConstants.Vision;
import com.eebbk.test.common.PackageConstants.Vtraining;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static com.eebbk.test.automator.Automator.WAIT_TIME;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AutomatorHelper {

    private UiDevice mDevice;

    public AutomatorHelper(UiDevice device) {
        mDevice = device;
    }

    public boolean sleep() {
        try {
            mDevice.sleep();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean wakeUp() {
        try {
            mDevice.wakeUp();
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void lock() {
        sleep();
        wakeUp();
        SystemClock.sleep(1500);
    }

    public void unlock() {
        wakeUp();
        UiObject2 lock = mDevice.findObject(By.res(SystemUi.PACKAGE, "userwallpaper"));
        if (lock != null && mDevice.hasObject(By.res(SystemUi.PACKAGE, "keyguard_host_view"))) {
            Point p = lock.getVisibleCenter();
            mDevice.swipe(p.x, p.y, p.x, 0, 5);
            mDevice.pressHome();
        }
    }

    public void openLauncher() {
        unlock();
        mDevice.pressHome();
        mDevice.waitForIdle();
        BySelector selector = By.res(Automator.PATTERN_BUTTON1);
        UiObject2 ok = mDevice.findObject(selector);
        if (ok != null) {
            ok.click();
            mDevice.wait(Until.gone(selector), WAIT_TIME);
        }

        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat("没有安装启动器", launcherPackage, notNullValue());
        assertThat("返回主界面失败",
                mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), WAIT_TIME), is(true));
    }

    public void longClick(int x, int y) {
        mDevice.drag(x, y, x, y, 5);
    }

    public void longClick(UiObject obj) {
        Rect r = null;
        try {
            r = obj.getVisibleBounds();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (r != null) {
            longClick(r.centerX(), r.centerY());
        }
    }

    public void longClick(UiObject2 obj) {
        Point p = obj.getVisibleCenter();
        longClick(p.x, p.y);
    }

    public Bitmap getBitmap(Rect r) {
        Bitmap source = getInstrumentation().getUiAutomation().takeScreenshot();
        return Bitmap.createBitmap(source, r.left, r.top, r.width(), r.height());
    }

    public Bitmap getBitmap(UiObject obj) {
        Bitmap target = null;
        try {
            target = getBitmap(obj.getVisibleBounds());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        return target;
    }

    public Bitmap getBitmap(UiObject2 obj) {
        return getBitmap(obj.getVisibleBounds());
    }

    public Bitmap takeScreenshot(String folder) throws FileNotFoundException {
        return takeScreenshot(folder, "source");
    }

    public Bitmap takeScreenshot(String folder, String fileName) throws FileNotFoundException {
        String sourcePath = "/sdcard/performance-test/" + folder + "/" + fileName +".png";
        mDevice.takeScreenshot(new File(sourcePath));

        
        FileInputStream source_fis = new FileInputStream("/sdcard/performance-test/" + folder + "/" + fileName + "" +
                ".png");
        Bitmap source_png = BitmapFactory.decodeStream(source_fis);


        //预计修改加载到内存中的像素大小

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
//        BitmapFactory.decodeFile(sourcePath, options);
//        int height = options.outHeight;
//        int width= options.outWidth;
//        int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
//        int minLen = Math.min(height, width); // 原图的最小边长
//        if(minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
//            float ratio = (float)minLen / 100.0f; // 计算像素压缩比例
//            inSampleSize = (int)ratio;
//        }
//        options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
//        options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
//        Bitmap source_png = BitmapFactory.decodeFile(sourcePath, options); // 解码文件
        return source_png;
    }


//    public boolean saveScreenshot(Bitmap screenshot, String folder) {
//        return saveScreenshot(screenshot, folder, null);
//    }

    public boolean saveScreenshot(Bitmap screenshot, String folder, String fileName) {
        if (screenshot == null) {
            return false;
        }
        BufferedOutputStream bos = null;
        try {
            Log.v("UiAutomatorHelper", "I am log");
            bos = new BufferedOutputStream(new FileOutputStream(new File("/sdcard/performance-test/" + folder +
                    "/" + fileName + ".png")));
            if (bos != null) {
                screenshot.compress(Bitmap.CompressFormat.PNG, 100, bos);
                bos.flush();
            }
        } catch (IOException ioe) {
            Log.e("UiAutomatorHelper", "failed to save screen shot to file", ioe);
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ioe) {
                    /* ignore */
                }
            }
            if (screenshot != null && !screenshot.isRecycled()) {
                screenshot.recycle();
            }
        }
        return true;
    }

    public boolean takeScreenshot(Rect r, File storePath, int quality) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(storePath);
        } catch (IOException e) {
            // Nothing to do
        }
        if (out != null) {
            Bitmap source = getInstrumentation().getUiAutomation().takeScreenshot();
            Bitmap bitmap = Bitmap.createBitmap(source, r.left, r.top, r.width(), r.height());
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            return true;
        }
        return false;
    }

    public boolean takeScreenshot(UiObject obj, File storePath, int quality) {
        Rect r = null;
        try {
            r = obj.getVisibleBounds();
        } catch (UiObjectNotFoundException e) {
            // Nothing to do
        }
        return r != null && takeScreenshot(r, storePath, quality);
    }

    public boolean takeScreenshot(UiObject2 obj, File storePath, int quality) {
        return takeScreenshot(obj.getVisibleBounds(), storePath, quality);
    }

    public Object findIcon(String title) {
        openLauncher();
        mDevice.waitForIdle();
        UiObject2 label = mDevice.findObject(By.text(title));
        if (label == null) {
            UiObject2 indicator = mDevice.findObject(By.res(Launcher.PACKAGE, "paged_view_indicator"));
            if (indicator != null) {
                int flag = 0;
                List<UiObject2> children = indicator.getChildren();
                for (int i = children.size() - 1; i > 0; i--) {
                    if (children.get(i).isChecked()) {
                        flag = i;
                        break;
                    }
                }
                if (flag <= children.size() / 2) {
                    for (int j = 0; j < children.size() - flag; j++) {
                        SystemClock.sleep(1500);
                        label = mDevice.findObject(By.text(title));
                        if (label != null) {
                            return label;
                        }
                        mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2, 0, mDevice
                                .getDisplayHeight() / 2, 20);

                    }
                } else {
                    for (int j = 0; j < flag; j++) {
                        SystemClock.sleep(1500);
                        label = mDevice.findObject(By.text(title));
                        if (label != null) {
                            return label;
                        }
                        mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2, mDevice
                                .getDisplayWidth(), mDevice.getDisplayHeight() / 2, 20);
                    }
                }
            }
        }
        return label;
    }

    public Object findIcon(String folder, String title) {
        openFolder(folder);
        mDevice.waitForIdle();
        UiObject2 label = mDevice.findObject(By.text(title));
        if (label == null) {
            UiScrollable icons = new UiScrollable(
                    new UiSelector().resourceId(String.format("%s:id/scroll_view", Launcher.PACKAGE)));
            UiObject icon = null;
            try {
                icon = icons.getChildByText(new UiSelector().className(TextView.class), title);
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
            return icon;
        }
        return label;
    }

    public void openIcon(String folder, String title, String packageName, boolean forceStop) {
        if (forceStop) {
            try {
                mDevice.executeShellCommand(String.format("am force-stop %s", packageName));
            } catch (IOException e) {
                // Nothing to do
            }
        }
        Object icon = folder != null ? findIcon(folder, title) : findIcon(title);
        assertThat(String.format("没有找到图标folder%s", title), icon, notNullValue());
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        if (!TextUtils.isEmpty(packageName)) {
            UiObject2 validate = mDevice.wait(Until.findObject(By.pkg(packageName).depth(0)), WAIT_TIME);
            assertThat(String.format("启动%s失败", title), validate, notNullValue());
        }
    }

    public Object openIcon(String folder, String title, String packageName) {
        boolean forceStop = false;
        if (forceStop) {
            try {
                mDevice.executeShellCommand(String.format("am force-stop %s", packageName));
            } catch (IOException e) {
                // Nothing to do
            }
        }
        Object icon = folder != null ? findIcon(folder, title) : findIcon(title);
        assertThat(String.format("没有找到图标%s", title), icon, notNullValue());
        return icon;
    }


    /**
     *
     * @param title 控件id或者content-desc
     * @param packageName 包名
     * @param flag 标记位 id(true) 或者 content-desc
     *
     * @return
     */
    public Object openPendant(String title, String packageName,boolean flag) {
        boolean forceStop = false;
        if (forceStop) {
            try {
                mDevice.executeShellCommand(String.format("am force-stop %s", packageName));
            } catch (IOException e) {
                // Nothing to do
            }
        }
        Object icon = findPendant(title,packageName,flag);
        assertThat(String.format("没有找到挂件%s", title), icon, notNullValue());
        return icon;
    }

    public Object findPendant(String title,String packageName ,boolean idFlag) {
        openLauncher();
        mDevice.waitForIdle();
        Object label ;
        if(idFlag){
            //控件id
            label = mDevice.findObject(By.res(packageName,title));
        }else{
            //content-desc
            label = mDevice.findObject(new UiSelector().descriptionContains(title));
        }
        if (label == null) {
            UiObject2 indicator = mDevice.findObject(By.res(Launcher.PACKAGE, "paged_view_indicator"));
            if (indicator != null) {
                int flag = 0;
                List<UiObject2> children = indicator.getChildren();
                for (int i = children.size() - 1; i > 0; i--) {
                    if (children.get(i).isChecked()) {
                        flag = i;
                        break;
                    }
                }
                if (flag <= children.size() / 2) {
                    for (int j = 0; j < children.size() - flag; j++) {
                        SystemClock.sleep(1500);
                        if(idFlag){
                            //控件id
                            label = mDevice.findObject(By.res(packageName,title));
                        }else{
                            //content-desc
                            label = mDevice.findObject(new UiSelector().descriptionContains(title));
                        }
                        if (label != null) {
                            return label;
                        }
                        mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2, 0, mDevice
                                .getDisplayHeight() / 2, 20);

                    }
                } else {
                    for (int j = 0; j < flag; j++) {
                        SystemClock.sleep(1500);
                        if(idFlag){
                            //控件id
                            label = mDevice.findObject(By.res(packageName,title));
                        }else{
                            //content-desc
                            label = mDevice.findObject(new UiSelector().descriptionContains(title));
                        }
                        if (label != null) {
                            return label;
                        }
                        mDevice.swipe(mDevice.getDisplayWidth() / 2, mDevice.getDisplayHeight() / 2, mDevice
                                .getDisplayWidth(), mDevice.getDisplayHeight() / 2, 20);
                    }
                }
            }
        }
        return label;
    }





    public void openIcon(String title, String packageName, boolean forceStop) {
        openIcon(null, title, packageName, forceStop);
    }

    public void openIcon(String title, String packageName) {
        openIcon(title, packageName, false);
    }

    public void openFolder(String title) {
        openIcon(title, null);
    }


    //打开同步英语初始化首页
    public void openSyncEnglish() {
        Object icon = openIcon("英语学习", "同步英语", SyncEnglish.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.waitForIdle();
        if (mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "change_book_layout_id")), WAIT_TIME * 1)) {
            UiObject2 changBook = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "change_book_layout_id"));
            if (changBook != null) {
                changBook.click();
                mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "add_id")), WAIT_TIME);
            }
        }
    }

    //打开同步英语首页 带刷新和添加首页
    public void openSyncEnglishMain() {
        openSyncEnglish();
        if (mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id")), WAIT_TIME)) {
            //带下拉环的菜单
            UiObject2 dropDown = mDevice.findObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id"));
            if (dropDown != null) {
                Rect rt = dropDown.getVisibleBounds();
                //点击下拉环
                longClick(rt.right - 35, rt.height() / 2);
                SystemClock.sleep(1000);
                //点击头像
                longClick(60, rt.height() / 2);
                SystemClock.sleep(2000);
                //更换课本
                longClick(640, 420);
                //点击趣味测试
                //longClick(rt.right-45,rt.height()/2);
                mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "add_id")), WAIT_TIME);
            }
        }
    }

    //打开同步英语课本内容首页
    public void openSyncEnglishBook() {
        openSyncEnglish();
        if (!mDevice.wait(Until.hasObject(By.res(SyncEnglish.PACKAGE, "toptoolbar_id")), WAIT_TIME * 1)) {
            mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
            UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
            List<UiObject2> children = booklist.getChildren();
            UiObject2 child = children.get(children.size() / 2);
            child.getChildren().get(0).clickAndWait(Until.newWindow(), WAIT_TIME);
        }
    }

    //打开同步数学首页 添加和刷新界面页
    public void openSynMathBook() {
        Object icon = openIcon("数学学习", "同步数学", SynMath.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.waitForIdle();
        if (!mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menu_back_btn")), WAIT_TIME)) {
            mDevice.wait(Until.hasObject(By.clazz(ListView.class)), WAIT_TIME);
            UiObject2 booklist = mDevice.findObject(By.clazz(ListView.class));
            List<UiObject2> children = booklist.getChildren();
            UiObject2 child = children.get(children.size() / 2);
            child.getChildren().get(1).clickAndWait(Until.newWindow(), WAIT_TIME);
            mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menu_back_btn")), WAIT_TIME);
        }
    }

    public void openSynMath() {
        Object icon = openIcon("数学学习", "同步数学", SynMath.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.waitForIdle();
        if (mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "menu_back_btn")), WAIT_TIME)) {
            UiObject2 changBook = mDevice.findObject(By.res(SynMath.PACKAGE, "menu_back_btn"));
            if (changBook != null) {
                changBook.click();
                mDevice.wait(Until.hasObject(By.res(SynMath.PACKAGE, "refreshBtnId")), WAIT_TIME);
                mDevice.waitForIdle();
            }
        }
    }

    public void openSynChinese() {
        Object icon = openIcon("语文学习", "同步语文", SynChinese.PACKAGE);
        if (icon instanceof UiObject2) {
            ((UiObject2) icon).clickAndWait(Until.newWindow(), WAIT_TIME);
        } else {
            try {
                ((UiObject) icon).clickAndWaitForNewWindow();
            } catch (UiObjectNotFoundException e) {
                // Nothing to do
            }
        }
        mDevice.waitForIdle();
    }

    public void openEnglishTalkBook() {
        openEnglishTalk();
    }


    public void openVtraining() {
        openIcon("名师辅导班", Vtraining.PACKAGE);
    }

    public void openVision() {
        openIcon("视力保护", Vision.PACKAGE);
    }

    public void openQuestionDatabse() {
        openIcon("好题精练", QuestionDatabase.PACKAGE);
    }

    public void openOnesearch() {
        openIcon("一键搜", OneSearchDark.PACKAGE);
    }

    public void openEnglishTalkContent() {
        UiObject2 addBook;
        openEnglishTalk();
        mDevice.wait(Until.hasObject(By.text("听说材料")), WAIT_TIME);
        UiObject2 talk = mDevice.findObject(By.text("听说材料"));
        talk.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "database_list_listview_id")), WAIT_TIME);
        UiObject2 listBook = mDevice.findObject(By.res(EnglishTalk.PACKAGE, "database_list_listview_id"));
        List<UiObject2> children = listBook.getChildren();
        addBook = children.get(0);
        addBook.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(EnglishTalk.PACKAGE, "detail_book_join_id")), WAIT_TIME * 4);
    }

    public void openEnglishTalk() {
        openIcon("英语听说", EnglishTalk.PACKAGE);
        mDevice.wait(Until.hasObject(By.text("学习")), WAIT_TIME);
        UiObject2 study = mDevice.findObject(By.text("学习"));
        study.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.waitForIdle();
    }


    public void openBbkMiddleMarket() {
        openIcon("应用商店", BbkMiddleMarket.PACKAGE);
    }
}
