package cn.sanlicun.pay.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.sanlicun.pay.App;
import cn.sanlicun.pay.Constans;
import cn.sanlicun.pay.net.Api;
import cn.sanlicun.pay.net.INet;
import cn.sanlicun.pay.param.PushParam;
import de.robv.android.xposed.XposedBridge;

public class QrCodeReceiver extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent intent) {


        String money = intent.getStringExtra(Constans.MONEY);
        String mark = intent.getStringExtra(Constans.MARK);
        String type = intent.getStringExtra(Constans.TYPE);
        String payUrl = intent.getStringExtra(Constans.PAY_URL);

        PushParam pushParam = new PushParam();
        pushParam.setMark(mark);
        pushParam.setMoney(money);
        pushParam.setPay_url(payUrl);
        pushParam.setType(type);

        Api.PUSH_URL(context, pushParam, String.class, 1);

    }


}
