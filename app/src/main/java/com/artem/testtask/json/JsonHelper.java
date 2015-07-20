package com.artem.testtask.json;

import com.artem.testtask.model.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class JsonHelper {

    public static List<Data> makeListFromJson(String json){
        List<Data> listData = new ArrayList<>();
        try {
            Type type = new TypeToken<List<Data>>(){}.getType();
            Gson gson = new Gson();
            listData = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
}
