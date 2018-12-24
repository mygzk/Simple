package com.example.simple.model.adress;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.simple.R;
import com.example.simple.dialog.DialogHelper;
import com.example.simple.utils.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 2018/12/9.
 * 地址选择 view
 */

public class AdressView extends FrameLayout {

    private String TAG = AdressView.class.getSimpleName();

    private ImageView imgAdressCancel;
    private TextView tvSure;
    private TabLayout tabAdress;

    private RecyclerView rcAdress;
    private AdressAdpter mAdapter;

    private AdressUtil mAddressUtil;

    private List<AdressBean> mProvinceAddressData = new ArrayList<>();
    private List<AdressBean> mCityAddressData = new ArrayList<>();
    private List<AdressBean> mAreaAddressData = new ArrayList<>();


    private String KEY_P = "KEY_P";
    private String KEY_C = "KEY_C";
    private String KEY_A = "KEY_A";
    private Map<String, AdressBean> mSecletData = new HashMap<>();


    private boolean hasAllCountry = false;//包含全国


    private int TYPE_PROVICE = 0;
    private int TYPE_CITY = 1;
    private int TYPE_AREA = 2;
    private int mType = TYPE_PROVICE;


    public AdressView(Context context) {
        this(context, null);
    }

    public AdressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_adress, this);
        imgAdressCancel = view.findViewById(R.id.dialog_select_address_cancel);
        tvSure = view.findViewById(R.id.dialog_select_address_sure);
        tabAdress = view.findViewById(R.id.dialog_select_address_tab);
        rcAdress = view.findViewById(R.id.dialog_select_address_rc);
        tvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCall();
            }
        });
        rcAdress.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AdressAdpter(new ArrayList<AdressBean>());
        mAdapter.setOnItemClick(new AdressAdpter.onItemClick() {
            @Override
            public void onItemClick(int pos) {
                AdressBean bean = mAdapter.getItemData(pos);
                if (mType == TYPE_PROVICE) {
                    mCityAddressData.clear();
                    mAreaAddressData.clear();
                    mType = TYPE_CITY;
                    mSecletData.clear();
                    mSecletData.put(KEY_P, bean);//  add(bean);
                    updateTab();
                    if (bean.isQuanguo()) {
                        mCityAddressData.add(AdressBean.getQuanguoBean());
                        mAreaAddressData.add(AdressBean.getQuanguoBean());
                    }
                    getData(bean.getAreaId());

                } else if (mType == TYPE_CITY) {
                    mType = TYPE_AREA;
                    if (mSecletData.containsKey(KEY_A)) {
                        mSecletData.remove(KEY_A);
                    }

                    mSecletData.put(KEY_C, bean);
                    updateTab();
                    getData(bean.getAreaId());
                } else {
                    mType = TYPE_AREA;
                    mSecletData.put(KEY_A, bean);
                    updateTab();
                }

            }
        });
        rcAdress.setAdapter(mAdapter);


        initAddressUtil();
        updateTab();
    }

    private void resultCall() {
        AdressBean proviceBean = null;
        AdressBean cityBean = null;
        AdressBean areaBean = null;


        if (mSecletData.size() > 0) {
            if (mSecletData.containsKey(KEY_P)) {
                proviceBean = mSecletData.get(KEY_P);
            }
            if (mSecletData.containsKey(KEY_C)) {
                cityBean = mSecletData.get(KEY_C);
            }
            if (mSecletData.containsKey(KEY_A)) {
                areaBean = mSecletData.get(KEY_A);
            }
        }


        if (mAdressCallback != null) {

            mAdressCallback.result(proviceBean, cityBean, areaBean);

        }

    }


    public void initData() {
        getData("");
    }


    private void initAddressUtil() {
        mAddressUtil = new AdressUtil();

        mAddressUtil.setIAddressResult(new AdressUtil.IAddressResult() {
            @Override
            public void addressData(List<AdressBean> adressBeans) {

                if (mType == TYPE_PROVICE) {
                    if (!hasAllCountry) {
                        int pos = -1;
                        for (int i = 0; i < adressBeans.size(); i++) {
                            AdressBean bean1 = adressBeans.get(i);
                            if (bean1.isQuanguo()) {
                                pos = i;
                                break;
                            }
                        }
                        if (pos != -1) {
                            adressBeans.remove(pos);
                        }
                    }
                    mProvinceAddressData.clear();
                    mProvinceAddressData.addAll(adressBeans);
                } else if (mType == TYPE_CITY) {
                    mCityAddressData.clear();
                    mCityAddressData.addAll(adressBeans);
                } else {
                    mAreaAddressData.clear();
                    mAreaAddressData.addAll(adressBeans);
                }
                mAdapter.updateDatas(adressBeans);
            }

            @Override
            public void fail(String msg) {
                Log.e("aaaa", "msg:" + msg);
            }
        });


    }


    private void getAddressData(String areaParentId) {
        mAddressUtil.getAreaDictionaryList(getContext());
    }

    private void updateTab() {
        List<AdressBean> tabDatas = getTabData();
        int count = tabAdress.getTabCount();
        if (tabDatas.size() > 0) {
            for (int i = 0; i < tabDatas.size(); i++) {
                AdressBean bean = tabDatas.get(i);
                if (i < count) {//更新tab
                    TabLayout.Tab tab = tabAdress.getTabAt(i);
                    if (tab.getCustomView() != null) {
                        TextView tv = (TextView) tab.getCustomView();
                        tv.setText(bean.getAreaName());
                    } else {
                        TextView tv = getTabView(bean, i);
                        tab.setCustomView(tv);
                    }
                }

                if (i == tabDatas.size() - 1) {//移除多余的tab
                    if (i < count - 1) {
                        while (i < tabAdress.getTabCount() - 1) {
                            tabAdress.removeTabAt(tabAdress.getTabCount() - 1);
                        }
                    }
                }

            }
        }

        if (tabDatas.size() < 3) {
            TabLayout.Tab tab = tabAdress.newTab();
            tab.setText("请选择");
            tab.setTag(-1);
            tabAdress.addTab(tab);
        }

        tabAdress.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabAdress.getTabAt(tabAdress.getTabCount() - 1).select();
            }
        }, 500);
    }

    private TextView getTabView(final AdressBean bean, final int pos) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        lp.setMargins(DensityUtil.dip2px(getContext(), 5), 0, DensityUtil.dip2px(getContext(), 0), 0);
        tv.setLayoutParams(lp);
        tv.setMinWidth(DensityUtil.dip2px(getContext(), 60));
        tv.setText(bean.getAreaName());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(getResources().getColorStateList(R.color.selector_text_color));
        // tv.setTextColor(getResources().getColor(R.color.color_282828));

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mType = pos;
                tabAdress.getTabAt(pos).select();
                getData(bean.getAreaId());
            }
        });
        return tv;
    }

    public void getData(String areaId) {

        if (mType == TYPE_PROVICE) {
            if (mProvinceAddressData.size() > 0) {
                mAdapter.updateDatas(mProvinceAddressData);
                return;
            }
        } else if (mType == TYPE_CITY) {
            if (mCityAddressData.size() > 0) {
                mAdapter.updateDatas(mCityAddressData);
                return;
            }
        } else {
            if (mAreaAddressData.size() > 0) {
                mAdapter.updateDatas(mAreaAddressData);
                return;
            }
        }


        getAddressData(areaId);

    }

    public void setHasAllCountry(boolean hasAllCountry) {
        this.hasAllCountry = hasAllCountry;
    }


    private IAdressCallback mAdressCallback;

    public void setAdressCallback(IAdressCallback callback) {
        this.mAdressCallback = callback;
    }

    public interface IAdressCallback {
        void result(AdressBean provinceBean, AdressBean cityBean, AdressBean areaBean);
    }


    public void showDialog(FragmentManager fragmentManager, final IAdressCallback callback) {
        int h = (int) (getResources().getDisplayMetrics().heightPixels * 0.7);
        final DialogHelper helper = new DialogHelper.Builder()
                .setGravity(Gravity.BOTTOM)
                .setWidth(getResources().getDisplayMetrics().widthPixels)
                .setHight(h)
                .setCancelableOutside(true)
                .create();

        AdressView adressView = new AdressView(getContext());
        adressView.setHasAllCountry(true);
        setAdressCallback(new AdressView.IAdressCallback() {
            @Override
            public void result(AdressBean provinceBean, AdressBean cityBean, AdressBean areaBean) {
                helper.dismiss();

                if (callback != null) {
                    callback.result(provinceBean, cityBean, areaBean);
                }
            }
        });

        imgAdressCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.dismiss();
            }
        });
        adressView.initData();

        helper.setDialogView(this);

        helper.show(fragmentManager, "adress");

    }


    /**
     * 获取 tabdata
     *
     * @return datas
     */
    private List<AdressBean> getTabData() {
        List<AdressBean> datas = new ArrayList<>();
        if (mSecletData.containsKey(KEY_P)) {
            datas.add(mSecletData.get(KEY_P));
        }
        if (mSecletData.containsKey(KEY_C)) {
            datas.add(mSecletData.get(KEY_C));
        }
        if (mSecletData.containsKey(KEY_A)) {
            datas.add(mSecletData.get(KEY_A));
        }

        return datas;

    }

}
