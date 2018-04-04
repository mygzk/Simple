package com.example.simple.ui;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.simple.R;
import com.example.simple.evenBean.TestMessEven;
import com.example.simple.widget.MyFragLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends BaseActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lsView;
    List<MyBean> mDatas;
    MyAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        lsView = (ListView) findViewById(R.id.testlist);
        initDatas();
        adapter = new MyAdapter(this, mDatas);
        lsView.setAdapter(adapter);
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyBean bean = mDatas.get(position);
                Intent i = new Intent(MainActivity.this, bean.getaClass());
                MainActivity.this.startActivity(i);
            }
        });
        getD();

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(new MyBean("WheelActivity", WheelActivity.class));
        mDatas.add(new MyBean("LinkActivity", LinkActivity.class));
        mDatas.add(new MyBean("XuanfuActivity", XuanfuActivity.class));
        mDatas.add(new MyBean("ModifyStatusStytleActivity", ModifyStatusStytleActivity.class));
        mDatas.add(new MyBean("CheckPremissActivity", CheckPremissActivity.class));
        mDatas.add(new MyBean("PremissionTestActivity", PremissionTestActivity.class));
        mDatas.add(new MyBean("DialogActivity", DialogActivity.class));
        mDatas.add(new MyBean("DrawActivity", DrawActivity.class));
        mDatas.add(new MyBean("DispatchTouchTestActivity", DispatchTouchTestActivity.class));
        mDatas.add(new MyBean("ImgTestActivity", ImgTestActivity.class));
        mDatas.add(new MyBean("EvenBusTestActivity", EvenBusTestActivity.class));
        mDatas.add(new MyBean("PressDemoActivity", PressDemoActivity.class));
        mDatas.add(new MyBean("QuestbankActivity", QuestbankActivity.class));
        mDatas.add(new MyBean("ScrollTabActivity", ScrollTabActivity.class));

    }

    class MyAdapter extends BaseAdapter {
        Context context;
        List<MyBean> datas;

        public MyAdapter(Context context, List<MyBean> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HolderView holderView = null;
            if (convertView == null) {
                holderView = new HolderView();
                TextView tv = new TextView(context);
                tv.setGravity(Gravity.CENTER);
                int itemHeight = (int) (context.getResources().getDisplayMetrics().density * 46);
                AbsListView.LayoutParams lp =
                        new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
                tv.setLayoutParams(lp);
                holderView.tvClass = tv;
                convertView = tv;
                convertView.setTag(holderView);

            } else {
                holderView = (HolderView) convertView.getTag();
            }

            holderView.tvClass.setText(datas.get(position).getDecript() + "");
            return convertView;
        }
    }

    class MyBean {
        private String decript;
        private Class aClass;

        public MyBean(String decript, Class aClass) {
            this.decript = decript;
            this.aClass = aClass;
        }

        public String getDecript() {
            return decript;
        }

        public void setDecript(String decript) {
            this.decript = decript;
        }

        public Class getaClass() {
            return aClass;
        }

        public void setaClass(Class aClass) {
            this.aClass = aClass;
        }
    }

    class HolderView {
        TextView tvClass;

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "====onPause=====");
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "====onStop=====");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "====onDestroy=====");
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e(TAG, "====onRestoreInstanceState=====");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "====onSaveInstanceState 000=====");
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        Log.e(TAG, "====onSaveInstanceState 1111=====");
//        super.onSaveInstanceState(outState, outPersistentState);
//    }


    private void getD(){
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.e("main","metrics:"+metrics.toString());
    }

}
