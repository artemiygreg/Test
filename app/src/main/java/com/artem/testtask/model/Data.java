package com.artem.testtask.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class Data implements Parcelable {
    private long id;
    private String name;
    private String url;
    private long time;


    public Data(){

    }

    public Data(long id, String name, String url, long time){
        this.id = id;
        this.name = name;
        this.url = url;
        this.time = time;
    }

    private Data(Parcel source) {
        id = source.readLong();
        name = source.readString();
        url = source.readString();
        time = source.readLong();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeLong(time);
    }
    public static final Creator CREATOR = new Creator() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[0];
        }
    };
}
