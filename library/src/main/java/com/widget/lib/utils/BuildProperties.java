package com.widget.lib.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BuildProperties {

    private final Properties mProperties;

    private BuildProperties() throws IOException {
        mProperties = new Properties();
        FileInputStream fis = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
        mProperties.load(fis);
        fis.close();
    }

    public boolean containsKey(final Object key) {
        return mProperties.containsKey(key);
    }

    public boolean containsValue(final Object value) {
        return mProperties.containsValue(value);
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return mProperties.entrySet();
    }

    public String getProperty(final String name) {
        return mProperties.getProperty(name);
    }

    public String getProperty(final String name, final String defaultValue) {
        return mProperties.getProperty(name, defaultValue);
    }

    public boolean isEmpty() {
        return mProperties.isEmpty();
    }

    public Enumeration<Object> keys() {
        return mProperties.keys();
    }

    public Set<Object> keySet() {
        return mProperties.keySet();
    }

    public int size() {
        return mProperties.size();
    }

    public Collection<Object> values() {
        return mProperties.values();
    }

    public static BuildProperties newInstance() throws IOException {
        return new BuildProperties();
    }
}