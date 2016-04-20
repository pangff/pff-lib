package com.pffair.android_libs.xproxy;

import com.pffair.android_libs.R;
import com.pffair.android_libs.base.PageViewHolder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pangff on 16/4/20.
 * Description ProxyViewHolder
 */
public class ProxyViewHolder extends PageViewHolder {


    @Bind(R.id.bt_get_proxy_ip)
    Button btGetProxyIp;

    @Bind(R.id.bt_force_stop)
    Button btForceStop;

    /**
     * 代理线程
     */
    ProxyIpVerify mProxyIpVerify;

    /**
     * ip数据读取线程
     */
    ProxyIpReader mProxyIpReader;


    Handler mHandler = new Handler() {

        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what){
                case ProxyIpVerify.VERIFY_OK:
                    break;
            }
        }
    };

    public ProxyViewHolder(Context context) {
        super(context);
    }

    @Override
    public void onBindView(ViewGroup view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void onAttach(ViewGroup view) {
        mProxyIpVerify = new ProxyIpVerify(mHandler);
        mProxyIpReader = new ProxyIpReader(mProxyIpVerify);

        btGetProxyIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProxyIpVerify.start();
                mProxyIpReader.start();
            }
        });

        btForceStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProxyIpReader.stopReader();
                mProxyIpVerify.stopVerify();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.xproxy;
    }
}
