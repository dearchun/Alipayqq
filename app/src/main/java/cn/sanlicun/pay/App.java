package cn.sanlicun.pay;

import android.app.Application;

import com.tencent.android.tpush.XGPushConfig;


/**
 * Created by 小饭 on 2018/7/6.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XGPushConfig.enableDebug(this,true);
    }

}
