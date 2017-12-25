package com.abir.lazaar.ocrssactivity.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.abir.lazaar.ocrssactivity.R;
import com.abir.lazaar.ocrssactivity.adapter.NewsAdapter;
import com.abir.lazaar.ocrssactivity.model.XmlAsynckTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XmlAsynckTask taskOne = null;
    private XmlAsynckTask taskTwo = null;
    private XmlAsynckTask taskThree = null;
    private RecyclerView newsRecyclerView;
    private ProgressBar progressBar;
    public NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecyclerView=(RecyclerView)findViewById(R.id.list_news);
        progressBar=(ProgressBar)findViewById(R.id.progresBarr);

        adapter=new NewsAdapter(MainActivity.this);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(adapter);

        //set visibility of progress bar
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progressBar.setVisibility(View.GONE);
            }
        });

        //execute tasks
        loadData();
    }

    public void loadData()
    {

        taskOne = new XmlAsynckTask(adapter);
        taskOne.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://www.lemonde.fr/m-actu/rss_full.xml");
        taskTwo = new XmlAsynckTask(adapter);
        taskTwo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://www.lemonde.fr/afrique/rss_full.xml");
        taskThree = new XmlAsynckTask(adapter);
        taskThree.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://www.lemonde.fr/ameriques/rss_full.xml");
    }

    public void clearTasks()
    {
        taskOne.cancel(true);
        taskTwo.cancel(true);
        taskThree.cancel(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_reload) {
            adapter.deleteNewsList();
            loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}