package com.artem.testtask.api;

import android.util.Log;

import com.artem.testtask.utils.Vars;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class MyWebsocket {

    public static void getUrlForDownloadData(final WebAPI.ResponseListener responseListener){
        String uri = Vars.Server.PROTOCOL_WS + Vars.Server.WS_HOST + ":" + Vars.Server.PORT;
        AsyncHttpClient.getDefaultInstance().websocket(uri, Vars.Server.PROTOCOL_WS, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                if (ex != null) {
                    ex.printStackTrace();
                    responseListener.onFailed(ex, ex.getMessage());
                    return;
                }
                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    public void onStringAvailable(String s) {
                        Log.e("onStringAvailable", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Log.e("url", jsonObject.getString(Vars.Server.URL));
                            responseListener.onSuccess(jsonObject.getString(Vars.Server.URL));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
