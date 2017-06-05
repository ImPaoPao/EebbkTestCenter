package com.eebbk.test.common;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public class PackageConstants {

    public static final class DeskClock {
        public static final String PACKAGE = "com.eebbk.deskclock";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }


    public static final class Android {
        public static final String PACKAGE = "android";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Launcher {
        public static final String PACKAGE = "com.bbk.studyos.launcher";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class Personal {
        public static final String PACKAGE = "com.bbk.personal";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //学科同步
    public static final class SynStudy {
        public static final String PACKAGE = "com.eebbk.synstudy";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SynChinese {
        public static final String PACKAGE = "com.eebbk.synchinese";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //汉字学习
    public static final class HanziLearning {
        public static final String PACKAGE = "com.eebbk.hanziLearning.activity";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //描红词典
    public static final class EebbkDict {
        public static final String PACKAGE = "com.eebbk.dict";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SynMath {
        public static final String PACKAGE = "com.eebbk.synmath";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class MathAnimation {
        public static final String PACKAGE = "com.eebbk.mathanimation";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }


    // 一键搜 com.eebbk.onesearchdark
    public static final class OneSearchDark {
        public static final String PACKAGE = "com.eebbk.onesearchdark";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //名师辅导 com.eebbk.vtraining
    public static final class Vtraining {
        public static final String PACKAGE = "com.eebbk.vtraining";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //同步英文 com.eebbk.syncenglish
    public static final class SyncEnglish {
        public static final String PACKAGE = "com.eebbk.syncenglish";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //英语听说 com.eebbk.englishtalk
    public static final class EnglishTalk {
        public static final String PACKAGE = "com.eebbk.englishtalk";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //视力保护 com.eebbk.vision
    public static final class Vision {
        public static final String PACKAGE = "com.eebbk.vision";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    // 好题精练 com.eebbk.questiondatabase
    public static final class QuestionDatabase {
        public static final String PACKAGE = "com.eebbk.questiondatabase";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    //应用商店 com.eebbk.bbkmiddlemarket
    public static final class BbkMiddleMarket {
        public static final String PACKAGE = "com.eebbk.bbkmiddlemarket";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }

    public static final class SystemUi {

        public static final String PACKAGE = "com.android.systemui";
        public static final PackageResources R = new PackageResources(getTargetContext(), PACKAGE);
    }
}