package com.example.simple.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.simple.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk
 * create time on 2017/12/22.
 */

public class TopicLayout extends LinearLayout {
    //单选
    public final static int TOPIC_TY_ONLY = 0;
    //多选
    public final static int TOPIC_TY_MORE = 1;
    private int mTopicType = TOPIC_TY_ONLY;

    //存放radioButton
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private List<CheckBox> checkBoxArrayList = new ArrayList<>();


    private OnResultCallback onResultCallback;

    //已选中的选项
//    private List<Integer> selected = new ArrayList<>();
    Map<Integer, Boolean> selectedMap = new HashMap<>();
    //选项内容
    private List<String> mOptionData;
    //题目
    private String mTitle;

    public TopicLayout(@NonNull Context context) {
        this(context, null);
    }

    public TopicLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopicLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setPadding(10, 0, 10, 0);
        initView();
    }

    public void setData(String title, List<String> data, int mTopicType) {
        this.mTitle = title;
        this.mOptionData = data;
        this.mTopicType = mTopicType;
        initView();
    }

    public TopicLayout setSelected(Map<Integer, Boolean> sel) {
        this.selectedMap = sel;
        return this;
    }

    private void initView() {
        removeAllViews();
        // TODO: 2017/12/22
//        if (mOptionData == null) {
//            return;
//        }
        //题目
        TextView topTitle = new TextView(getContext());
        topTitle.setTextColor(getResources().getColor(R.color.front_3));
        topTitle.setTextSize(16.0f);
        topTitle.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        topTitle.setMinHeight(64);
        topTitle.setText("1.只有缺失的机会没有获利的风险称为系统性风险？");
        addView(topTitle);
        //创建选项
        initItem(mTopicType);
    }


    private void initItem(int type) {
        radioButtonList.clear();
        checkBoxArrayList.clear();
        //for (int i = 0; i < mOptionData.size(); i++) {
        for (int i = 0; i < 4; i++) {
            View item = LayoutInflater.from(getContext()).inflate(R.layout.check_item_layout, null);
            CheckBox checkBox = (CheckBox) item.findViewById(R.id.check_item_cb);
            RadioButton radioButton = (RadioButton) item.findViewById(R.id.check_item_rb);
            TextView textView = (TextView) item.findViewById(R.id.check_item_tv);
            final int finalI = i;
            if (type == TOPIC_TY_ONLY) {
                radioButton.setVisibility(VISIBLE);
                checkBox.setVisibility(GONE);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        selectOption(finalI, isChecked);
                    }
                });
                radioButtonList.add(radioButton);
            } else {
                radioButton.setVisibility(GONE);
                checkBox.setVisibility(VISIBLE);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        selectOption(finalI, isChecked);
                    }
                });
                checkBoxArrayList.add(checkBox);
            }
            textView.setText("测试 侧四  测试 侧四 测试 侧四  ");
            addView(item);
        }

        //初始化选中答案
        if (this.selectedMap != null && selectedMap.size() > 0) {
            if (type == TOPIC_TY_ONLY) {
                for (int s : selectedMap.keySet()) {
                    boolean seleced = selectedMap.get(s);
                    if (seleced) {
                        selectOption(s, seleced);
                        break;
                    }
                }
            } else {
                for (int s : selectedMap.keySet()) {
                    checkBoxArrayList.get(s).setChecked(selectedMap.get(s));
                }
            }
        }
    }

    /**
     * 点击选框 事件
     *
     * @param pos       位置
     * @param isChecked 是否被选中
     */
    private void selectOption(int pos, boolean isChecked) {
        if (mTopicType == TOPIC_TY_ONLY) {
            for (int i = 0; i < radioButtonList.size(); i++) {
                if (isChecked) {
                    if (pos != i) {
                        radioButtonList.get(i).setChecked(!isChecked);
                       // updateResult(i, !isChecked);
                    } else {
                        radioButtonList.get(i).setChecked(isChecked);
                        //updateResult(i, isChecked);
                    }
                }
            }
        } else {
            //记录结果
            //updateResult(pos, isChecked);
        }

        //选中时候才会回调
        if (onResultCallback != null && isChecked) {
            onResultCallback.selectCallback(pos);
        }
    }

    private void updateResult(int pos, boolean isChecked) {
        selectedMap.put(pos, isChecked);

    }


    public void setOnResultCallback(OnResultCallback onResultCallback) {
        this.onResultCallback = onResultCallback;
    }

    public interface OnResultCallback {
        void selectCallback(int pos);
    }
}
