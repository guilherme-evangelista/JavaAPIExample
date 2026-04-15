package com.dummyjson.core.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    private static final int INDENT_FACTORY = 4;
    private static final Gson GSON = new Gson();

    public static String getJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T getDTO(Class<T> clazz, String json) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> T getDTO(Type type, String json) {
        return GSON.fromJson(json, type);
    }

    public static String prettyPrint(List<?> jsonObjectList) {
        ListUtils.requireNonEmpty(jsonObjectList, "Invalid jsonObject list: null or empty");
        return new JSONArray(jsonObjectList).toString(INDENT_FACTORY);
    }

}
