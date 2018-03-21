package com.wjb.utils;

public class KVEntity {
    public String key;
    public Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public KVEntity(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
