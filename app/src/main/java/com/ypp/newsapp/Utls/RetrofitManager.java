package com.ypp.newsapp.Utls;

import android.util.Base64;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ypp.newsapp.Config.ModelConfig;
import com.ypp.newsapp.DB.ListBean;
import com.ypp.newsapp.DB.getnews;
import com.ypp.newsapp.IwebCallback;
import com.ypp.newsapp.Iwebmanager;
import com.ypp.newsapp.Journalism_api;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：create by Administrator on 2020/4/22
 * TIME BY 15:47
 */
public class RetrofitManager implements Iwebmanager {
    private static final String TAG = "ssssss";
    //创建接口实例
    Journalism_api journalism_api;
    //云市场分配的密钥Id
    String secretId = "AKID3q0KG19weMKBws16S1gaayhyic0gap8b0s5";
    //云市场分配的密钥Key
    String secretKey = "d9kVMnnnti39epe5TDnD1LbjAU2ZFaj35MZxgvp1";
    String source = "market";
    // 请求头
    private static Map<String, String> headers = new HashMap<String, String>();
    //   查询参数
    private static Map<String, String> queryParams = new HashMap<String, String>();
    private  static Map<String,String > keyword=new HashMap<String, String>();
    private Retrofit retrofit;
    //单例模式
    private static RetrofitManager Instance;
    public static RetrofitManager getInstance(){
        if (Instance==null){
            synchronized (RetrofitManager.class){
                if (Instance==null){
                    Instance=new RetrofitManager();
                }
            }
        }
        return Instance;
    }
    //配置Retrofit参数
    //获取推荐新闻
    @Override
    public void get( final String url, final IwebCallback iwebCallback) {
        getJournalism_api(url).getgetnews(getHeaders(),getQueryParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<getnews>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(getnews value) {
                        iwebCallback.onRuccess(value.getResult().getList());
                        Log.i(TAG, "数据获取成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "数据获取失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private  Journalism_api getJournalism_api(String url){
        //实现网络请求
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        journalism_api=retrofit.create(Journalism_api.class);
        return journalism_api;
    }
    /*签名算法*/
    public static String calcAuthorization(String source, String secretId, String secretKey, String datetime) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String signStr = "x-date: " + datetime + "\n" + "x-source: " + source;
        Mac mac = Mac.getInstance("HmacSHA1");
        Key sKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(sKey);
        byte[] hash = mac.doFinal(signStr.getBytes("UTF-8"));
        String sig = Base64.encodeToString(hash,Base64.NO_WRAP);
        String auth = "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"" + sig + "\"";
        return auth;
    }
    /*获取头文件*/
    private Map<String, String> getHeaders() {
        String auth=getauth();
        headers.clear();
        //为请求头添加数据
        String datetime = getdatetime();
        headers.put("X-Source", source);
        headers.put("X-Date", datetime);
        headers.put("Authorization", auth);
        return headers;
    }
    /*获取QueryParams*/
    private Map<String, String> getQueryParams() {
        queryParams.clear();
        //为
        queryParams.put("channel", ModelConfig.CHANNEL);
        queryParams.put("num", "50");
        queryParams.put("start", "");
        return queryParams;
    }
    /*获取时间*/
    private String getdatetime() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        return datetime;
    }
    /*获取签名*/
    private String getauth() {
        String datetime = getdatetime();
        String auth = null;
        try {
            // 签名
            auth = calcAuthorization(source, secretId, secretKey, datetime);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }
    private Map<String,String> getKeyword(){
        keyword.clear();
        keyword.put("keyword", ModelConfig.KEYWORLD);
        return keyword;
    }
}
