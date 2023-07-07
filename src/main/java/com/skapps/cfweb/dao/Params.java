package com.skapps.cfweb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Params {
    Map<String, Object> paramMap = new HashMap<>();

    public void addParam(String key, Object value) {
        paramMap.put(key, value);
    }

    public List<String> getParamKeys() {
        return paramMap.keySet().stream().toList();
    }

    public Object getValue(String key) {
        return paramMap.get(key);
    }
}
