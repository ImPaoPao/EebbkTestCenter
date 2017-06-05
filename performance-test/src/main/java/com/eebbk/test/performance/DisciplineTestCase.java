package com.eebbk.test.performance;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import com.eebbk.test.common.PackageConstants;
import com.eebbk.test.common.PackageConstants.Launcher;
import com.eebbk.test.common.PackageConstants.Personal;
import com.eebbk.test.common.PackageConstants.SynStudy;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class DisciplineTestCase extends PerforTestCase {

    //切换
    public void setUp() throws Exception {
        super.setUp();
        mDevice.waitForIdle();
        UiObject2 user = mDevice.findObject(By.res(Launcher.PACKAGE, "personal_grade"));
        if (!user.getText().contains("高中")) {
            user = mDevice.findObject(By.res(Launcher.PACKAGE, "personal_head_layout"));
            user.clickAndWait(Until.newWindow(), WAIT_TIME);
            if (mDevice.wait(Until.hasObject(By.res(Personal.PACKAGE, "checkbox")), WAIT_TIME)) {
                user = mDevice.findObject(By.res(Personal.PACKAGE, "checkbox"));
                user.click();
//                user = mDevice.findObject(By.res(Personal.PACKAGE, "btn_ok"));
//                user.clickAndWait(Until.newWindow(), WAIT_TIME);
                mDevice.pressBack();//点击不再提示后,返回键即可回到信息编辑页面
                mDevice.waitForIdle();
                //com.bbk.personal:id/btn_ok 登录激活
                //com.bbk.personal:id/checkbox 不再提示
            }
            //com.bbk.personal:id/beta_edt_current_main 编辑信息
            //com.bbk.personal:id/user_grade_editabl 年级信息编辑
            //年级列表切换 com.bbk.personal:id/pop_add_grade_list 滚动 查找高中年级
            //保存信息 com.bbk.personal:id/save_person_btn
            //回到桌面 等待加载完即为学科同步页面。
        }
    }

    //点击添加按钮→下载界面加载完成
    @Test
    public void addDiscipline() throws IOException, JSONException {
        openSynStudy();
        //com.eebbk.synstudy:id/book_searchbook 有书本的时候 刷新
        //com.eebbk.synstudy:id/book_add 添加
        //com.eebbk.synstudy:id/book_del 删除
        //书本列表 android.widget.ListView -> LinearLayout->View
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
        UiObject2 add = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_add"));
        add.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_list")), WAIT_TIME * 4);
        UiObject2 bookList = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_list"));
        Rect rt = bookList.getVisibleBounds();
        mDevice.waitForIdle();
        SystemClock.sleep(20000);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        Rect loadPngRect = new Rect(0, 0, source_png.getWidth(), 80);
        //界面刷新出来
        Rect refreshPngRect = new Rect(rt.left, rt.top, rt.right, rt.bottom);
        SystemClock.sleep(1000);
        mDevice.pressBack();
        mDevice.waitForIdle();
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_add")), WAIT_TIME);
            add = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_add"));
            startTestRecord();
            add.click();
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, refreshPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            SystemClock.sleep(1000);
            mDevice.pressBack();
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    //点击书本→书本目录加载完成
    @Test
    public void showDisciplineContent() throws FileNotFoundException, JSONException {
        openSynStudy();
    }

    //点击教辅目录→进入课本详情
    @Test
    public void showDisciplineDetails() {
    }

    //教材内容界面，点击右下角目录按钮→目录加载完成
    @Test
    public void showDisciplineContentDetails() {
    }

    //教材全解界面，点击右下角目录中教材内容全解→页面加载完成
    @Test
    public void showDisciplineContentOfTeaching() {
    }

    //教材全解界面，点击典型例题→页面加载完成
    @Test
    public void showDisciplineTypical() {
    }

    //书架界面10本书，点击刷新→刷新完成
    @Test
    public void refreshDiscipline() throws JSONException, FileNotFoundException {
        openSynStudy();
        mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_searchbook")), WAIT_TIME);
        Bitmap source_png = mHelper.takeScreenshot(mNumber);
        //中间刷新的那个黑色的带彩色点的小方块
        Rect loadPngRect = new Rect(source_png.getWidth() / 2 - 40, source_png.getHeight() / 2 - 40,
                source_png.getWidth() / 2 + 40, source_png.getHeight() / 2 + 40);
        SystemClock.sleep(1000);
        for (int i = 0; i < mCount; i++) {
            mDevice.wait(Until.hasObject(By.res(SynStudy.PACKAGE, "book_searchbook")), WAIT_TIME);
            UiObject2 refresh = mDevice.findObject(By.res(SynStudy.PACKAGE, "book_searchbook"));
            startTestRecord();
            refresh.click();
            SystemClock.sleep(200);
            Map<String, String> compareResult = doCompare(source_png, loadPngRect, new Date());
            stopTestRecord(compareResult.get("loadTime"), compareResult.get("refreshTime"), compareResult.get
                    ("loadResult"), compareResult.get("refreshResult"));
            mDevice.waitForIdle();
            SystemClock.sleep(2000);
        }
        if (!source_png.isRecycled()) {
            source_png.recycle();
        }
    }

    private void openSynStudy() {
        mDevice.wait(Until.hasObject(By.res(PackageConstants.SynStudy.PACKAGE, "syn_widget_new_chinese")), WAIT_TIME);
        UiObject2 synStudy = mDevice.findObject(By.res(PackageConstants.SynStudy.PACKAGE, "syn_widget_new_chinese"));
        synStudy.clickAndWait(Until.newWindow(), WAIT_TIME);
        mDevice.waitForIdle();
    }
}
