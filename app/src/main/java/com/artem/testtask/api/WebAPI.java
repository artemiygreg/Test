package com.artem.testtask.api;

import android.net.Uri;
import android.util.Log;

import com.artem.testtask.json.JsonHelper;
import com.artem.testtask.model.Data;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpResponse;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class WebAPI {

    public interface ResponseListener {
        void onSuccess(String result);
        void onFailed(Exception ex, String message);
    }
    public interface ListCallback {
        void get(List<Data> listData);
        void onError(String message);
    }

    public void getDataByUrl(final ListCallback callback){
        MyWebsocket.getUrlForDownloadData(new ResponseListener() {
            @Override
            public void onSuccess(String result) {
                AsyncHttpClient.getDefaultInstance().executeJSONArray(
                                                    new AsyncHttpRequest(Uri.parse(result), "GET"),
                                                    new AsyncHttpClient.JSONArrayCallback() {
                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse source, JSONArray result) {
                        if (e != null) {
                            e.printStackTrace();
                            return;
                        }
                        Log.e("onCompleted", result.toString());
                        callback.get(JsonHelper.makeListFromJson(result.toString()));
                    }
                });
            }

            @Override
            public void onFailed(Exception ex, String message) {
                callback.onError(message);
            }
        });
    }

}
