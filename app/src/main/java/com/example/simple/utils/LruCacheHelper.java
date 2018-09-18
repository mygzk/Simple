package com.example.simple.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by guozhk on 2018/9/17.
 */

public class LruCacheHelper {



    private void init(){



        int maxMemory = (int) (Runtime.getRuntime().totalMemory()/1024);
        int cacheSize = maxMemory/8;
        LruCache   mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }



}
