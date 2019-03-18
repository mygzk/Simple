package com.org.orm.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.org.orm.demo.db.AppOrmLiteSqliteHelper;
import com.org.orm.demo.db.UserDbHelper;
import com.org.orm.demo.db.UserEntity;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG  = "DB_ACTICITY";


    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AppOrmLiteSqliteHelper.getInstance(this);

        UserDbHelper.getInstance(this);


        initView();
    }

    private void initView() {

        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);


        tvResult = findViewById(R.id.result);
    }

int i=0;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                i++;
                UserEntity userEntity = new UserEntity();
                userEntity.setName("test");
                userEntity.setId(i);

                UserDbHelper.getInstance(this).insterDate(userEntity);
                break;
            case R.id.del:
                //  UserDbHelper.getInstance(this).delData(userEntity);
                break;
            case R.id.update:
               // i++;
                UserEntity userEntity1 = new UserEntity();
                userEntity1.setName("test"+(i));
                UserDbHelper.getInstance(this).insterDate(userEntity1);
                break;
            case R.id.query:
                List<UserEntity> datas = UserDbHelper.getInstance(this).queryUser();
                if (datas != null && datas.size() > 0) {
                    for (UserEntity entity :datas){
                        Log.e(TAG,"entity:"+entity.toString());
                    }
                }
                break;
            default:
                break;
        }
    }


}
