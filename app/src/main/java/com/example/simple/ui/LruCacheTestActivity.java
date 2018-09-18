package com.example.simple.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.simple.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheTestActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_lru_cache_test;
    }

    @Override
    protected void initView() {
        queryViewById(R.id.lru_test1,true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.lru_test1:
                testLinkHashMap();
                break;

        }
    }

    private void testLinkHashMap(){


       // DiskLruCache
        LinkedHashMap<Integer, Integer> map =
                new LinkedHashMap<>(0, 0.75f, false);

       // map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.get(1);
        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }


    }


    private void asnTask(){

      new   AsyncTask<String,Integer,String> (){

          @Override
          protected String doInBackground(String... strings) {
              return null;
          }
      }.execute();
    }





}
