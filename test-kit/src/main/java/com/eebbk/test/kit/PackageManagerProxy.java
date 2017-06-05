package com.eebbk.test.kit;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;


@RunWith(AndroidJUnit4.class)
public class PackageManagerProxy extends ServiceProxy {

    private PackageManager mManager;

    @Before
    public void setUp() {
        mManager = InstrumentationRegistry.getTargetContext().getPackageManager();
    }

    @Test
    public void getPackageList() throws JSONException {
        String[] categories = {Intent.CATEGORY_LAUNCHER, Intent.CATEGORY_HOME,
                Intent.CATEGORY_MONKEY};
        JSONObject jobj = new JSONObject();

        for (PackageInfo pi : mManager.getInstalledPackages(0)) {
            JSONObject app = new JSONObject();
            app.put("versionCode", pi.versionCode);
            app.put("versionName", pi.versionName);
            app.put("label", pi.applicationInfo.loadLabel(mManager));
            app.put("activities", new JSONArray());
            app.put("system", (pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM));
            jobj.put(pi.packageName, app);

            for (String category : categories) {
                Intent intent = new Intent(Intent.ACTION_MAIN).addCategory(
                        category).setPackage(pi.packageName);
                List<ResolveInfo> ris = mManager.queryIntentActivities(intent, 0);

                for (ResolveInfo ri : ris) {
                    JSONObject activity = new JSONObject();
                    activity.put("label", ri.loadLabel(mManager).toString());
                    activity.put("name", ri.activityInfo.name);
                    activity.put("category", category);
                    if (jobj.has(pi.packageName)) {
                        jobj.optJSONObject(pi.packageName).getJSONArray("activities").put(activity);
                    }
                }
            }
        }

        sendResult(jobj);
    }
}
