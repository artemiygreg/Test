package com.artem.testtask.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.artem.testtask.R;
import com.artem.testtask.api.WebAPI;
import com.artem.testtask.model.Data;
import com.artem.testtask.utils.Vars;
import com.artem.testtask.view.DividerItemDecoration;
import com.artem.testtask.view.adapter.DataAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.textViewNotData) TextView textViewNotData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, R.color.black);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        WebAPI webAPI = new WebAPI();
        webAPI.getDataByUrl(new WebAPI.ListCallback() {
            @Override
            public void get(final List<Data> listData) {
                setData(listData);
            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }

    private void setData(final List<Data> listData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (listData != null) {
                    DataAdapter adapter = new DataAdapter(listData, MainActivity.this);
                    adapter.setOnItemClickListener(MainActivity.this);
                    recyclerView.setVisibility(View.VISIBLE);
                    textViewNotData.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                } else {
                    textViewNotData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showToast(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position, Object itemObject) {
        Intent detailIntent = new Intent(this, DetailDataActivity.class);
        detailIntent.putExtra(Vars.Extra.EXTRA_DATA, (Data)itemObject);
        startActivity(detailIntent);
    }
}
