package com.artem.testtask.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.artem.testtask.R;
import com.artem.testtask.model.Data;
import com.artem.testtask.utils.Vars;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailDataActivity extends AppCompatActivity {
    @Bind(R.id.textViewId) TextView textViewId;
    @Bind(R.id.textViewTime) TextView textViewTime;
    @Bind(R.id.textViewName) TextView textViewName;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_data_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        if(intent.hasExtra(Vars.Extra.EXTRA_DATA)){
            Data data = intent.getParcelableExtra(Vars.Extra.EXTRA_DATA);
            setData(data);
        }
    }

    private void setData(Data data){
        setTitle(data.getName());
        textViewId.setText(Long.toString(data.getId()));
        textViewName.setText(data.getName());
        textViewTime.setText(Long.toString(data.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_data, menu);
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
}
