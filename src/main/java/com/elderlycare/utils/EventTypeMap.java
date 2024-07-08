package com.elderlycare.utils;

import java.util.HashMap;
import java.util.Map;

public class EventTypeMap {
    public static final Map<String, String> EVENT_TYPE_MAP = new HashMap<>();

    static {
        EVENT_TYPE_MAP.put("1", "有人闯入禁区");
        EVENT_TYPE_MAP.put("2", "陌生人出现");
        EVENT_TYPE_MAP.put("3", "老人笑了");
        EVENT_TYPE_MAP.put("4", "义工与老人交互");
        EVENT_TYPE_MAP.put("5", "摔倒");
    }
}