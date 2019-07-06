package com.example.simple.model.cehua;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simple.R;
import com.example.simple.model.cehua.CehuaAdapter;
import com.example.simple.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CehuaListviewActivity extends BaseActivity {

    private ListView lsCehua;
    private CehuaAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cehua_listview;
    }

    @Override
    protected void initView() {
        lsCehua = queryViewById(R.id.ls_cehua);
        mAdapter = new CehuaAdapter(this, new ArrayList<String>());
        lsCehua.setAdapter(mAdapter);

        lsCehua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CehuaListviewActivity.this, "item click ", Toast.LENGTH_SHORT).show();
            }
        });
        lsCehua.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CehuaListviewActivity.this, "item long click ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        update();

    }

    private void update() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            datas.add("str-" + i);
        }

        mAdapter.update(datas);


    }


}
