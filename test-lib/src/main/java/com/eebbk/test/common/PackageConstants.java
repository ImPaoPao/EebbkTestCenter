package com.eebbk.test.common;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public class PackageConstants {

    public static final class Android {
        public static final String PACKAGE = "android";
        public static final String LABEL = "android";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Launcher {
        public static final String PACKAGE = "com.bbk.studyos.launcher";
        public static final String LABEL = "桌面";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class AndroidLauncher {
        public static final String PACKAGE = "com.android.launcher";
        public static final String LABEL = "原生Launcher";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }


    public static final class Personal {
        public static final String PACKAGE = "com.bbk.personal";
        public static final String LABEL = "个人中心";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SynStudy {
        public static final String PACKAGE = "com.eebbk.synstudy";
        public static final String LABEL = "学科同步";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SynChinese {
        public static final String PACKAGE = "com.eebbk.synchinese";
        public static final String LABEL = "同步语文";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //语文学习
    public static final class HanziLearning {
        public static final String PACKAGE = "com.eebbk.hanziLearning.activity";
        public static final String LABEL = "快乐学汉字";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

//    public static final class ChaziCi {
//        public static final String PACKAGE = "com.eebbk.dict";
//        public static final String LABEL = "查字典";
//        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
//    }

    public static final class EebbkDict {
        public static final String PACKAGE = "com.eebbk.dict";
        public static final String LABEL = "描红词典";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SynMath {
        public static final String PACKAGE = "com.eebbk.synmath";
        public static final String LABEL = "同步数学";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class MathAnimation {
        public static final String PACKAGE = "com.eebbk.mathanimation";
        public static final String LABEL = "快乐数学动画";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class OneSearchDark {
        public static final String PACKAGE = "com.eebbk.onesearchdark";
        public static final String LABEL = "一键搜";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Vtraining {
        public static final String PACKAGE = "com.eebbk.vtraining";
        public static final String LABEL = "名师辅导班";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //同步英文 com.eebbk.syncenglish
    public static final class SyncEnglish {
        public static final String PACKAGE = "com.eebbk.syncenglish";
        public static final String LABEL = "同步英语";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class EnglishTalk {
        public static final String PACKAGE = "com.eebbk.englishtalk";
        public static final String LABEL = "英语听说";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //视力保护 com.eebbk.vision
    public static final class Vision {
        public static final String PACKAGE = "com.eebbk.vision";
        public static final String LABEL = "视力保护";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class QuestionDatabase {
        public static final String PACKAGE = "com.eebbk.questiondatabase";
        public static final String LABEL = "好题精练";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkMiddleMarket {
        public static final String PACKAGE = "com.eebbk.bbkmiddlemarket";
        public static final String LABEL = "应用商店";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkTranslation {
        public static final String PACKAGE = "com.eebbk.translation";
        public static final String LABEL = "整句翻译";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Feedback {
        public static final String PACKAGE = "com.bbk.feedback_h10";
        public static final String LABEL = "问题反馈";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkNewword {
        public static final String PACKAGE = "com.eebbk.newword";
        public static final String LABEL = "生词库";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkWallpaper {
        public static final String PACKAGE = "com.eebbk.studyos.wallpaper";
        public static final String LABEL = "随心壁纸";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Primarylisten {
        public static final String PACKAGE = "com.bbk.primarylisten";
        public static final String LABEL = "早晚听";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Playmanual {
        public static final String PACKAGE = "com.eebbk.playmanual";
        public static final String LABEL = "用户手册";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Servicecenter {
        public static final String PACKAGE = "com.eebbk.servicecenter";
        public static final String LABEL = "服务中心";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Recorder {
        public static final String PACKAGE = "com.bbk.recorder";
        public static final String LABEL = "录音";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class TestKit {
        public static final String PACKAGE = "com.eebbk.test.kit";
        public static final String LABEL = "test-kit";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BBKUserSevice {
        public static final String PACKAGE = "com.eebbk.bfc.app.bfcbehavior";
        public static final String LABEL = "BBKUserSevice";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkSafe {
        public static final String PACKAGE = "com.eebbk.studyos.bbksafe";
        public static final String LABEL = "应用广告过滤";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Stresstest {
        public static final String PACKAGE = "com.eebbk.stresstest";
        public static final String LABEL = "Stresstest";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Deskclock {
        public static final String PACKAGE = "com.eebbk.deskclock";
        public static final String LABEL = "闹钟";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class UScannerService {
        public static final String PACKAGE = "com.eebbk.uscanner";
        public static final String LABEL = "UScannerService";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Electronicalbum {
        public static final String PACKAGE = "com.eebbk.electronicalbum.activity";
        public static final String LABEL = "我的相册";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Interestingapplications {
        public static final String PACKAGE = "com.eebbk.interestingapplications_junior";
        public static final String LABEL = "趣味应用题";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Onekeycleaner {
        public static final String PACKAGE = "com.bbk.studyos.onekeycleaner";
        public static final String LABEL = "一键清理";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Otaupdate {
        public static final String PACKAGE = "com.eebbk.tools.otaupdate";
        public static final String LABEL = "系统升级";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Calendar {
        public static final String PACKAGE = "com.eebbk.calendar";
        public static final String LABEL = "日历";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class PadStudyOsTheme {
        public static final String PACKAGE = "com.eebbk.padstudyostheme";
        public static final String LABEL = "PadStudyOsTheme";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Relaxedtheme {
        public static final String PACKAGE = "com.eebbk.relaxedtheme";
        public static final String LABEL = "轻松作文";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Ancientprose {
        public static final String PACKAGE = "com.eebbk.ancientprose";
        public static final String LABEL = "古文观止";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkPackageinstaller {
        public static final String PACKAGE = "com.eebbk.bbkpackageinstaller";
        public static final String LABEL = "软件包安装程序";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Browser {
        public static final String PACKAGE = "com.eebbk.browser";
        public static final String LABEL = "浏览器";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Onesearch {
        public static final String PACKAGE = "com.eebbk.onesearch";
        public static final String LABEL = "扫条码";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Maindoexercise {
        public static final String PACKAGE = "com.eebbk.doexercise.view.maindoexercise";
        public static final String LABEL = "速算练习";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Fileexplorer {
        public static final String PACKAGE = "com.bbk.fileexplorer";
        public static final String LABEL = "资源管理";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Timetable {
        public static final String PACKAGE = "com.bbk.timetable";
        public static final String LABEL = "课程表";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Recitewords {
        public static final String PACKAGE = "com.eebbk.recitewords";
        public static final String LABEL = "背单词";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Kittest {
        public static final String PACKAGE = "com.eebbk.translation";
        public static final String LABEL = "com.eebbk.test.kit.test";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Parentsupport {
        public static final String PACKAGE = "com.eebbk.parentsupport";
        public static final String LABEL = "家长管理";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Videoplayer {
        public static final String PACKAGE = "com.eebbk.videoplayer";
        public static final String LABEL = "影视欣赏";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class AbcSysLog {
        public static final String PACKAGE = "com.bbk.abcsyslog";
        public static final String LABEL = "AbcSysLog";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class BbkMusicplayer {
        public static final String PACKAGE = "com.eebbk.musicplayer";
        public static final String LABEL = "音乐";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SystemUi {
        public static final String PACKAGE = "com.android.systemui";
        public static final String LABEL = "原生SystemUI";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }
}