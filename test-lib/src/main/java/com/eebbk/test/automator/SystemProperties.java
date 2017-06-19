package com.eebbk.test.automator;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Gives access to the system properties store. The system properties store
 * contains a list of string key-value pairs.
 * 
 */
public class SystemProperties {

    /**
     * Get the value for the given key.
     * @param key
     * @return an empty string if the key isn't found
     */
    public static String get(String key) {
        try {
            Process p = Runtime.getRuntime().exec("getprop " + key);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            return br.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Get the value for the given key.
     * @param key
     * @param def
     * @return if the key isn't found, return def if it isn't null, or an
     *         empty string otherwise
     */
    public static String get(String key, String def) {
        String val = get(key);
        return TextUtils.isEmpty(val) ? def : val;
    }

    /**
     * Get the value for the given key, and return as an integer.
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as an integer, or def if the key isn't found or
     *         cannot be parsed
     */
    public static int getInt(String key, int def) {
        String val = get(key);
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Get the value for the given key, and return as a long.
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a long, or def it the key isn't found or
     *         cannot be parsed
     */
    public static long getLong(String key, long def) {
        String val = get(key);
        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Get the value for the given key, and return as a boolean.
     * Values 'n', 'no', '0', 'false' or 'off' are considered false.
     * Values 'y', 'yes', '1', 'true' or 'on' are considered true.
     * (case sensitive).
     * If the key does not exist, or has any other value, then the default
     * result is returned.
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a boolean, or def is the key isn't found or is
     *         not able to be parsed as a boolean.
     */
    public static boolean getBoolean(String key, boolean def) {
        String val = get(key);
        if ("n".equals(val) || "no".equals(val) || "0".equals(val)
                || "false".equals(val) || "off".equals(val)) {
            return false;
        } else if ("y".equals(val) || "yes".equals(val) || "1".equals(val)
                || "true".equals(val) || "on".equals(val)) {
            return true;
        }
        return def;
    }

    /**
     * Set the value for the given key.
     * @param key
     * @param val
     */
    public static void set(String key, String val) {
        try {
            Process p = Runtime.getRuntime().exec(
                    String.format("setprop %s %s", key, val));
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
