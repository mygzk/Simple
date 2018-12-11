package com.example.simple.model.adress;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.simple.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by guozhk on 2018/12/9.
 */

public class AdressUtil {


    public void getAreaDictionaryList(final Context context) {

        new MyAsyncTask(context, mAddressResult).execute();

    }

    private IAddressResult mAddressResult;

    public void setIAddressResult(IAddressResult mAddressResult) {
        this.mAddressResult = mAddressResult;
    }

    public interface IAddressResult {

        void addressData(List<AdressBean> adressBeans);

        void fail(String msg);
    }


    class MyAsyncTask extends AsyncTask<Void, Void, List<AdressBean>> {

        private Context context;
        private IAddressResult iAddressResult;


        public MyAsyncTask(Context context, IAddressResult iAddressResult) {
            this.context = context;
            this.iAddressResult = iAddressResult;
        }

        @Override
        protected List<AdressBean> doInBackground(Void... voids) {

            //将json数据变成字符串
            StringBuilder stringBuilder = new StringBuilder();
            try {
                //获取assets资源管理器
                AssetManager assetManager = context.getApplicationContext().getAssets();
                //通过管理器打开文件并读取
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open("adress.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }


                String jsonStr = stringBuilder.toString();
                if (!TextUtils.isEmpty(jsonStr)) {
                    return GsonUtils.fromJson(jsonStr, new TypeToken<List<AdressBean>>() {
                    });
                }
            } catch (IOException e) {

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<AdressBean> adressBeans) {
            if (iAddressResult != null) {
                if (adressBeans != null) {
                    iAddressResult.addressData(adressBeans);
                } else {
                    iAddressResult.fail("获取数据失败");
                }
            }

        }
    }

}
