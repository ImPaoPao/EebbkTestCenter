package com.eebbk.test.kit;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;

import org.json.JSONObject;

public class ServiceProxy {

    public void sendResult(JSONObject obj) {
        Bundle b = new Bundle();
        b.putString(Instrumentation.REPORT_KEY_STREAMRESULT, obj.toString());
        InstrumentationRegistry.getInstrumentation().sendStatus(0, b);
    }
}
