package cn.sanlicun.pay.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushProvider;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import cn.sanlicun.pay.Constans;
import cn.sanlicun.pay.model.PushMsgModel;

/**
 * Created by 小饭 on 2018/7/13.
 */

public class TencentReceiver extends XGPushBaseReceiver {
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {

    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        Toast.makeText(context, message.toString(), Toast.LENGTH_LONG).show();

        // 获取自定义key-value
        String customContent = message.getCustomContent();

        String param = JSONObject.parseObject(customContent).getString("param");


        PushMsgModel msgModel = JSONObject.parseObject(param, PushMsgModel.class);
        Intent intent = new Intent();
        intent.setAction( msgModel.getType());

        intent.putExtra(Constans.MARK, msgModel.getMark());
        intent.putExtra(Constans.MONEY, msgModel.getMoney());
        context.sendBroadcast(intent);


    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {

    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

    }
}
