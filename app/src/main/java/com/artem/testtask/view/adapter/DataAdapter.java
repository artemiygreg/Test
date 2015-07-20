package com.artem.testtask.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artem.testtask.R;
import com.artem.testtask.model.Data;
import com.artem.testtask.service.date.DateService;
import com.artem.testtask.service.image.ImageService;

import java.util.List;

/**
 * Created by artem_mobile_dev on 19.07.2015.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<Data> listData;
    private Activity activity;

    public DataAdapter(List<Data> listData, Activity activity) {
            this.listData = listData;
            this.activity = activity;
        }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public View view;
    public TextView textViewName, textViewTime;
    public ImageView imageViewLogo;

    public ViewHolder(View v) {
        super(v);
        view = v;
        textViewName = (TextView)v.findViewById(R.id.textViewName);
        textViewTime = (TextView)v.findViewById(R.id.textViewTime);
        imageViewLogo = (ImageView)v.findViewById(R.id.imageViewLogo);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener != null) {
            onItemClickListener.onItemClick(v, getPosition(), listData.get(getPosition()));
        }
    }
}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_data, parent, false);
        return new ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Data data = listData.get(position);

        holder.textViewName.setText(data.getName());
        holder.textViewTime.setText(DateService.convertTimestampToString(data.getTime() * 1000, DateService.DATE_AND_TIME_FORMAT_RU));

        ImageService.setImage(activity, holder.imageViewLogo, data.getUrl());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position, Object itemObject);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}