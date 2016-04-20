package com.pffair.android_libs.xproxy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pangff on 16/4/20.
 * Description ProxyIpVerify
 */
public class ProxyIpVerify extends Thread {

    public static final int VERIFY_OK = 1;

    private Handler mHandler;

    public ProxyIpVerify(Handler handler){
        this.mHandler = handler;
    }

    private LinkedBlockingQueue<IpInfo> mIpInfoLinkedBlockingQueue = new LinkedBlockingQueue<>();

    private Proxy proxy;

    private OkHttpClient clientProxy;

    private AtomicBoolean isStop = new AtomicBoolean(false);

    public void addIp(IpInfo ipInfo){
        mIpInfoLinkedBlockingQueue.add(ipInfo);
    }

    @Override
    public void run() {
        while (!isStop.get()) {
            try {
                IpInfo ipInfo = mIpInfoLinkedBlockingQueue.take();
                if (ipInfo != null) {
                    proxy = new Proxy(Proxy.Type.SOCKS,
                            new InetSocketAddress(ipInfo.ip, ipInfo.port));
                    clientProxy = new OkHttpClient.Builder().connectTimeout(90, TimeUnit.SECONDS)
                            .proxy(proxy)
                            .build();
                    Request request = new Request.Builder().url("http://www.whatismyip.com.tw")
                            .get().build();//查询本机ip地址请求
                    Response response = clientProxy.newCall(request).execute();
                    Log.e("ProxyIpVerify==代理后IP：",response.body().string());
                    //@TODO 要解析response 并添加验证后response IP和请求IP的比较，比较成功侯发送hander消息
                    if(true){
                        Message msg =  Message.obtain();
                        msg.what = VERIFY_OK;
                        Bundle bundle = new Bundle();
                        bundle.putString("ip",ipInfo.ip);
                        bundle.putInt("port",ipInfo.port);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
                    }
                }
                //调用验证接口
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopVerify(){
        isStop.set(false);
    }
}
