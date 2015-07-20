package com.artem.testtask.service.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class ImageService {

    public static void setImage(Context context, ImageView imageView, String url){
        Glide.with(context).load(url).centerCrop().crossFade().into(imageView);
    }
}
