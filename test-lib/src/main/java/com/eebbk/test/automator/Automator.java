package com.eebbk.test.automator;

import android.app.UiAutomation;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;

import java.util.regex.Pattern;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class Automator {

    public static final String BUTTON1 = "button1";
    public static final Pattern PATTERN_BUTTON1 = Pattern.compile(String.format("^.*%s$", BUTTON1));
    public static final int WAIT_TIME = 5000;

    protected UiDevice mDevice;
    protected UiAutomation mAutomation;
    protected AutomatorHelper mHelper;

    @Before
    public void setUp() throws Exception {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        mAutomation = getInstrumentation().getUiAutomation();

        // Initialize AutomatorHelper instance
        mHelper = new AutomatorHelper(mDevice);

        // Start from the home screen
        mHelper.unlock();
        mHelper.openLauncher();
    }
}
