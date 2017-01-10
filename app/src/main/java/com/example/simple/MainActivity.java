package com.example.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    private String str= "{\"caseImg\":\"http:\\/\\/220.249.20.134:2828\\/fileupload\\/2017010411002370211.jpg\",\"userId\":46}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bean bean =GsonUtils.fromJson(str,new TypeToken<Bean>(){});
                Log.e("test","url:"+bean.getCaseImg());
            }
        });


    }
}
