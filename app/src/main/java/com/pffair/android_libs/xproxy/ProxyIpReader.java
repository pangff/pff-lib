package com.pffair.android_libs.xproxy;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pangff on 16/4/20.
 * Description ProxyIpReader
 */
public class ProxyIpReader extends Thread{

    ProxyIpVerify mProxyIpVerify;

    public ProxyIpReader(ProxyIpVerify proxyIpVerify){
        mProxyIpVerify = proxyIpVerify;
    }

    private LinkedBlockingQueue<IpInfo> cacheIpInfos = new LinkedBlockingQueue<>();

    OkHttpClient mClient = new OkHttpClient.Builder().build();

    //@TODO 获取IP列表的接口
    String url = "";

    private AtomicBoolean isStop = new AtomicBoolean(false);

    @Override
    public void run() {
       while (!isStop.get()){
           try {
               if(cacheIpInfos.size()==0){
                   getIps();
               }
               IpInfo ipInfo = cacheIpInfos.take();
               if(ipInfo!=null){
                   //@TODO 我这里直接添加到了验证队列，这部分考虑是否添加 不均匀算法，根据算法过滤侯再去验证
                   //@TODO 还是最终验证结束后再根据 算法 决定是否最终刷新
                   mProxyIpVerify.addIp(ipInfo);
               }
               if(cacheIpInfos.size()==0){
                   getIps();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    private void getIps() throws IOException {
        Request request = new Request.Builder().url(url)
                .get().build();//查询本机ip地址请求
        Response response = mClient.newCall(request).execute();
        Log.e("ProxyIp====response",response.body().string());
        //@TODO 要解析response 然后解析结果 扔到 cacheIpInfos 中
//        IpInfo ipInfo = new IpInfo();
//        cacheIpInfos.add()
    }


    public void stopReader(){
        isStop.set(false);
    }
}
