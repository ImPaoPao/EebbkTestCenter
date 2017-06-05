package com.eebbk.test.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PackageResources {

    private Context mContext;

    public PackageResources(Context targetContext, String packageName) {
        try {
            mContext = targetContext.createPackageContext(packageName,
                    Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
        } catch (PackageManager.NameNotFoundException e) {
            // Nothing to do!
        }
    }

    /**
     * Return a resource identifier for the given resource name.
     *
     * @param type Optional default resource type to find. if "type/" is
     *             not included in the name.  Can be null to require an
     *             explicit type.
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not a valid resourceID.)
     */
    public int getIdentifier(String type, String name) {
        if (mContext != null) {
            return mContext.getResources().getIdentifier(name, type, mContext.getPackageName());
        }
        return 0;
    }

    /**
     * Return a string identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Returns 0 if no such
     *         resource was found.  (0 is not a valid resourceID.)
     */
    public int getStringIdentifier(String name) {
        return getIdentifier("string", name);
    }

    /**
     * Return a drawable identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *          resource was found.  (0 is not a valid resourceID.)
     */
    public int getDrawableIdentifier(String name) {
        return getIdentifier("drawable", name);
    }

    /**
     * Return a layout identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Returns 0 if no such
     *         resource was found.  (0 is not a valid resource ID.)
     */
    public int getLayoutIdentifier(String name) {
        return getIdentifier("layout", name);
    }

    /**
     * Return a id identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not a valid resourceID.)
     */
    public int getIdIdentifier(String name) {
        return getIdentifier("id", name);
    }

    /**
     * Return a xml identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not a valid resourceID.)
     */
    public int getXmlIdentifier(String name) {
        return getIdentifier("xml", name);
    }

    /**
     * Return a bool identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not valid resourceID.)
     */
    public int getBoolIdentifier(String name) {
        return getIdentifier("bool", name);
    }

    /**
     * Return a dimen identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not valid resourceID.)
     */
    public int getDimenIdentifier(String name) {
        return getIdentifier("dimen", name);
    }

    /**
     * Return a array identifier for the given name.
     *
     * @param name The name of the desired resource.
     * @return int The associated resource identifier.  Return 0 if no such
     *         resource was found.  (0 is not valid resourceID.)
     */
    public int getArrayIdentifier(String name) {
        return getIdentifier("array", name);
    }

    /**
     * Return a localized string from the application's package's default string table.
     *
     * @param name The name of the desired resource.
     * @return The string data associated with the resource, stripped of styled
     *         text information.
     */
    public String getString(String name) {
        String str = null;
        if (mContext != null) {
            int id = getStringIdentifier(name);
            if (id > 0) {
                str = mContext.getString(id);
            }
        }
        return str;
    }

    /**
     * Return a plurals string from the application's package
     * @param name The name of the desired resource.
     * @param quantity The number used to get the correct string for the current language's
     *        plural rules.
     * @return The string data associated with the resource.
     */
    public String getQuantityString(String name, int quantity) {
        String str = null;
        if (mContext != null) {
            int id = getIdentifier("plurals", name);
            if (id > 0) {
                str = mContext.getResources().getQuantityString(id, quantity, quantity);
            }
        }
        return str;
    }

    /**
     * Return a bitmap from the application's package's default drawable table.
     *
     * @param name The name of the desired resource.
     * @return The drawable data associated with the resource.
     */
    public Bitmap getBitmap(String name) {
        Bitmap bitmap = null;
        if (mContext != null) {
            int id = getDrawableIdentifier(name);
            if (id > 0) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), id);
            }
        }
        return bitmap;
    }

    /**
     * Return true if this layout contains the view from the given name.
     *
     * @param layoutId The layout identifier.
     * @param viewName The view name.
     * @param viewId The view identifier.
     * @return true if this layout contains the view.
     */
    public boolean isContainsView(int layoutId, String viewName, int viewId) {
        XmlPullParser parser = mContext.getResources().getXml(layoutId);
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG
                        && viewName.equals(parser.getName())) {
                    String id = parser.getAttributeValue(
                            "http://schemas.android.com/apk/res/android", "id");
                    if (id != null && id.contains(String.valueOf(viewId))) {
                        return true;
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Return true if this layout contains the view from the given class.
     *
     * @param layoutId The layout identifier.
     * @param clazz The view class.
     * @param viewId The view identifier.
     * @return true if this layout contains the view.
     */
    public boolean isContainsView(int layoutId, Class<? extends View> clazz, int viewId) {
        return isContainsView(layoutId, clazz.getSimpleName(), viewId);
    }
}
