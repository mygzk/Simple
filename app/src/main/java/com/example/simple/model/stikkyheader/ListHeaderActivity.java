package com.example.simple.model.stikkyheader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple.R;
import com.example.simple.model.stikkyheader.view.ExtendMsgHeader;
import com.example.simple.model.stikkyheader.view.IPullHeaderOpenListener;
import com.example.simple.model.stikkyheader.view.PullExtendLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListHeaderActivity extends AppCompatActivity {

    private String TAG = ListHeaderActivity.class.getSimpleName();
    private ListView lsView;
    private TextView tvHeader;

    LinearLayout belowView;
    PullExtendLayout mPullExtendLayout;
    ExtendMsgHeader mPullNewHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_header);


        initView();
    }

    private void initView() {

        lsView = findViewById(R.id.list_test1);
        tvHeader = findViewById(R.id.header_tv);

        belowView = findViewById(R.id.below_view);
        mPullExtendLayout = findViewById(R.id.pull_extend);
        mPullNewHeader = findViewById(R.id.extend_header);

        mPullExtendLayout.setPullHeaderOpenListener(new IPullHeaderOpenListener() {
            @Override
            public void open() {
                Toast.makeText(ListHeaderActivity.this,"header open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void close() {
                lsView.setSelection(1);
                Toast.makeText(ListHeaderActivity.this,"header close",Toast.LENGTH_SHORT).show();
            }
        });

        PullLayoutUtil pullLayoutUtil =new PullLayoutUtil(mPullExtendLayout,belowView,mPullNewHeader);


        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        int itemHeight = (int) (this.getResources().getDisplayMetrics().density * 46);
        AbsListView.LayoutParams lp =
                new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
        tv.setLayoutParams(lp);
        tv.setBackgroundColor(getResources().getColor(R.color.comm_title));
      //  tv.setText("header  list view");
        tv.setText("header view");
        tv.setBackgroundColor(getResources().getColor(R.color.color_D9E4EE));

        lsView.addHeaderView(tv);


        MyTestAdapter adapter = new MyTestAdapter(this,getDatas());
        lsView.setAdapter(adapter);
        lsView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e(TAG,"onScrollStateChanged scrollState:"+scrollState);
//                if(scrollState==SCROLL_STATE_FLING){
//                   int pos= view.getFirstVisiblePosition();
//                    int count= view.getChildCount();
//                    Log.e(TAG,"onScrollStateChanged pos:"+pos);
//                    Log.e(TAG,"onScrollStateChanged count:"+count);
//                   if(pos==0){
//                       if(count>3){
//                           lsView.setSelection(0);
//                       }
//
//                   }
//
//                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e(TAG,"onScroll firstVisibleItem:"+firstVisibleItem);
                Log.e(TAG,"onScroll visibleItemCount:"+visibleItemCount);
                if (firstVisibleItem >= 1) {
                    tvHeader.setVisibility(View.VISIBLE);

                } else {

                    tvHeader.setVisibility(View.GONE);
                }
            }
        });

        lsView.setSelection(1);


    }


    private List<String> getDatas(){
        List<String> datas=new ArrayList<>();
        for (int i=0;i<35;i++){
            datas.add("aa-"+i);

        }

        return datas;
    }



    class MyTestAdapter extends BaseAdapter{

        Context context;
        List<String> datas;

        public MyTestAdapter(Context context, List<String> datas) {
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

            holderView.tvClass.setText(datas.get(position));
            return convertView;
        }
    }


    class HolderView {
        TextView tvClass;

    }

}
